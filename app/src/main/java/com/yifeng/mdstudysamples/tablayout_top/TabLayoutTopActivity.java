package com.yifeng.mdstudysamples.tablayout_top;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.yifeng.mdstudysamples.BaseActivity;
import com.yifeng.mdstudysamples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout 顶部标签页
 */
public class TabLayoutTopActivity extends BaseActivity {

    /**
     *
     */
    //
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
        setContentView(R.layout.tablayout_top_activity);

        // 初始化数据
        initData();
        // 初始化View
        initView();
    }

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

    private void initView() {


        //---------ViewPager---------
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mTabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabLayoutPagerAdapter);

        //--------TabLayout-------
        mTabLayout = (TabLayout) findViewById(R.id.tl_tab);
        // mode
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 选中状态与非选中状态
        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.white));
        // 当前选中的颜色
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
        // 设置阴影
        ViewCompat.setElevation(mTabLayout, 10);
        // 关联ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }


    /**
     * 加载标题栏的menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_add:
                // 添加一条tab数据
                mTabIndicatorList.add("Tab " + mTabIndicatorList.size());
                // 添加一个新的Fragment
                mTabFragmentList.add(TabLayoutFragment.newInstance(mTabIndicatorList.get(mTabIndicatorList.size() - 1)));
                // 更新UI
                mTabLayoutPagerAdapter.notifyDataSetChanged();
                // tablayout与ViewPager绑定
                mTabLayout.setupWithViewPager(mViewPager);
                return true;

            case R.id.tab_mode_fixed:
                // 模式更改
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                return true;

            case R.id.tab_mode_scrollable:
                // 模式更改
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                break;
        }
        return super.onOptionsItemSelected(item);
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
