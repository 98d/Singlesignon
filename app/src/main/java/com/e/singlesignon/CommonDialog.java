package com.e.singlesignon;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 * desc   : 类似IOS的普通对话框
 * params :
 * <pre>
 */

public class CommonDialog extends Dialog {


    private final Context mContext;
    @BindView(R.id.dialog_title)
    TextView     dialogTitle;
    @BindView(R.id.dialog_content)
    TextView     dialogContent;
    @BindView(R.id.dialog_cancel)
    Button       dialogCancel;
    @BindView(R.id.dialog_ensure)
    Button       dialogEnsure;
    @BindView(R.id.ll_button)
    LinearLayout llButton;

    private String title;
    private String content;
    private String cancel;
    private String ensure;

    private View.OnClickListener onCancelClickListener;
    private View.OnClickListener onEnsureClickListener;

    public CommonDialog(@NonNull Context context) {
        super(context, R.style.dialog_untran);
        mContext = context;
        setContentView(R.layout.dialog_common);
        ButterKnife.bind(this);
    }


    /**
     * 确定按钮事件监听 默认是dismiss对话框
     *
     * @param onEnsureClickListener
     */
    public void setOnEnsureClickListener(View.OnClickListener onEnsureClickListener) {
        this.onEnsureClickListener = onEnsureClickListener;
    }

    /**
     * 取消按钮事件监听 默认是dismiss对话框
     *
     * @param onCabcelClickListener
     */
    public void setOnCancelClickListener(View.OnClickListener onCabcelClickListener) {
        this.onCancelClickListener = onCabcelClickListener;
    }

    /**
     * 设置标题 默认没有标题
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置内容    默认为空
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 设置确定按钮内容 默认为确定
     *
     * @param ensure
     */
    public void setEnsure(String ensure) {
        this.ensure = ensure;
    }

    /**
     * 设置取消按钮内容  默认为取消
     *
     * @param cancel
     */
    public void setCancel(String cancel) {
        this.cancel = cancel;
    }


    /**
     * 重写show方法
     */
    @Override
    public void show() {
        if (TextUtils.isEmpty(title)) {
            dialogTitle.setVisibility(View.GONE);
        } else {
            dialogTitle.setVisibility(View.VISIBLE);
            setTextViewTxt(dialogTitle, title);
        }
        if (TextUtils.isEmpty(cancel)) {
            cancel = mContext.getString(R.string.cancel);
        }
        if (TextUtils.isEmpty(ensure)) {
            ensure = mContext.getString(R.string.ensure);
        }
        setTextViewTxt(dialogContent, content);
        setTextViewTxt(dialogCancel, cancel);
        setTextViewTxt(dialogEnsure, ensure);
        setButtonOnClickListener(dialogCancel, onCancelClickListener);
        setButtonOnClickListener(dialogEnsure, onEnsureClickListener);
        super.show();
    }

    /**
     * 按钮点击事件
     * @param textView
     * @param onClickListener
     */
    private void setButtonOnClickListener(TextView textView, View.OnClickListener onClickListener) {
        if (textView == null) {
            return;
        }
        if (onClickListener == null) {
            onClickListener = onClickListenerDismiss;
        }
        textView.setOnClickListener(onClickListener);

    }

    /**
     *默认点击事件，点击弹框消失
     */
    private View.OnClickListener onClickListenerDismiss = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    /**
     * 设置文字
     * @param textView
     * @param string
     */
    private void setTextViewTxt(TextView textView, String string) {
        if (null == textView) {
            return;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        textView.setText(string);
    }
}
