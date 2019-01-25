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

%typemap(jni) std::vector<std::shared_ptr<kuzzleio::UserRight>> "jobjectArray";
%typemap(jtype) std::vector<std::shared_ptr<kuzzleio::UserRight>> "UserRight[]";
%typemap(jstype) std::vector<std::shared_ptr<kuzzleio::UserRight>> "UserRight[]";

%typemap(out) std::vector<std::shared_ptr<kuzzleio::UserRight>> {
  std::vector<std::shared_ptr<UserRight>> * user_rights = & $1;

  jclass user_right_class = jenv->FindClass("io/kuzzle/sdk/UserRight");
  $result = JCALL3(NewObjectArray, jenv, user_rights->size(), user_right_class, NULL);

  jmethodID
    constructor = jenv->GetMethodID(user_right_class, "<init>", "()V"),
    setCtrl = jenv->GetMethodID(user_right_class, "setController", "(Ljava/lang/String;)V"),
    setAction = jenv->GetMethodID(user_right_class, "setAction", "(Ljava/lang/String;)V"),
    setIndex = jenv->GetMethodID(user_right_class, "setIndex", "(Ljava/lang/String;)V"),
    setCollection = jenv->GetMethodID(user_right_class, "setCollection", "(Ljava/lang/String;)V"),
    setValue = jenv->GetMethodID(user_right_class, "setValue", "(Ljava/lang/String;)V");

  for (size_t i = 0; i < user_rights->size(); ++i) {
    jobject user_right = jenv->NewObject(user_right_class, constructor);

    // Set controller
    jstring ctrl_string = JCALL1(NewStringUTF, jenv, (*user_rights)[i]->controller().c_str());
    jenv->CallVoidMethod(user_right, setCtrl, ctrl_string);

    // Set action
    jstring action_string = JCALL1(NewStringUTF, jenv, (*user_rights)[i]->action().c_str());
    jenv->CallVoidMethod(user_right, setAction, action_string);

    // Set index
    jstring index_string = JCALL1(NewStringUTF, jenv, (*user_rights)[i]->index().c_str());
    jenv->CallVoidMethod(user_right, setIndex, index_string);

    // Set collection
    jstring collection_string = JCALL1(NewStringUTF, jenv, (*user_rights)[i]->collection().c_str());
    jenv->CallVoidMethod(user_right, setCollection, collection_string);

    // Set value
    jstring value_string = JCALL1(NewStringUTF, jenv, (*user_rights)[i]->value().c_str());
    jenv->CallVoidMethod(user_right, setValue, value_string);

    JCALL3(SetObjectArrayElement, jenv, $result, i, user_right);
  }
}

%typemap(javaout) std::vector<std::shared_ptr<kuzzleio::UserRight>> {
  return $jnicall;
}
