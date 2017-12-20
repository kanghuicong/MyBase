package com.kang.mybase.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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

    /*left--left图片*/
    private Drawable mLeftImageL;
    private int mLeftImageLWidth;
    private int mLeftImageLHeight;
    /*left文字*/
    private String mLeftText;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftDrawablePadding;
    /*left--right图片*/
    private Drawable mLeftImageR;
    private int mLeftImageRWidth;
    private int mLeftImageRHeight;
    /*right--left图片*/
    private Drawable mRightImageL;
    private int mRightImageLWidth;
    private int mRightImageLHeight;
    /*right文字*/
    private String mRightText;
    private int mRightTextColor;
    private int mRightTextSize;
    private int mRightDrawablePadding;
    /*right--right图片*/
    private Drawable mRightImageR;
    private int mRightImageRWidth;
    private int mRightImageRHeight;
    private boolean isArrow;
    /*top*/
    private int mTopGravity;
    /*top--left文字*/
    private String mTopLeftText;
    private int mTopLeftTextSize;
    private int mTopLeftTextColor;
    /*top--right文字*/
    private String mTopRightText;
    private int mTopRightTextSize;
    private int mTopRightTextColor;
    /*bottom*/
    private int mBottomGravity;
    /*bottom--left文字*/
    private String mBottomLeftText;
    private int mBottomLeftTextSize;
    private int mBottomLeftTextColor;
    /*bottom--right文字*/
    private String mBottomRightText;
    private int mBottomRightTextSize;
    private int mBottomRightTextColor;

