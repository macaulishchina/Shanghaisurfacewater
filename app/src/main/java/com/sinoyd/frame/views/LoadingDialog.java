package com.sinoyd.frame.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoyd.R;

/**
 * 作者： 王一凡
 * 创建时间： 2017/9/5
 * 版权： 江苏远大信息股份有限公司
 * 描述：
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }


    public void onWindowFocusChanged(boolean hasFocus){
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        if(message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView)findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    public static LoadingDialog show(Context context, CharSequence message, boolean indeterminate, boolean cancelable,
                                   DialogInterface.OnCancelListener cancelListener) {
        LoadingDialog dialog = new LoadingDialog(context,R.style.ProgressHUD);
        dialog.setTitle("");
        dialog.setContentView(R.layout.frmprogress);
        if(message == null || message.length() == 0) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView)dialog.findViewById(R.id.message);
            txt.setText(message);
        }
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.getWindow().getAttributes().gravity= Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount=0.2f;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        return dialog;
    }
}
