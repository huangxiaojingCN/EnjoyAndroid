//
// Created by ciggo on 2019-09-12.
//

#include <jni.h>
#include <string>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_hxj_enjoyandroid_fragments_ndk_CMakeListFragment_helloJni(JNIEnv *env, jobject instance) {

    string text = "66666";

    return env->NewStringUTF(text.c_str());
}