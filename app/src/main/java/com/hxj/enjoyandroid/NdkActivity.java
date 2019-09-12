package com.hxj.enjoyandroid;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hxj.enjoyandroid.fragments.ndk.AndroidMakeFragment;
import com.hxj.enjoyandroid.fragments.ndk.CMakeListFragment;

import java.util.ArrayList;
import java.util.List;

public class NdkActivity extends AppCompatActivity {

    private String[] titles = new String[] {"Android.mk", "CMakeLists.txt"};

    private List<Fragment> fragments = new ArrayList<>();

    private TabLayout mTablayout;

    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);

        mTablayout = findViewById(R.id.tabLayout);
        mViewpager = findViewById(R.id.viewpager);

        initData();

        mTablayout.addTab(mTablayout.newTab().setText(titles[0]));
        mTablayout.setupWithViewPager(mViewpager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(myAdapter);

        mTablayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initData() {
        AndroidMakeFragment androidMakeFragment = new AndroidMakeFragment();
        fragments.add(androidMakeFragment);

        CMakeListFragment cMakeListFragment = new CMakeListFragment();
        fragments.add(cMakeListFragment);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}