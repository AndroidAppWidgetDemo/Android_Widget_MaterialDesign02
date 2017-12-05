package com.yifeng.mdstudysamples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yifeng.mdstudysamples.appbar.AppBarLayoutActivity;
import com.yifeng.mdstudysamples.behaviordependent.BehaviorDependentActivity;
import com.yifeng.mdstudysamples.behaviornested.BehaviorNestedActivity;
import com.yifeng.mdstudysamples.behaviornestedexpand.BehaviorNestedExpandActivity;
import com.yifeng.mdstudysamples.bottomnavigation.BottomNavigationActivity;
import com.yifeng.mdstudysamples.fab.FloatingActionButtonActivity;
import com.yifeng.mdstudysamples.snackbar.SnackbarActivity;
import com.yifeng.mdstudysamples.tablayout_bottom.TabLayoutBottomActivity;
import com.yifeng.mdstudysamples.tablayout_top.TabLayoutTopActivity;
import com.yifeng.mdstudysamples.textinput.TextInputActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 不显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * TabLayout 顶部标签页
     *
     * @param v
     */
    public void onClickTabLayoutTop(View v) {
        startActivity(new Intent(this, TabLayoutTopActivity.class));
    }

    /**
     * TabLayout 底部菜单页
     *
     * @param v
     */
    public void onClickTabLayoutBottom(View v) {
        startActivity(new Intent(this, TabLayoutBottomActivity.class));
    }

    /**
     * Snackbar
     *
     * @param v
     */
    public void onClickSnackbar(View v) {
        startActivity(new Intent(this, SnackbarActivity.class));
    }

    /**
     * FloatingActionButton
     *
     * @param v
     */
    public void onClickFab(View v) {
        startActivity(new Intent(this, FloatingActionButtonActivity.class));
    }

    /**
     * AppBarLayoutActivity
     *
     * @param v
     */
    public void onClickAbl(View v) {
        startActivity(new Intent(this, AppBarLayoutActivity.class));
    }

    /**
     * BehaviorDependent
     *
     * @param v
     */
    public void onClickBehaviorDependent(View v) {
        startActivity(new Intent(this, BehaviorDependentActivity.class));
    }

    public void onClickBehaviorNested(View v) {
        startActivity(new Intent(this, BehaviorNestedActivity.class));
    }

    public void onClickBehaviorExpand(View v) {
        startActivity(new Intent(this, BehaviorNestedExpandActivity.class));
    }

    public void onClickTextInput(View v) {
        startActivity(new Intent(this, TextInputActivity.class));
    }

    public void onClickBottomNavigation(View v) {
        startActivity(new Intent(this, BottomNavigationActivity.class));
    }

}