/*---------------------------------------------------------------------------------------------------*/

    private Context context;

    private ImageView leftImageL;
    private TextView leftText;
    private ImageView rightImageL;
    private TextView rightText;

    private LinearLayout topLayout;
    private TextView topLeftText;
    private TextView topRightText;

    private LinearLayout bottomLayout;
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
        /*left--left图片*/
        mLeftImageL = typedArray.getDrawable(R.styleable.MySuperItem_left_limg);
        mLeftImageLWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_width, LayoutParams.WRAP_CONTENT);
        mLeftImageLHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_height, LayoutParams.WRAP_CONTENT);
        /*left文字*/
        mLeftText = typedArray.getString(R.styleable.MySuperItem_left_text);
        mLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_left_text_color, defaultColor);
        mLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_size, defaultSize);
        mLeftDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_drawable_padding, defaultDrawablePadding);
        /*left--right图片*/
        mLeftImageR = typedArray.getDrawable(R.styleable.MySuperItem_left_rimg);
        mLeftImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_width, -1);
        mLeftImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_height, -1);
        /*right--left图片*/
        mRightImageL = typedArray.getDrawable(R.styleable.MySuperItem_right_limg);
        mRightImageLWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_width, -1);
        mRightImageLHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_height, -1);
        /*right文字*/
        mRightText = typedArray.getString(R.styleable.MySuperItem_right_text);
        mRightTextColor = typedArray.getColor(R.styleable.MySuperItem_right_text_color, defaultColor);
        mRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_size, defaultSize);
        mRightDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_drawable_padding, defaultDrawablePadding);
        /*right--right图片*/
        mRightImageR = typedArray.getDrawable(R.styleable.MySuperItem_right_rimg);
        mRightImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_width, -1);
        mRightImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_height, -1);
        isArrow = typedArray.getBoolean(R.styleable.MySuperItem_isArrow, false);
        /*top*/
        mTopGravity = typedArray.getInteger(R.styleable.MySuperItem_top_gravity, Gravity.LEFT);
        /*top--left文字*/
        mTopLeftText = typedArray.getString(R.styleable.MySuperItem_top_left_text);
        mTopLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_left_textSize, defaultSize);
        mTopLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_top_left_textColor, defaultColor);
        /*top--right文字*/
        mTopRightText = typedArray.getString(R.styleable.MySuperItem_top_right_text);
        mTopRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_right_textSize, defaultSize);
        mTopRightTextColor = typedArray.getColor(R.styleable.MySuperItem_top_right_textColor, defaultColor);
        /*bottom*/
        mBottomGravity = typedArray.getInteger(R.styleable.MySuperItem_bottom_gravity, Gravity.LEFT);
        /*bottom--left文字*/
        mBottomLeftText = typedArray.getString(R.styleable.MySuperItem_bottom_left_text);
        mBottomLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_left_textSize, defaultSize);
        mBottomLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_bottom_left_textColor, defaultColor);
        /*bottom--right文字*/
        mBottomRightText = typedArray.getString(R.styleable.MySuperItem_bottom_right_text);
        mBottomRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_right_textSize, defaultSize);
        mBottomRightTextColor = typedArray.getColor(R.styleable.MySuperItem_bottom_right_textColor, defaultColor);

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
        initImage(leftImageL, mLeftImageLWidth, mLeftImageLHeight, mLeftImageL);
    }

    /*左边文字*/
    private void initLeftText() {
        leftText = new TextView(context);
        initText(leftText, mLeftText, mLeftTextSize, mLeftTextColor, mLeftImageR, mLeftImageRWidth, mLeftImageRHeight, mLeftDrawablePadding,20);
    }

    /*中间布局*/
    private void initCenter() {
        LinearLayout centerLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
        centerLayout.setLayoutParams(lp);
        centerLayout.setOrientation(VERTICAL);
        centerLayout.setGravity(Gravity.CENTER);

        /*top*/
        topLayout = new LinearLayout(context);
        initCenterLayout(topLayout, mTopGravity);
        topLeftText = new TextView(context);
        initCenterText(topLeftText, mTopLeftText, mTopLeftTextSize, mTopLeftTextColor);
        topRightText = new TextView(context);
        initCenterText(topRightText, mTopRightText, mTopRightTextSize, mTopRightTextColor);

        topLayout.addView(topLeftText);
        topLayout.addView(topRightText);
        centerLayout.addView(topLayout);

        /*bottom*/
        bottomLayout = new LinearLayout(context);
        initCenterLayout(bottomLayout, mBottomGravity);
        if (mBottomLeftText==null && mBottomRightText==null)bottomLayout.setVisibility(GONE);
        bottomLeftText = new TextView(context);
        initCenterText(bottomLeftText, mBottomLeftText, mBottomLeftTextSize, mBottomLeftTextColor);
        bottomRightText = new TextView(context);
        initCenterText(bottomRightText, mBottomRightText, mBottomRightTextSize, mBottomRightTextColor);

        bottomLayout.addView(bottomLeftText);
        bottomLayout.addView(bottomRightText);
        centerLayout.addView(bottomLayout);

        addView(centerLayout);
    }

    /*右边文字---左边图片*/
    private void initRightImage() {
        rightImageL = new ImageView(context);
        initImage(rightImageL, mRightImageLWidth, mRightImageLHeight, mRightImageL);
    }

    /*右边文字*/
    private void initRightText() {
        rightText = new TextView(context);
        if (!isArrow)
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, mRightImageR, mRightImageRWidth, mRightImageRHeight, mRightDrawablePadding,0);
        else
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, context.getResources().getDrawable(R.mipmap.arrow_right), 30, 60, 20, 0);
    }

