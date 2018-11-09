%rename (_now) kuzzleio::Server::now(query_options*);
%rename (_now) kuzzleio::Server::now();

%javamethodmodifiers kuzzleio::Server::now(query_options* options) "private";
%javamethodmodifiers kuzzleio::Server::now() "private";
%typemap(javacode) kuzzleio::Server %{
  /**
   * {@link #now(QueryOptions)}
   * @return java.util.Date
   */
  public java.util.Date now() {
    long res = _now();

    return new java.util.Date(res);
  }

  /**
   * Returns the current Kuzzle UTC timestamp
   *
   * @param options - Request options
   * @return a DateResult
   */
  public java.util.Date now(QueryOptions options) {
    long res = _now(options);

    return new java.util.Date(res);
  }
%}

%typemap(jni) std::string* "jobject"
%typemap(jtype) std::string* "java.lang.String"
%typemap(jstype) std::string* "java.lang.String"
%typemap(javain) std::string* "$javainput"
%typemap(in) std::string * (std::string strTemp) {
  jobject oInput = $input;
  if (oInput != NULL) {
    jstring sInput = static_cast<jstring>( oInput );

    const char * $1_pstr = (const char *)jenv->GetStringUTFChars(sInput, 0);
    if (!$1_pstr) return $null;
    strTemp.assign( $1_pstr );
    jenv->ReleaseStringUTFChars( sInput, $1_pstr);
    $1 = &strTemp;
  } else {
    $1 = NULL;
  }
}
%apply std::string * { std::string* };


// const char** to String[]

%typemap(jni) const char**, const char * const * "jobjectArray";
%typemap(jtype) const char**, const char * const * "String[]";
%typemap(jstype) const char**, const char * const * "String[]";

%typemap(in) const char**, const char * const * %{
  jobject fu = (jobject) jenv->GetObjectArrayElement($input, 0);

  size_t size = jenv->GetArrayLength($input);
  int i = 0;
  char **res = (char**)malloc(sizeof(*res) * size + 1);
  while(i < size) {
    jobject fu = (jobject) jenv->GetObjectArrayElement($input, i);
    res[i] = (char *)jenv->GetStringUTFChars(static_cast<jstring>(fu), 0);
    i++;
  }
  res[i] = NULL;
  $1 = res;
%}

%typemap(out) const char**, const char * const *  {
  size_t count = 0;
  const char **pos = const_cast<const char**>($1);
  while(pos[++count]);

  $result = JCALL3(NewObjectArray, jenv, count, JCALL1(FindClass, jenv, "java/lang/String"), NULL);
  size_t idx = 0;
  while (*pos) {
    jobject str = JCALL1(NewStringUTF, jenv, *pos);
    assert(idx < count);
    JCALL3(SetObjectArrayElement, jenv, $result, idx++, str);
    *pos++;
  }
}

%typemap(javain) const char**, const char * const * "$javainput"
%typemap(javaout) const char**, const char * const * {
  return $jnicall;
}

// UserRight** to UserRight[]

%typemap(jni) kuzzleio::user_right** "jobjectArray";
%typemap(jtype) kuzzleio::user_right** "UserRight[]";
%typemap(jstype) kuzzleio::user_right** "UserRight[]";

%typemap(in) kuzzleio::user_right** %{
  jobject fu = (jobject) jenv->GetObjectArrayElement($input, 0);

  size_t size = jenv->GetArrayLength($input);
  int i = 0;
  user_right** res = (kuzzleio::user_right**)malloc(sizeof(*res) * size + 1);
  while(i < size) {
    jobject fu = (jobject) jenv->GetObjectArrayElement($input, i);
    res[i] = (user_right *)fu;
    i++;
  }
  res[i] = NULL;
  $1 = res;
%}

%typemap(out) kuzzleio::user_right** {
  size_t count = 0;
  user_right** pos = $1;
  while(pos[++count]);

  $result = JCALL3(NewObjectArray, jenv, count, JCALL1(FindClass, jenv, "io/kuzzle/sdk/UserRight"), NULL);
  size_t idx = 0;

  jclass cls = jenv->FindClass("io/kuzzle/sdk/UserRight");
  jmethodID constructor = jenv->GetMethodID(cls, "<init>", "()V");
  jobject user_right = jenv->NewObject(cls, constructor);
  while (*pos) {
    // Set controller
    jmethodID setCtrl = jenv->GetMethodID(cls, "setController", "(Ljava/lang/String;)V");
    jstring ctrl_string = JCALL1(NewStringUTF, jenv, (*pos)->controller);
    jenv->CallVoidMethod(user_right, setCtrl, ctrl_string);

    // Set action
    jmethodID setAction = jenv->GetMethodID(cls, "setAction", "(Ljava/lang/String;)V");
    jstring action_string = JCALL1(NewStringUTF, jenv, (*pos)->action);
    jenv->CallVoidMethod(user_right, setAction, action_string);

    // Set index
    jmethodID setIndex = jenv->GetMethodID(cls, "setIndex", "(Ljava/lang/String;)V");
    jstring index_string = JCALL1(NewStringUTF, jenv, (*pos)->index);
    jenv->CallVoidMethod(user_right, setIndex, index_string);

    // Set collection
    jmethodID setCollection = jenv->GetMethodID(cls, "setCollection", "(Ljava/lang/String;)V");
    jstring collection_string = JCALL1(NewStringUTF, jenv, (*pos)->collection);
    jenv->CallVoidMethod(user_right, setCollection, collection_string);

    // Set value
    jmethodID setValue = jenv->GetMethodID(cls, "setValue", "(Ljava/lang/String;)V");
    jstring value_string = JCALL1(NewStringUTF, jenv, (*pos)->value);
    jenv->CallVoidMethod(user_right, setValue, value_string);

    assert(idx < count);
    JCALL3(SetObjectArrayElement, jenv, $result, idx++, user_right);
    *pos++;
  }
}

%typemap(javain) kuzzleio::user_right** "$javainput"
%typemap(javaout) kuzzleio::user_right** {
  return $jnicall;
}
