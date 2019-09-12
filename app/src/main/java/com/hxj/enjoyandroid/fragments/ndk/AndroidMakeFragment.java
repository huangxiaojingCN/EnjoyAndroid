package com.hxj.enjoyandroid.fragments.ndk;

import android.view.View;
import android.widget.TextView;

import com.hxj.enjoyandroid.R;
import com.hxj.enjoyandroid.fragments.BaseFragment;

public class AndroidMakeFragment extends BaseFragment {
    /*
    *  需要打开 app build.gradle
    *
    *
    externalNativeBuild{
        ndkBuild{
            path   "src/main/jniLibs/Android.mk"
        }

    }

    externalNativeBuild{
        ndkBuild{
            path   "src/main/jniLibs/Android.mk"
        }

    }
    *
    */
   /* static {
        System.loadLibrary("hello-jni");
    }*/

    private TextView mTvMsg;

   // private native String nativeTest();

    @Override
    public void initView(View view) {
        mTvMsg = view.findViewById(R.id.tv_msg);

        mTvMsg.setText("hello jni: " /*+ nativeTest()*/);
    }

    @Override
    public int loadLayoutId() {
        return R.layout.fragment_androidmk;
    }

    @Override
    public void initData() {

    }
}
