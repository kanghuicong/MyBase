package com.kang.mybase.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.pro.IDialog;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

//自定义dialog
public class MyDialog extends DialogFragment {

    String title, content, left, right;
    IDialog iDialog;
    int type ;
    TextView tvTitle;
    TextView tvContent;
    boolean isFinish = false;

    public MyDialog(String title, String content, String left, String right, boolean isFinish, IDialog iDialog) {
        this.title = title;
        this.content = content;
        this.left = left;
        this.right = right;
        this.iDialog = iDialog;
        this.isFinish = isFinish;
        type = 2;
    }

    public MyDialog(String title, String right, String content, IDialog iDialog) {
        this.title = title;
        this.content = content;
        this.right = right;
        type = 1;
    }

    public MyDialog(String title, String content) {
        this.title = title;
        this.content = content;
        type = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (type!=0) {
            //含有按钮时，点击屏幕Dialog不消失
            this.setCancelable(false);// 设置点击屏幕Dialog不消失
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_main, null);

        tvTitle   = (TextView) view.findViewById(R.id.tvTitle);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        tvTitle.setText(title);
        tvContent.setText(content);

        switch (type) {
            case 0:
                //不含按钮
                builder.setView(view).setPositiveButton(right, null).setNegativeButton(left, null);
                break;
            case 1:
                //只有一个按钮
                builder.setView(view).setPositiveButton(right, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iDialog.rightClick(dialog);
                    }
                }).setNegativeButton(left, null);
                break;
            case 2:
                //两个按钮
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
                            if (isFinish){
                                //SplashActivity权限弹框，返回键关闭activity
                                getActivity().finish();
                            }else {
                                return true;
                            }
                        }
                        return false;
                    }
                });
                break;
        }
        return builder.create();

    }
}
