package com.hxj.enjoyandroid;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxj.enjoyandroid.fragments.CanvasApiFragment;
import com.hxj.enjoyandroid.fragments.CanvasPathFragment;
import com.hxj.enjoyandroid.fragments.ComposeViewFragment;
import com.hxj.enjoyandroid.fragments.EventViewFragment;
import com.hxj.enjoyandroid.fragments.GesutureOneFragment;
import com.hxj.enjoyandroid.fragments.HistogramFragment;
import com.hxj.enjoyandroid.fragments.PaintBasicFragment;
import com.hxj.enjoyandroid.fragments.PathApiFragment;
import com.hxj.enjoyandroid.fragments.ScreenCoordinateFragment;
import com.hxj.enjoyandroid.fragments.SelfDrawViewFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private MyViewPager myViewPager;

    private List<Fragment> fragments = new ArrayList<>();

    private String[] titles = new String[]{
            "坐标系",
            "Canvas",
            "path",
            "画笔",
            "手势1",
            "自绘",
            "直方图",
            "组合",
            "事件"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewpager);

        initData();

        myViewPager = new MyViewPager(getSupportFragmentManager(), fragments, titles);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[0]));
        mViewPager.setAdapter(myViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
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
        ScreenCoordinateFragment screenCoordinateFragment = new ScreenCoordinateFragment();
        fragments.add(screenCoordinateFragment);

        CanvasApiFragment canvasApiFragment = new CanvasApiFragment();
        fragments.add(canvasApiFragment);

        CanvasPathFragment canvasPathFragment = new CanvasPathFragment();
        fragments.add(canvasPathFragment);

        /*PathApiFragment pathApiFragment = new PathApiFragment();
        fragments.add(pathApiFragment);*/

        PaintBasicFragment paintBasicFragment = new PaintBasicFragment();
        fragments.add(paintBasicFragment);

        GesutureOneFragment gesutureOneFragment = new GesutureOneFragment();
        fragments.add(gesutureOneFragment);

        SelfDrawViewFragment selfDrawViewFragment = new SelfDrawViewFragment();
        fragments.add(selfDrawViewFragment);

        HistogramFragment histogramFragment = new HistogramFragment();
        fragments.add(histogramFragment);

        ComposeViewFragment composeViewFragment = new ComposeViewFragment();
        fragments.add(composeViewFragment);

        EventViewFragment eventViewFragment = new EventViewFragment();
        fragments.add(eventViewFragment);
    }

    public static class MyViewPager extends FragmentPagerAdapter {

        private String[] mTitles;
        private List<Fragment> mFragments;

        public MyViewPager(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            this.mFragments = fragments;
            this.mTitles = titles;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
