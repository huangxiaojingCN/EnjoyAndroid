package com.hxj.enjoyandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NdkActivity extends AppCompatActivity {

    static {
        System.loadLibrary("hello-jni");
    }

    private native int nativeTest();

    private native String nativeTest1(String a);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);

        TextView msg = findViewById(R.id.tv_msg);
        msg.setText("hello ndk: " + nativeTest1("hello"));
    }
}