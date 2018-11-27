VERSION = 1.0.0

ROOT_DIR = $(dir $(abspath $(lastword $(MAKEFILE_LIST))))
ifeq ($(OS),Windows_NT)
	STATICLIB = .lib
	DYNLIB = .dll
	GOROOT ?= C:/Go
	GOCC ?= $(GOROOT)bin\go
	SEP = \\
	RM = del /Q /F /S
	RRM = rmdir /S /Q
	MV = rename
	CMDSEP = &
	ROOT_DIR_CLEAN = $(subst /,\,$(ROOT_DIR))
	LIB_PREFIX =
else
	STATICLIB = .a
	DYNLIB = .so
	GOROOT ?= /usr/local/go
	GOCC ?= $(GOROOT)/bin/go
	SEP = /
	RM = rm -f
	RRM = rm -f -r
	MV = mv -f
	CMDSEP = ;
	ROOT_DIR_CLEAN = $(ROOT_DIR)
	LIB_PREFIX = lib
endif

PATHSEP = $(strip $(SEP))
JAVA_HOME ?= /usr/local
ROOTOUTDIR = $(ROOT_DIR)/build
JAVAINCLUDE = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux
SWIG = swig

INCLUDES_PATH = -I.$(PATHSEP)sdk-cpp$(PATHSEP)build$(PATHSEP)kuzzle-cpp-sdk$(PATHSEP)include \
								-I.$(PATHSEP)sdk-cpp$(PATHSEP)sdk-c$(PATHSEP)build$(PATHSEP)kuzzle-c-sdk$(PATHSEP)include$(PATHSEP) \
								-I.$(PATHSEP)sdk-cpp$(PATHSEP)src \


CXXFLAGS = -g -fPIC -std=c++11 -I.$(PATHSEP)sdk-cpp$(PATHSEP)include \
					 $(INCLUDES_PATH) \
					 -L.$(PATHSEP)sdk-cpp$(PATHSEP)sdk-c$(PATHSEP)build$(PATHSEP)kuzzle-c-sdk$(PATHSEP)lib

	# -I.$(PATHSEP)sdk-cpp$(PATHSEP)src
	# -I.$(PATHSEP)sdk-cpp$(PATHSEP)sdk-c$(PATHSEP)include$(PATHSEP)
	# -I.$(PATHSEP)sdk-cpp$(PATHSEP)sdk-c$(PATHSEP)build$(PATHSEP)
LDFLAGS = -lkuzzlesdk

SRCS = kcore_wrap.cxx
OBJS = $(SRCS:.cxx=.o)

all: java

kcore_wrap.o: kcore_wrap.cxx
	$(CXX) -c $< -o $@ $(CXXFLAGS) $(LDFLAGS) $(JAVAINCLUDE)

makedir:
ifeq ($(OS),Windows_NT)
	@if not exist $(subst /,\,$(ROOTOUTDIR)/java/io/kuzzle/sdk) mkdir $(subst /,\,$(ROOTOUTDIR)/java/io/kuzzle/sdk)
else
	mkdir -p $(ROOTOUTDIR)/java/io/kuzzle/sdk
endif

make_c_sdk:
	cd sdk-cpp/sdk-c && $(MAKE)

swig:
	$(SWIG) -Wall -c++ -java -package io.kuzzle.sdk -outdir $(OUTDIR) -o $(SRCS) \
		$(INCLUDES_PATH) \
		$(JAVAINCLUDE) swig/core.i

make_lib:
	$(CXX) -shared kcore_wrap.o -o $(OUTDIR)/$(LIB_PREFIX)kuzzle-wrapper-java$(DYNLIB) $(CXXFLAGS) $(LDFLAGS) $(JAVAINCLUDE)
	strip $(ROOTOUTDIR)/java/io/kuzzle/sdk/$(LIB_PREFIX)kuzzle-wrapper-java$(DYNLIB)

remove_so:
	rm -rf .$(PATHSEP)sdk-cpp$(PATHSEP)sdk-c$(PATHSEP)build$(PATHSEP)*.so*

java: OUTDIR=$(ROOTOUTDIR)/java/io/kuzzle/sdk
java: makedir make_c_sdk remove_so swig $(OBJS) make_lib
	$(JAVA_HOME)/bin/javac $(OUTDIR)/*.java
	mkdir -p $(ROOTOUTDIR)/io/kuzzle/sdk/resources
	cp build/java/io/kuzzle/sdk/$(LIB_PREFIX)kuzzle-wrapper-java.so build/io/kuzzle/sdk/resources
	mkdir -p $(ROOTOUTDIR)/java/src/main/resources
	mkdir -p $(ROOTOUTDIR)/java/src/main/java
	mv build/java/io/kuzzle/sdk/$(LIB_PREFIX)kuzzle-wrapper-java.so $(ROOTOUTDIR)/java/src/main/resources/
	cd build/java && ln -sfr io/kuzzle/sdk/* src/main/java/ && cd -
	cd build/java && VERSION=$(VERSION) gradle sourcesJar jar javadocJar
	cp -p sdk-cpp/sdk-c/build/$(LIB_PREFIX)kuzzlesdk$(STATICLIB) $(OUTDIR)
	cp build/java/build/libs/* build/

test: $(ROOT_DIR)$(PATHSEP)build$(PATHSEP)*.jar
	cd $(ROOT_DIR)$(PATHSEP)features$(PATHSEP)java && gradle cucumber

package: $(ROOT_DIR)$(PATHSEP)build$(PATHSEP)*.jar
	mkdir $(ROOT_DIR)$(PATHSEP)deploy
	cp -fr $(ROOT_DIR)$(PATHSEP)build$(PATHSEP)kuzzlesdk-$(VERSION)-$(ARCH).jar $(ROOT_DIR)$(PATHSEP)deploy$(PATHSEP)kuzzlesdk-$(ARCH).jar

clean:
	cd sdk-cpp && $(MAKE) clean
	rm -rf build/java/io build/io build/java/src kcore_wrap.cxx  kcore_wrap.h  kcore_wrap.o
	rm -rf features/java/.gradle/ features/java/build/
	rm -rf build/java/.gradle/ build/java/build/
	rm -rf build/*.jar deploy



.PHONY: all clean swig java remove_so make_lib make_c_sdk makedir
