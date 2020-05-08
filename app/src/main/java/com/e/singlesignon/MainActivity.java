package com.e.singlesignon;

import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.GO)
    Button GO;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindListener() {

    }

    @OnClick(R.id.GO)
    public void onClick() {
        startActivity(SecondActivity.class);
    }
}
