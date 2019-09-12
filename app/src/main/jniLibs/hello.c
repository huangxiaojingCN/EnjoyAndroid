#include<jni.h>


JNIEXPORT jstring JNICALL
Java_com_hxj_enjoyandroid_fragments_ndk_AndroidMakeFragment_nativeTest(JNIEnv *env,
                                                                       jobject instance) {
    const char *returnValue = "66666";

    return (*env)->NewStringUTF(env, returnValue);
}