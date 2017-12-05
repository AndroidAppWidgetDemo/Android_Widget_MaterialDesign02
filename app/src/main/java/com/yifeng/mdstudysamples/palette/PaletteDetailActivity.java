package com.yifeng.mdstudysamples.palette;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.yifeng.mdstudysamples.BaseActivity;
import com.yifeng.mdstudysamples.R;
import com.yifeng.mdstudysamples.ToolbarColorizeHelper;


public class PaletteDetailActivity extends BaseActivity {

    public static final String EXTRA_INDEX = "mIndex";

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageView;
    private ScrollView mScrollView;

    /**
     * 数据
     */
    int mIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //
        setContentView(R.layout.palette_detail_activity);
        //
        initData();
        //
        initView();
        //
        palette(getResources().getIdentifier("ic_palette_0" + mIndex % 4, "mipmap", getPackageName()));
    }

    private void initData() {
        mIndex = getIntent().getIntExtra(EXTRA_INDEX, 0);
    }

    private void initView() {
        //----------
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctl_toolbar);
        //----------
        mImageView = (ImageView) findViewById(R.id.iv_header);
        mImageView.setImageResource(getResources().getIdentifier("ic_palette_0" + mIndex % 4, "mipmap", getPackageName()));
        //----------
        mScrollView = (ScrollView) findViewById(R.id.sv_content);
        //---------
        ViewCompat.setNestedScrollingEnabled(mScrollView, true);
    }

    /**
     * 应用调色板“调色板”
     *
     * @param res
     */
    private void palette(int res) {
        Palette.from(BitmapFactory.decodeResource(getResources(), res))
                .generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        // 返回从调色板中占主导地位的样本的颜色，为RGB包装INT。
                        int color = palette.getDominantColor(ContextCompat.getColor(mContext, R.color.blue));
                        //获取柔和的黑
                        int colorDark = palette.getDarkMutedColor(color);
                        //返回从调色板中占主导地位的样本。
                        int titleTextColor = palette.getDominantSwatch().getTitleTextColor();

                        //----------
                        mCollapsingToolbarLayout.setContentScrimColor(color);
                        // 状态栏的背景
                        mCollapsingToolbarLayout.setStatusBarScrimColor(colorDark);
                        // 收缩后在Toolbar上显示时的title的颜色
                        mCollapsingToolbarLayout.setCollapsedTitleTextColor(titleTextColor);
                        // 扩张时候的title颜色
                        mCollapsingToolbarLayout.setExpandedTitleColor(titleTextColor);
                        //
                        ToolbarColorizeHelper.colorizeToolbar(mToolbar, titleTextColor, PaletteDetailActivity.this);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_palette_detail, menu);
        return true;
    }

}
