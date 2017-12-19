package com.kang.mybase.custom;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.pro.IDialogBottom;

import java.util.List;

/**
 * Created by KangHuiCong on 2017/12/18.
 * E-Mail is 515849594@qq.com
 */

public class MyBottomDialog extends LinearLayout {
    private Context context;
    private List<String> list;
    private IDialogBottom iDialogBottom;
    private Activity activity;
    private Dialog dialog;
    private boolean type = true;

    //传入list列表内容，在IDialogBottom接口回调里写对应点击事件
    public MyBottomDialog(Context context, List<String> list, IDialogBottom iDialogBottom) {
        super(context);
        this.context = context;
        this.list = list;
        this.iDialogBottom = iDialogBottom;
        this.activity = (Activity) context;
        init();
    }

    public MyBottomDialog(Context context, List<String> list, boolean type,IDialogBottom iDialogBottom) {
        super(context);
        this.context = context;
        this.list = list;
        this.iDialogBottom = iDialogBottom;
        this.activity = (Activity) context;
        this.type = type;
        init();
    }

    public void init() {
        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.setBackgroundResource(R.color.Transparent);
        this.setGravity(Gravity.BOTTOM);
        this.setOrientation(VERTICAL);
        this.setPadding(10, 10, 10, 10);
        dialog = new Dialog(context,R.style.dialogBackground);

        TextView line = new TextView(context);
        line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        line.setBackgroundResource(R.color.ViewLine);

        switch (list.size()) {
            case 1:
                TextView tvNormal = new TextView(context);
                setTextStyle(tvNormal,R.drawable.dialog_normal,0,null);
                this.addView(tvNormal);
                break;
            default:
                TextView tvTop = new TextView(context);
                setTextStyle(tvTop,R.drawable.dialog_top,0,null);

                TextView tvBottom = new TextView(context);
                setTextStyle(tvBottom,R.drawable.dialog_bottom,list.size()-1,null);

                if(list.size()==2){
                    this.addView(tvTop);
                    this.addView(line);
                    this.addView(tvBottom);
                }else {
                    this.addView(tvTop);
                    this.addView(line);
                    for (int i=1;i<list.size()-1;i++) {
                        TextView tvCenter = new TextView(context);
                        setTextStyle(tvCenter,R.drawable.dialog_center,i,null);
                        this.addView(tvCenter);
                        TextView lineCenter = new TextView(context);
                        lineCenter.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                        lineCenter.setBackgroundResource(R.color.ViewLine);
                        this.addView(lineCenter);
                    }
                    this.addView(tvBottom);
                }
                break;
        }

        if (type) {
            TextView tvCancel = new TextView(context);
            LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 20, 0, 0);
            setTextStyle(tvCancel, R.drawable.dialog_normal, -1, params);

            tvCancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            this.addView(tvCancel);
        }

        dialog.setContentView(this, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();

        window.setWindowAnimations(R.style.dialogAnim);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);

        dialog.setCanceledOnTouchOutside(true);// 设置点击外围解散
        dialog.show();
    }

    private void setTextStyle(TextView view,int background,int position,LayoutParams params) {
        if (params == null) view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        else view.setLayoutParams(params);
        view.setBackgroundResource(background);
        if (position == -1)view.setText("取消");
        else view.setText(list.get(position));
        view.setPadding(0,30,0,30);
        view.setOnClickListener(new Click(position));
        view.setGravity(Gravity.CENTER);
    }

    private class Click implements OnClickListener {
        int i;
        public Click(int i) {this.i = i;}

        @Override
        public void onClick(View view) {
            if (i!=-1) iDialogBottom.iDialog(i);
            dialog.dismiss();
        }
    }
}
