package com.example.dell.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.viewpager.fragment.MeFragment;
import com.example.dell.viewpager.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener ,View.OnClickListener{
    private ViewPager mViewPager;
    private ImageView mNews,mMe;
    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void init() {
        //构造Fragments;
        mFragments=new ArrayList<>();
        mFragments.add(new NewsFragment());
        mFragments.add(new MeFragment());

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()) {
        });
        mViewPager.addOnPageChangeListener(this);
        mNews.setOnClickListener(this);
        mMe.setOnClickListener(this);


        resetImageViewSelected();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        mViewPager.setCurrentItem(0);
        mNews.setSelected(true);
    }

    private void resetImageViewSelected() {
        mNews.setSelected(false);
        mMe.setSelected(false);

    }

    private void initView() {
        mViewPager=(ViewPager)findViewById(R.id.view_pager);
        mNews=(ImageView)findViewById(R.id.image_news);
        mMe=(ImageView)findViewById(R.id.image_me);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                mNews.setSelected(true);
                mMe.setSelected(false);
                break;
            case 1:
                mMe.setSelected(true);
                mNews.setSelected(false);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_news:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.image_me:
                mViewPager.setCurrentItem(1);
                break;
        }

    }
    public  class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
