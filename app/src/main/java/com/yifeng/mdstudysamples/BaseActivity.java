package com.yifeng.mdstudysamples;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.yifeng.mdstudysamples.R;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "xiaxl: BaseActivity";

    // 上下文对象
    public Context mContext;
    // toolbar
    public Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        // 上下文对象
        mContext = this;
        // 获取toolbar
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        /**
         * http://blog.csdn.net/lovexieyuan520/article/details/9974929
         */
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            // setHomeButtonEnabled这个小于4.0版本的默认值为true的；但是在4.0及其以上是false。
            // 该方法的作用：决定左上角的图标是否可以点击。
            // 没有向左的小图标。 true 图标可以点击  false 不可以点击。
            getSupportActionBar().setHomeButtonEnabled(true);
            // actionBar.setDisplayHomeAsUpEnabled(true)
            // 给左上角图标的左边加上一个返回的图标 。
            // 对应ActionBar.DISPLAY_HOME_AS_UP
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e(TAG, "---onOptionsItemSelected---");
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.e(TAG, "android.R.id.home");
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        Log.e(TAG, "---onTitleChanged---");
        Log.e(TAG, "title: " + title);
        //
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

}
