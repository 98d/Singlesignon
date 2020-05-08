package com.e.singlesignon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.LinkedList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Project: MyDemo
 * Author:  NRF
 * Version:  1.0
 * Date:    2017/9/17
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static LinkedList<AppCompatActivity> activities;
    private        OtherEquipmentLoginReceiver   receiver;
    private        RelativeLayout                container;
    private        Unbinder                      mButterBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activities == null) {
            activities = new LinkedList<>();
        }
        activities.add(this);

        //注册广播
        receiver = new OtherEquipmentLoginReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("LoginOut");
        registerReceiver(receiver, intentFilter);

        container = new RelativeLayout(this);
        container.addView(LayoutInflater.from(this).inflate(initLayout(), null, false), new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(container, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mButterBind = ButterKnife.bind(this);
        initView();
        initData();
        bindListener();

    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void bindListener();

    private void otherEquipmentLogin() {

        if (activities.getLast().getClass() == this.getClass()) {

            final CommonDialog otherLoginDialog = new CommonDialog(BaseActivity.this);
            otherLoginDialog.setContent("您的账号已在其他设备登录,请重新登录!");
            otherLoginDialog.setTitle("警告");
            otherLoginDialog.setEnsure("重新登录");
            otherLoginDialog.setCancelable(false);
            otherLoginDialog.setCanceledOnTouchOutside(false);
            otherLoginDialog.setOnEnsureClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitApp();
                    //跳转到登录界面
                    startActivity(LoginActivity.class);
                    otherLoginDialog.dismiss();
                }
            });

            otherLoginDialog.setOnCancelClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击取消退出app
                    exitApp();
                    otherLoginDialog.dismiss();
                }
            });
            otherLoginDialog.show();
        }
    }

    /**
     * 在其他设备登录
     */
    public class OtherEquipmentLoginReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if ("LoginOut".equals(intent.getAction())) {
                otherEquipmentLogin();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activities != null) {
            activities.remove(this);
        }
        //解除广播
        unregisterReceiver(receiver);
        mButterBind.unbind();
    }

    /**
     * 彻底退出app
     */
    protected void exitApp() {
        for (AppCompatActivity baseActivity : activities) {
            baseActivity.finish();
        }
        activities.clear();
        activities = null;
    }


    /**
     * 跳转到指定的页面
     *
     * @param clazz
     */
    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
