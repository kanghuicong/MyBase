package com.kang.mybase.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.pro.IDialog;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class MyDialog extends DialogFragment {

    String title, content, left, right;
    IDialog iDialog;
    int type ;
    boolean isFinish = false;
    boolean isLoading = false;
    MyLoading myLoading;

    public MyDialog() {
        this.isLoading = true;
        type = 1;
    }

    public MyDialog(String title, String content) {
        this.title = title;
        this.content = content;
        type = 0;
    }

    public MyDialog(String title, String right, String content, IDialog iDialog) {
        this.title = title;
        this.content = content;
        this.right = right;
        type = 1;
    }

    public MyDialog(String title, String content, String left, String right, IDialog iDialog) {
        this.title = title;
        this.content = content;
        this.left = left;
        this.right = right;
        this.iDialog = iDialog;
        type = 2;
    }

    public MyDialog isFinish(boolean isFinish) {
        this.isFinish = isFinish;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*含有按钮时，点击屏幕Dialog不消失*/
        if (type!=0) this.setCancelable(false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (isLoading) {
            LinearLayout view = new LinearLayout(getActivity());
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.setOrientation(LinearLayout.HORIZONTAL);
            view.setPadding(10, 30, 10, 30);
            view.setGravity(Gravity.CENTER);

            myLoading = new MyLoading(getActivity());
            myLoading.setLayoutParams(new LinearLayout.LayoutParams(60, 60));
            myLoading.startAnimator();
            view.addView(myLoading);

            TextView textView = new TextView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(30,0,0,0);
            textView.setLayoutParams(lp);
            textView.setText("正在加载数据...");

            view.addView(textView);

            builder.setView(view);
            builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        myLoading.stopAnimator();
                    }
                    return false;
                }
            });
        } else {
            LinearLayout view = new LinearLayout(getActivity());
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.setOrientation(LinearLayout.VERTICAL);
            view.setPadding(10, 20, 10, 10);
            view.setGravity(Gravity.CENTER);

            TextView tvTitle = new TextView(getActivity());
            tvTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tvTitle.setTextSize(18);
            view.addView(tvTitle);

            TextView tvContent = new TextView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20,20,20,10);
            tvContent.setLayoutParams(lp);
            tvContent.setTextSize(16);
            view.addView(tvContent);

            tvTitle.setText(title);
            tvContent.setText(content);

            switch (type) {
                case 0:/*不含按钮*/
                    builder.setView(view).setPositiveButton(right, null).setNegativeButton(left, null);
                    break;
                case 1:/*只有一个按钮*/
                    builder.setView(view).setPositiveButton(right, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            iDialog.rightClick(dialog);
                        }
                    }).setNegativeButton(left, null);
                    break;
                case 2:/*两个按钮*/
                    builder.setView(view).setPositiveButton(right, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            iDialog.rightClick(dialog);
                        }
                    }).setNegativeButton(left, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            iDialog.leftClick(dialog);
                        }
                    });
                    builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_BACK) {
                                /*返回键关闭activity*/
                                if (isFinish) getActivity().finish();
                                else return true;
                            }
                            return false;
                        }
                    });
                    break;
            }
        }
        return builder.create();
    }

    public void stopAnimator() {
        if (myLoading!=null)myLoading.stopAnimator();
    }
}
