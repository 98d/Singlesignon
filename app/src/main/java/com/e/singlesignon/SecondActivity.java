package com.e.singlesignon;

import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 * Project    : MyDemo
 * Author     : NRF
 * Version    : 1.0
 * Date       : 2017/9/18
 * Modify     : //TODO
 * Description: //TODO
 * Copyright  :
 * </pre>
 */
public class SecondActivity extends BaseActivity {
    @BindView(R.id.go)
    Button go;

    @Override
    protected int initLayout() {
        return R.layout.activity_second;
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

    @OnClick(R.id.go)
    public void onClick() {
        startActivity(ThirdActivity.class);
    }
}
