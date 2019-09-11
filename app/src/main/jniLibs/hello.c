#include<jni.h>


JNIEXPORT jint JNICALL
Java_com_hxj_enjoyandroid_NdkActivity_nativeTest(JNIEnv *env, jobject instance) {

    return 666;

}

JNIEXPORT jstring JNICALL
Java_com_hxj_enjoyandroid_NdkActivity_nativeTest1(JNIEnv *env, jobject instance, jstring a_) {
    const char *a = (*env)->GetStringUTFChars(env, a_, 0);

    const char *returnValue = "66666";

    (*env)->ReleaseStringUTFChars(env, a_, a);

    return (*env)->NewStringUTF(env, returnValue);
}