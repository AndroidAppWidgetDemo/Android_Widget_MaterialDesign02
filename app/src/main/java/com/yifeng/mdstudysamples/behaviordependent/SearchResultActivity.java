package com.yifeng.mdstudysamples.behaviordependent;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yifeng.mdstudysamples.BaseActivity;
import com.yifeng.mdstudysamples.R;
import com.yifeng.mdstudysamples.ToastUtils;

/**
 * Created by lxf on 16/10/28.
 *
 */

public class SearchResultActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);

        mToolbar.setTitle(R.string.search_result);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            ToastUtils.showToast(this, intent.getStringExtra(SearchManager.QUERY));
        }
    }
}
