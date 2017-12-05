package com.yifeng.mdstudysamples.tablayout_bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.yifeng.mdstudysamples.BaseActivity;
import com.yifeng.mdstudysamples.R;
import com.yifeng.mdstudysamples.tablayout_top.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout 底部菜单页
 */
public class TabLayoutBottomActivity extends BaseActivity {


    /**
     *
     */
    private ViewPager mViewPager;
    private TabLayoutPagerAdapter mTabLayoutPagerAdapter;
    //
    private TabLayout mTabLayout;

    /**
     * 数据
     */
    private List<String> mTabIndicatorList;
    private List<Fragment> mTabFragmentList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_bottom_activity);


        // 初始化数据
        initData();
        // 初始化View
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 初始化tab数据
        mTabIndicatorList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mTabIndicatorList.add("Tab " + i);
        }
        // 初始化fragment数据
        mTabFragmentList = new ArrayList<>();
        for (String s : mTabIndicatorList) {
            mTabFragmentList.add(TabLayoutFragment.newInstance(s));
        }
    }

    /**
     * 初始化
     */
    private void initView() {
        //---------ViewPager---------
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mTabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabLayoutPagerAdapter);
        //--------TabLayout-------
        mTabLayout = (TabLayout) findViewById(R.id.tl_tab);
        // 模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        // 设置高度为0，就看不见滑块了
        mTabLayout.setSelectedTabIndicatorHeight(0);
        // 阴影
        ViewCompat.setElevation(mTabLayout, 10);
        // ViewPager与TabLayout关联
        mTabLayout.setupWithViewPager(mViewPager);
        // 设置自定义
        for (int i = 0; i < mTabIndicatorList.size(); i++) {
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                // 自定义的
                itemTab.setCustomView(R.layout.tablayout_custom_item);
                // 设置textView的显示文字
                TextView textView = (TextView) itemTab.getCustomView().findViewById(R.id.tablayout_item_textview);
                textView.setText(mTabIndicatorList.get(i));
            }
        }
        // 选中第0个
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }


    class TabLayoutPagerAdapter extends FragmentPagerAdapter {

        public TabLayoutPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mTabFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mTabIndicatorList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabIndicatorList.get(position);
        }
    }

}
