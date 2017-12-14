package com.kang.mybase.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.mybase.R;

import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

public class MyItem extends LinearLayout {

    @InjectView(R.id.tv_item)
    TextView tvItem;
    @InjectView(R.id.tv_left)
    TextView tvLeft;

    public MyItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.main_item, this, true);
        this.setPadding(20, 20, 20, 20);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundColor(Color.WHITE);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItem);

        String itemName = typedArray.getString(R.styleable.MyItem_item_name);
        tvItem.setText(itemName);

        String leftText = typedArray.getString(R.styleable.MyItem_left_text);
        tvLeft.setText(leftText);

        typedArray.recycle();
    }
}
