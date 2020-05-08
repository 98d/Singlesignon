package com.e.singlesignon;

import android.content.Intent;
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
public class ThirdActivity extends BaseActivity {
    @BindView(R.id.show)
    Button show;

    @Override
    protected int initLayout() {
        return R.layout.third;
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


    @OnClick(R.id.show)
    public void onClick() {
        Intent intent = new Intent();
        intent.setAction("LoginOut");
        sendBroadcast(intent);
    }
}
