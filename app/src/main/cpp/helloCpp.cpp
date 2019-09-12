//
// Created by ciggo on 2019-09-12.
//

#include <jni.h>
#include <string>
#include <android/log.h>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_hxj_enjoyandroid_fragments_ndk_CMakeListFragment_helloJni(JNIEnv *env, jobject instance) {

    string text = "牛叉的cpp";

    __android_log_print(ANDROID_LOG_DEBUG, "HelloJni", "text: %s", text.c_str());

    return env->NewStringUTF(text.c_str());
}