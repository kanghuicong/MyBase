package com.kang.mybase.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kang.mybase.R;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public class MySuperItem extends LinearLayout {
    int defaultColor = Color.BLACK;
    int defaultSize = 14;
    int defaultDrawablePadding = 5;

    /*左边文字---左边图片*/
    private Drawable mLeftImageL;
    private int mLeftImageLWidth;
    private int mLeftImageLHeight;
    /*左边文字*/
    private String mLeftText;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftDrawablePadding;
    /*左边文字---右边图片*/
    private Drawable mLeftImageR;
    private int mLeftImageRWidth;
    private int mLeftImageRHeight;
    /*右边文字---左边图片*/
    private Drawable mRightImageL;
    private int mRightImageLWidth;
    private int mRightImageLHeight;
    /*右边文字*/
    private String mRightText;
    private int mRightTextColor;
    private int mRightTextSize;
    private int mRightDrawablePadding;
    /*右边文字---右边图片*/
    private Drawable mRightImageR;
    private int mRightImageRWidth;
    private int mRightImageRHeight;
    private boolean isArrow;

/*---------------------------------------------------------------------------------------------------*/

    private Context context;

    private ImageView leftImageL;
    private TextView leftText;
    private ImageView rightImageL;
    private TextView rightText;

    private TextView topLeftText;
    private TextView topRightText;
    private TextView bottomLeftText;
    private TextView bottomRightText;

    public MySuperItem(Context context) {
        this(context, null);
    }

    public MySuperItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySuperItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(attrs);
        init();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySuperItem);
        /*左边文字---左边图片*/
        mLeftImageL = typedArray.getDrawable(R.styleable.MySuperItem_left_limg);
        mLeftImageLWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_width, LayoutParams.WRAP_CONTENT);
        mLeftImageLHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_height, LayoutParams.WRAP_CONTENT);
        /*左边文字*/
        mLeftText = typedArray.getString(R.styleable.MySuperItem_left_text);
        mLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_left_text_color, defaultColor);
        mLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_size, defaultSize);
        mLeftDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_drawable_padding, defaultDrawablePadding);
        /*左边文字---右边图片*/
        mLeftImageR = typedArray.getDrawable(R.styleable.MySuperItem_left_rimg);
        mLeftImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_width, -1);
        mLeftImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_height, -1);
        /*右边文字---左边图片*/
        mRightImageL = typedArray.getDrawable(R.styleable.MySuperItem_right_limg);
        mRightImageLWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_width, -1);
        mRightImageLHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_height, -1);
        /*右边文字*/
        mRightText = typedArray.getString(R.styleable.MySuperItem_right_text);
        mRightTextColor = typedArray.getColor(R.styleable.MySuperItem_right_text_color, defaultColor);
        mRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_size, defaultSize);
        mRightDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_drawable_padding, defaultDrawablePadding);
        /*左边文字---右边图片*/
        mRightImageR = typedArray.getDrawable(R.styleable.MySuperItem_right_rimg);
        mRightImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_width, -1);
        mRightImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_height, -1);
        isArrow = typedArray.getBoolean(R.styleable.MySuperItem_isArrow, false);

        typedArray.recycle();
    }

    private void init() {
        this.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        this.setOrientation(HORIZONTAL);
        this.setBackgroundResource(R.color.LitterGrey);
        this.setGravity(Gravity.CENTER);

        initLeftImage();
        initLeftText();
        initCenter();
        initRightImage();
        initRightText();
    }

/*-----------------------------------------初始化布局------------------------------------------------*/
    /*左边文字---左边图片*/
    private void initLeftImage() {
        leftImageL = new ImageView(context);
        initImage(leftImageL,mLeftImageLWidth,mLeftImageLHeight,mLeftImageL);
    }
    /*左边文字*/
    private void initLeftText() {
        leftText = new TextView(context);
        initText(leftText, mLeftText, mLeftTextSize, mLeftTextColor, mLeftImageR, mLeftImageRWidth, mLeftImageRHeight, mLeftDrawablePadding);
    }

    private void initCenter() {
        LinearLayout centerLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
        centerLayout.setLayoutParams(lp);
        centerLayout.setOrientation(VERTICAL);

        LinearLayout topLayout = new LinearLayout(context);
        initLayout(topLayout,Gravity.CENTER);
        topLeftText = new TextView(context);
        topRightText = new TextView(context);

        topLayout.addView(topLeftText);
        topLayout.addView(topRightText);
        centerLayout.addView(topLayout);

        LinearLayout bottomLayout = new LinearLayout(context);
        initLayout(bottomLayout,Gravity.CENTER);
        bottomLeftText = new TextView(context);
        bottomRightText = new TextView(context);

        bottomLayout.addView(bottomLeftText);
        bottomLayout.addView(bottomRightText);
        centerLayout.addView(bottomLayout);
        addView(centerLayout);
    }
    /*右边文字---左边图片*/
    private void initRightImage() {
        rightImageL = new ImageView(context);
        initImage(rightImageL,mRightImageLWidth,mRightImageLHeight,mRightImageL);
    }
    /*右边文字*/
    private void initRightText() {
        rightText = new TextView(context);
        if (!isArrow)
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, mRightImageR, mRightImageRWidth, mRightImageRHeight, mRightDrawablePadding);
        else initText(rightText, mRightText, mRightTextSize, mRightTextColor, context.getResources().getDrawable(R.mipmap.arrow_right), 30, 60, 20);
    }

/*-------------------------------------------公共方法----------------------------------------------*/

    public void initLayout(LinearLayout layout,int gravity) {
        layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(gravity);

    }

    public void initText(TextView textView,String content,int textSize,int textColor,Drawable drawable,int drawableWidth,int drawableHight,int drawablePadding) {
        textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(content);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);

        if (drawable != null) {
            if (drawableWidth != -1 && drawableHight != -1) {
                drawable.setBounds(0, 0, drawableWidth, drawableHight);
                textView.setCompoundDrawables(null, null, drawable, null);
            } else textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
        textView.setCompoundDrawablePadding(drawablePadding);
        addView(textView);
    }

    public void initImage(ImageView imageView,int imageWidth,int imageHeight,Drawable drawable) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        lp.setMargins(0,0,50,0);
        imageView.setLayoutParams(lp);
        imageView.setBackgroundDrawable(drawable);
        addView(imageView);
        if (drawable == null) imageView.setVisibility(GONE);
    }



/*-------------------------------------------动态修改-----------------------------------------------*/
    /*左边文字---左边图片*/
    public MySuperItem setLeftImageL(String url) {
        leftImageL.setVisibility(VISIBLE);
        Glide.with(context).load(url).into(leftImageL);
        return this;
    }
    /*左边文字*/
    public MySuperItem setLeftText(String mLeftText) {
        leftText.setText(mLeftText);
        return this;
    }
    /*右边文字---左边图片*/
    public MySuperItem setRightImageL(String url) {
        rightImageL.setVisibility(VISIBLE);
        Glide.with(context).load(url).into(rightImageL);
        return this;
    }
    /*右边文字*/
    public MySuperItem setRightText(String mRightText) {
        rightText.setText(mRightText);
        return this;
    }

}
//    Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.note_play);
//    ImageSpan imgSpan = new ImageSpan(context,bitmap);
//    SpannableString spanString = new SpannableString("icon");
//        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                vh.outline1.append(spanString);
//                vh.outline2.append(spanString);