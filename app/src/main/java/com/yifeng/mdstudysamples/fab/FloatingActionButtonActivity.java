package com.yifeng.mdstudysamples.fab;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.yifeng.mdstudysamples.BaseActivity;
import com.yifeng.mdstudysamples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FloatingActionButton
 */
public class FloatingActionButtonActivity extends BaseActivity {

    /**
     *
     */
    //
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    //
    private DrawerLayout mRootDrawerLayout;
    //
    private LinearLayout mMenuLinearLayout;
    //
    private TabLayout mTabLayout;
    //
    private ViewPager mViewPager;
    private FABPagerAdapter mFABPagerAdapter;

    /**
     * 数据
     */
    private List<String> mTabIndicatorList;
    private List<Fragment> mTabFragmentList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fab_activity);

        // 初始化数据
        initData();
        // 初始化View
        initView();
    }

    /**
     * 加载menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fab_menu, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mTabIndicatorList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mTabIndicatorList.add("Tab " + i);
        }
        mTabFragmentList = new ArrayList<>();
        for (String s : mTabIndicatorList) {
            mTabFragmentList.add(TabFragment.newInstance(s));
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        //
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //---------------
        // 抽屉组建
        mRootDrawerLayout = (DrawerLayout) findViewById(R.id.dl_root);
        //--------------
        //  ActionBarDrawerToggle 使用小结
        // http://blog.csdn.net/chencehnggq/article/details/21492417
        // 1.改变android.R.id.home返回图标。
        // 2.Drawer拉出、隐藏，带有android.R.id.home动画效果。
        // 3.监听Drawer拉出、隐藏；
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mRootDrawerLayout, R.string.app_name, R.string.app_name);
        //
        // 关联mActionBarDrawerToggle
        mRootDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        //---------------
        //可滑出面板
        mMenuLinearLayout = (LinearLayout) findViewById(R.id.ll_menu);
        // 设置为屏幕宽的3/4
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) mMenuLinearLayout.getLayoutParams();
        layoutParams.width = getScreenSize()[0] / 4 * 3;
        //-----------------
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mFABPagerAdapter = new FABPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFABPagerAdapter);

        // -----------------
        mTabLayout = (TabLayout) findViewById(R.id.tl_indicator);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.white));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
        ViewCompat.setElevation(mTabLayout, 10);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 悬浮按钮的点击事件
     *
     * @param v
     */
    public void onClickFab(View v) {
        Snackbar.make(findViewById(R.id.fab_add), "Show The Snackbar", Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    public int[] getScreenSize() {
        int screenSize[] = new int[2];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenSize[0] = displayMetrics.widthPixels;
        screenSize[1] = displayMetrics.heightPixels;
        return screenSize;
    }


    /**
     * ViewPager
     */
    class FABPagerAdapter extends FragmentPagerAdapter {

        public FABPagerAdapter(FragmentManager fm) {
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
