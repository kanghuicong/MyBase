package com.kang.mybase.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kang.mybase.R;

import static com.kang.utilssdk.BarUtils.addMarginTopEqualStatusBarHeight;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

public class MyTitle extends RelativeLayout {
    RelativeLayout rlTitle;
    ImageView ivBack;
    public TextView tvTitle;
    private Activity activity;

    public MyTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;

        LayoutInflater.from(context).inflate(R.layout.main_title, this, true);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        this.setBackgroundResource(R.color.main_color);
        addMarginTopEqualStatusBarHeight(rlTitle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitle);

        String title = typedArray.getString(R.styleable.MyTitle_title);
        tvTitle.setText(title);

        boolean isBack = typedArray.getBoolean(R.styleable.MyTitle_isBack, true);
        if (isBack) ivBack.setVisibility(View.VISIBLE);
        else ivBack.setVisibility(View.GONE);

        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        typedArray.recycle();
    }

}
