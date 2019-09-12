package com.hxj.enjoyandroid.fragments.ndk;

import android.view.View;
import android.widget.TextView;

import com.hxj.enjoyandroid.R;
import com.hxj.enjoyandroid.fragments.BaseFragment;

public class CMakeListFragment extends BaseFragment {

    static {
        System.loadLibrary("hellocpp-lib");
    }

    private native String helloJni();

    @Override
    public void initView(View view) {
        TextView msg = view.findViewById(R.id.tv_msg);

        msg.setText("hello jni: " + helloJni());
    }

    @Override
    public int loadLayoutId() {
        return R.layout.fragment_cmake_list;
    }

    @Override
    public void initData() {

    }
}
