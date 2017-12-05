package com.yifeng.mdstudysamples.textinput;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.yifeng.mdstudysamples.BaseActivity;
import com.yifeng.mdstudysamples.R;
import com.yifeng.mdstudysamples.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextInputActivity extends BaseActivity {

    private TextInputLayout mMobileTil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_input_activity);

        mMobileTil = (TextInputLayout) findViewById(R.id.til_mobile);

    }

    public void onClickLogin(View v) {
        if (verifyMobile()) {
            mMobileTil.setErrorEnabled(false);
            ToastUtils.showToast(this, "Success");
        } else {
            mMobileTil.setErrorEnabled(true);
            mMobileTil.setError("手机号格式错误");
        }

    }

    public boolean verifyMobile() {
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(((EditText) findViewById(R.id.et_mobile)).getText().toString());
        return matcher.matches();
    }

}