/*-------------------------------------------公共方法----------------------------------------------*/

    public void initCenterLayout(LinearLayout layout, int gravity) {
        layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(gravity);
    }

    public void initCenterText(TextView textView, String content, int size, int color) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 20, 0);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
    }

    public void initText(TextView textView, String content, int textSize, int textColor, Drawable drawable, int drawableWidth, int drawableHight, int drawablePadding,int rightMargin) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, rightMargin, 0);
        textView.setLayoutParams(lp);
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

    public void initImage(ImageView imageView, int imageWidth, int imageHeight, Drawable drawable) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        lp.setMargins(0, 0, 50, 0);
        imageView.setLayoutParams(lp);
        imageView.setBackgroundDrawable(drawable);
        addView(imageView);
        if (drawable == null) imageView.setVisibility(GONE);
    }

    /*----------------------------------------------动态修改----------------------------------------*/
    /*-------------------------------------------left-----------------------------------------------*/
    /*left--left图片*/
    public MySuperItem setLeftImageL(String url) {
        leftImageL.setVisibility(VISIBLE);
        Glide.with(context).load(url).into(leftImageL);
        return this;
    }

    /*left文字content*/
    public MySuperItem setLeftText(String mLeftText) {
        leftText.setText(mLeftText);
        return this;
    }

    /*left文字size*/
    public MySuperItem setLeftTextSize(int size) {
        leftText.setTextSize(size);
        return this;
    }

    /*left文字color*/
    public MySuperItem setLeftTextColor(int color) {
        leftText.setTextColor(color);
        return this;
    }

    /*-------------------------------------------top-----------------------------------------------*/
    public MySuperItem setTopGravity(int gravity) {
        topLayout.setGravity(gravity);
        return this;
    }

    /*top--left文字content*/
    public MySuperItem setTopLeftText(String content) {
        topLeftText.setText(content);
        return this;
    }

    /*top--left文字size*/
    public MySuperItem setTopLeftTextSize(int size) {
        topLeftText.setTextSize(size);
        return this;
    }

    /*top--left文字color*/
    public MySuperItem setTopLeftTextColor(int color) {
        topLeftText.setTextColor(color);
        return this;
    }

    /*top--right文字content*/
    public MySuperItem setTopRightText(String content) {
        topRightText.setText(content);
        return this;
    }

    /*top--right文字size*/
    public MySuperItem setTopRightTextSize(int size) {
        topRightText.setTextSize(size);
        return this;
    }

    /*top--right文字color*/
    public MySuperItem setTopRightTextColor(int color) {
        topRightText.setTextColor(color);
        return this;
    }

    /*-------------------------------------------bottom-----------------------------------------------*/
    public MySuperItem setBottomGravity(int gravity) {
        bottomLayout.setGravity(gravity);
        return this;
    }

    /*bottom--left文字content*/
    public MySuperItem setBottomLeftText(String content) {
        bottomLayout.setVisibility(VISIBLE);
        bottomLeftText.setText(content);
        return this;
    }

    /*top--left文字size*/
    public MySuperItem setBottomLeftTextSize(int size) {
        bottomLeftText.setTextSize(size);
        return this;
    }

    /*top--left文字color*/
    public MySuperItem setBottomLeftTextColor(int color) {
        bottomLeftText.setTextColor(color);
        return this;
    }

    /*top--right文字content*/
    public MySuperItem setBottomRightText(String content) {
        bottomLayout.setVisibility(VISIBLE);
        bottomRightText.setText(content);
        return this;
    }

    /*top--right文字size*/
    public MySuperItem setBottomRightTextSize(int size) {
        bottomRightText.setTextSize(size);
        return this;
    }

    /*top--right文字color*/
    public MySuperItem setBottomRightTextColor(int color) {
        bottomRightText.setTextColor(color);
        return this;
    }

    /*-------------------------------------------right-----------------------------------------------*/
    /*right---left图片*/
    public MySuperItem setRightImageL(String url) {
        rightImageL.setVisibility(VISIBLE);
        Glide.with(context).load(url).into(rightImageL);
        return this;
    }

    /*right文字content*/
    public MySuperItem setRightText(String mRightText) {
        rightText.setText(mRightText);
        return this;
    }

    /*right文字size*/
    public MySuperItem setRightTextSize(int size) {
        rightText.setTextSize(size);
        return this;
    }

    /*right文字size*/
    public MySuperItem setRightTextColor(int color) {
        rightText.setTextColor(color);
        return this;
    }

    /*-------------------------------------------click----------------------------------------------*/

    /*item点击事件*/
//    public MySuperItem setOnClickItem(final IItemClick iItemClick) {
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iItemClick.onClick();
//            }
//        });
//        return this;
//    }
//
//    public interface IItemClick {
//        void onClick();
//    }
}
//    Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.note_play);
//    ImageSpan imgSpan = new ImageSpan(context,bitmap);
//    SpannableString spanString = new SpannableString("icon");
//        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                vh.outline1.append(spanString);
//                vh.outline2.append(spanString);