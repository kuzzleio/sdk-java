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
