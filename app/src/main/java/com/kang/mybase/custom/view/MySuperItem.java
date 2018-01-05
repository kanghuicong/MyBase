package com.kang.mybase.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kang.mybase.R;
import com.kang.mybase.custom.other.CircleTransform;

import static com.kang.utilssdk.SizeUtils.dp2px;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public class MySuperItem extends LinearLayout {
    int defaultColor = Color.BLACK;
    int defaultSize = 14;
    int defaultDrawablePadding = 5;
    int defaultMargins_0 = 0;
    int defaultMargins_10 = 10;
    int defaultMargins_20 = 20;

    /*left--left图片*/
    private Drawable mLeftImageL;
    private int mLeftImageLWidth;
    private int mLeftImageLHeight;
    private boolean noLeftImgL;
    private int mLeftImageLLeftMargin;
    private int mLeftImageLRightMargin;
    private int mLeftImageLTopMargin;
    private int mLeftImageLBottomMargin;
    /*left文字*/
    private String mLeftText;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftDrawablePadding;
    private int mLeftTextLeftMargin;
    private int mLeftTextRightMargin;
    private int mLeftTextTopMargin;
    private int mLeftTextBottomMargin;
    private boolean noLeftText;
    /*left--right图片*/
    private Drawable mLeftImageR;
    private int mLeftImageRWidth;
    private int mLeftImageRHeight;
    /*right--left图片*/
    private Drawable mRightImageL;
    private int mRightImageLWidth;
    private int mRightImageLHeight;
    private int mRightImageLLeftMargin;
    private int mRightImageLRightMargin;
    private int mRightImageLTopMargin;
    private int mRightImageLBottomMargin;
    private boolean noRightImgL;
    /*right文字*/
    private String mRightText;
    private int mRightTextColor;
    private int mRightTextSize;
    private int mRightDrawablePadding;
    private int mRightTextLeftMargin;
    private int mRightTextRightMargin;
    private int mRightTextTopMargin;
    private int mRightTextBottomMargin;
    private boolean noRightText;
    /*right--right图片*/
    private Drawable mRightImageR;
    private int mRightImageRWidth;
    private int mRightImageRHeight;
    private boolean isArrow;
    /*CheckBox*/
    private boolean isCheckBox;
    private int mCheckBoxWidth;
    private int mCheckBoxHeight;
    private int mCheckBoxLeftMargin;
    private int mCheckBoxRightMargin;
    private int mCheckBoxTopMargin;
    private int mCheckBoxBottomMargin;
    /*switch*/
    private boolean isSwitch;
    /*top*/
    private String mTopGravity;
    private int mTop_leftMargin;
    private int mTop_topMargin;
    private int mTop_bottomMargin;
    private int mTop_rightMargin;
    private boolean noTop;
    private boolean isTopBetween;
    /*top--left文字*/
    private String mTopLeftText;
    private int mTopLeftTextSize;
    private int mTopLeftTextColor;
    private int mTopLeftLeftMargin;
    private int mTopLeftTopMargin;
    private int mTopLeftRightMargin;
    private int mTopLeftBottomMargin;
    /*top--right文字*/
    private String mTopRightText;
    private int mTopRightTextSize;
    private int mTopRightTextColor;
    private int mTopRightLeftMargin;
    private int mTopRightTopMargin;
    private int mTopRightRightMargin;
    private int mTopRightBottomMargin;
    /*bottom*/
    private String mBottomGravity;
    private boolean noBottom;
    private boolean isBottomBetween;
    private int mBottom_leftMargin;
    private int mBottom_topMargin;
    private int mBottom_bottomMargin;
    private int mBottom_rightMargin;
    /*bottom--left文字*/
    private String mBottomLeftText;
    private int mBottomLeftTextSize;
    private int mBottomLeftTextColor;
    private int mBottomLeftLeftMargin;
    private int mBottomLeftTopMargin;
    private int mBottomLeftRightMargin;
    private int mBottomLeftBottomMargin;
    /*bottom--right文字*/
    private String mBottomRightText;
    private int mBottomRightTextSize;
    private int mBottomRightTextColor;
    private int mBottomRightLeftMargin;
    private int mBottomRightTopMargin;
    private int mBottomRightRightMargin;
    private int mBottomRightBottomMargin;

/*---------------------------------------------------------------------------------------------------*/

    private Context context;

    private ImageView leftImageL;
    private TextView leftText;
    private ImageView rightImageL;
    private TextView rightText;
    private CheckBox checkBox;
    private SwitchCompat switchCompat;

    private LinearLayout centerLayout;

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
        mLeftImageLLeftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_leftMargin, defaultMargins_0);
        mLeftImageLRightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_rightMargin, defaultMargins_10);
        mLeftImageLTopMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_topMargin, defaultMargins_0);
        mLeftImageLBottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_limg_bottomMargin, defaultMargins_0);
        noLeftImgL = typedArray.getBoolean(R.styleable.MySuperItem_noLeftImageL, false);
        /*left文字*/
        mLeftText = typedArray.getString(R.styleable.MySuperItem_left_text);
        mLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_left_text_color, defaultColor);
        mLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_size, defaultSize);
        mLeftDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_drawable_padding, defaultDrawablePadding);
        mLeftTextLeftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_leftMargin, defaultMargins_0);
        mLeftTextTopMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_topMargin, defaultMargins_0);
        mLeftTextBottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_bottomMargin, defaultMargins_0);
        mLeftTextRightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_rightMargin, defaultMargins_10);
        noLeftText = typedArray.getBoolean(R.styleable.MySuperItem_noLeftText, false);
        /*left--right图片*/
        mLeftImageR = typedArray.getDrawable(R.styleable.MySuperItem_left_rimg);
        mLeftImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_width, -1);
        mLeftImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_height, -1);
        /*right--left图片*/
        mRightImageL = typedArray.getDrawable(R.styleable.MySuperItem_right_limg);
        mRightImageLWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_width, -1);
        mRightImageLHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_height, -1);
        mRightImageLLeftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_leftMargin, defaultMargins_10);
        mRightImageLRightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_rightMargin, defaultMargins_0);
        mRightImageLTopMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_topMargin, defaultMargins_0);
        mRightImageLBottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_bottomMargin, defaultMargins_0);
        noRightImgL = typedArray.getBoolean(R.styleable.MySuperItem_noRightImgL, false);
        /*right文字*/
        mRightText = typedArray.getString(R.styleable.MySuperItem_right_text);
        mRightTextColor = typedArray.getColor(R.styleable.MySuperItem_right_text_color, defaultColor);
        mRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_size, defaultSize);
        mRightDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_drawable_padding, defaultDrawablePadding);
        mRightTextLeftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_leftMargin,defaultMargins_10 );
        mRightTextRightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_rightMargin, defaultMargins_0);
        mRightTextTopMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_topMargin, defaultMargins_0);
        mRightTextBottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_bottomMargin, defaultMargins_0);
        noRightText = typedArray.getBoolean(R.styleable.MySuperItem_noRightText, false);
        /*right--right图片*/
        mRightImageR = typedArray.getDrawable(R.styleable.MySuperItem_right_rimg);
        mRightImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_width, -1);
        mRightImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_height, -1);
        isArrow = typedArray.getBoolean(R.styleable.MySuperItem_isArrow, false);
        /*CheckBox*/
        isCheckBox = typedArray.getBoolean(R.styleable.MySuperItem_isCheckBox, false);
        mCheckBoxWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_width, defaultMargins_20);
        mCheckBoxHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_height, defaultMargins_20);
        mCheckBoxLeftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_leftMargin, defaultMargins_20);
        mCheckBoxTopMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_topMargin, defaultMargins_0);
        mCheckBoxRightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_rightMargin, defaultMargins_0);
        mCheckBoxBottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_bottomMargin, defaultMargins_0);
        /*switch*/
        isSwitch = typedArray.getBoolean(R.styleable.MySuperItem_isSwitch, false);
        /*top*/
        mTopGravity = typedArray.getString(R.styleable.MySuperItem_top_gravity);
        mTop_leftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_leftMargin, defaultMargins_0);
        mTop_topMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_topMargin, defaultMargins_0);
        mTop_bottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_bottomMargin, defaultMargins_0);
        mTop_rightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_rightMargin, defaultMargins_0);
        noTop = typedArray.getBoolean(R.styleable.MySuperItem_noTop, false);
        isTopBetween = typedArray.getBoolean(R.styleable.MySuperItem_isTopBetween, false);
        /*top--left文字*/
        mTopLeftText = typedArray.getString(R.styleable.MySuperItem_top_left_text);
        mTopLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_left_textSize, defaultSize);
        mTopLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_top_left_textColor, defaultColor);
        mTopLeftLeftMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_left_leftMargin, defaultMargins_0);
        mTopLeftTopMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_left_topMargin, defaultMargins_0);
        mTopLeftRightMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_left_rightMargin, defaultMargins_0);
        mTopLeftBottomMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_left_bottomMargin, defaultMargins_0);
        /*top--right文字*/
        mTopRightText = typedArray.getString(R.styleable.MySuperItem_top_right_text);
        mTopRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_right_textSize, defaultSize);
        mTopRightTextColor = typedArray.getColor(R.styleable.MySuperItem_top_right_textColor, defaultColor);
        mTopRightLeftMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_right_leftMargin, defaultMargins_10);
        mTopRightTopMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_right_topMargin, defaultMargins_0);
        mTopRightRightMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_right_rightMargin, defaultMargins_0);
        mTopRightBottomMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_right_bottomMargin, defaultMargins_0);
        /*bottom*/
        mBottomGravity = typedArray.getString(R.styleable.MySuperItem_bottom_gravity);
        mBottom_topMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_topMargin, defaultMargins_0);
        mBottom_leftMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_leftMargin, defaultMargins_0);
        mBottom_bottomMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_bottomMargin, defaultMargins_0);
        mBottom_rightMargin = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_rightMargin, defaultMargins_0);
        noBottom = typedArray.getBoolean(R.styleable.MySuperItem_noBottom, false);
        isBottomBetween = typedArray.getBoolean(R.styleable.MySuperItem_isBottomBetween, false);
        /*bottom--left文字*/
        mBottomLeftText = typedArray.getString(R.styleable.MySuperItem_bottom_left_text);
        mBottomLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_left_textSize, defaultSize);
        mBottomLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_bottom_left_textColor, defaultColor);
        mBottomLeftLeftMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_left_leftMargin, defaultMargins_0);
        mBottomLeftTopMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_left_topMargin, defaultMargins_0);
        mBottomLeftRightMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_left_rightMargin, defaultMargins_0);
        mBottomLeftBottomMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_left_bottomMargin, defaultMargins_0);
        /*bottom--right文字*/
        mBottomRightText = typedArray.getString(R.styleable.MySuperItem_bottom_right_text);
        mBottomRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_right_textSize, defaultSize);
        mBottomRightTextColor = typedArray.getColor(R.styleable.MySuperItem_bottom_right_textColor, defaultColor);
        mBottomRightLeftMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_right_leftMargin, defaultMargins_10);
        mBottomRightTopMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_right_topMargin, defaultMargins_0);
        mBottomRightRightMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_right_rightMargin, defaultMargins_0);
        mBottomRightBottomMargin =  typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_right_bottomMargin, defaultMargins_0);

        typedArray.recycle();
    }

    private void init() {
        this.setPadding((getPaddingLeft()==0?15:getPaddingLeft()),
                (getPaddingTop()==0?15:getPaddingTop()),
                (getPaddingRight()==0?15:getPaddingRight()),
                (getPaddingBottom()==0?15:getPaddingBottom()));
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);

        initLeftImage();
        initLeftText();
        initCenter();
        initRightImage();
        initRightText();
        initCheckBox();
        initSwicth();
    }

    /*-----------------------------------------初始化布局------------------------------------------------*/
    /*left---left图片*/
    private void initLeftImage() {
        if (noLeftImgL)return;
        leftImageL = new ImageView(context);
        initImage(leftImageL, mLeftImageLWidth, mLeftImageLHeight, mLeftImageL, mLeftImageLLeftMargin, mLeftImageLTopMargin,mLeftImageLRightMargin,mLeftImageLBottomMargin);
    }

    /*left文字*/
    private void initLeftText() {
        if (noLeftText)return;
        leftText = new TextView(context);
        initText(leftText, mLeftText, mLeftTextSize, mLeftTextColor, mLeftImageR, mLeftImageRWidth, mLeftImageRHeight, mLeftDrawablePadding, mLeftTextLeftMargin,mLeftTextTopMargin,mLeftTextRightMargin,mLeftTextBottomMargin);
        goneText(mLeftText, leftText);
    }

    /*center*/
    private void initCenter() {
        centerLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        centerLayout.setLayoutParams(lp);
        centerLayout.setOrientation(VERTICAL);
        centerLayout.setGravity(Gravity.CENTER);
        initTop();
        initBottom();
        addView(centerLayout);
    }
    /*top*/
    private void initTop() {
        if (noTop)return;
        topLayout = new LinearLayout(context);
        initCenterLayout(topLayout, mTopGravity, mTop_leftMargin, mTop_topMargin, mTop_rightMargin, mTop_bottomMargin);

        topLeftText = new TextView(context);
        initCenterText(topLeftText, mTopLeftText, mTopLeftTextSize, mTopLeftTextColor,mTopLeftLeftMargin,mTopLeftTopMargin,mTopLeftRightMargin,mTopLeftBottomMargin,true,isTopBetween);
        topRightText = new TextView(context);
        initCenterText(topRightText, mTopRightText, mTopRightTextSize, mTopRightTextColor,mTopRightLeftMargin,mTopRightTopMargin,mTopRightRightMargin,mTopRightBottomMargin,false,isTopBetween);
        goneText(mTopRightText,topRightText);

        topLayout.addView(topLeftText);
        topLayout.addView(topRightText);
        centerLayout.addView(topLayout);
    }
    /*bottom*/
    private void initBottom() {
        if (noBottom)return;
        bottomLayout = new LinearLayout(context);
        initCenterLayout(bottomLayout, mBottomGravity,mBottom_leftMargin,mBottom_topMargin,mBottom_rightMargin,mBottom_bottomMargin);

        if (mBottomLeftText == null && mBottomRightText == null) bottomLayout.setVisibility(GONE);

        bottomLeftText = new TextView(context);
        initCenterText(bottomLeftText, mBottomLeftText, mBottomLeftTextSize, mBottomLeftTextColor, mBottomLeftLeftMargin, mBottomLeftTopMargin, mBottomLeftRightMargin, mBottomLeftBottomMargin,true,isBottomBetween);
        bottomRightText = new TextView(context);
        initCenterText(bottomRightText, mBottomRightText, mBottomRightTextSize, mBottomRightTextColor, mBottomRightLeftMargin, mBottomRightTopMargin, mBottomRightRightMargin, mBottomRightBottomMargin,false,isBottomBetween);
        goneText(mBottomRightText,bottomRightText);

        bottomLayout.addView(bottomLeftText);
        bottomLayout.addView(bottomRightText);
        centerLayout.addView(bottomLayout);
    }

    /*right---left图片*/
    private void initRightImage() {
        if (noRightImgL)return;
        rightImageL = new ImageView(context);
        initImage(rightImageL, mRightImageLWidth, mRightImageLHeight, mRightImageL, mRightImageLLeftMargin, mRightImageLTopMargin,mRightImageLRightMargin,mRightImageLBottomMargin);
    }

    /*right文字*/
    private void initRightText() {
        if (noRightText)return;
        rightText = new TextView(context);
        if (!isArrow) {
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, mRightImageR, mRightImageRWidth, mRightImageRHeight, mRightDrawablePadding, mRightTextLeftMargin,mRightTextTopMargin,mRightTextRightMargin,mRightTextBottomMargin);
            goneText(mRightText, rightText);
        } else
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, context.getResources().getDrawable(R.mipmap.arrow_right), 8, 16, mRightDrawablePadding, mRightTextLeftMargin,mRightTextTopMargin,mRightTextRightMargin,mRightTextBottomMargin);
    }
    /*CheckBox*/
    private void initCheckBox() {
        if (isCheckBox) {
            checkBox = new CheckBox(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp2px(mCheckBoxWidth), dp2px(mCheckBoxHeight));
            lp.setMargins(mCheckBoxLeftMargin,mCheckBoxTopMargin,mCheckBoxRightMargin,mCheckBoxBottomMargin);
            checkBox.setLayoutParams(lp);
            checkBox.setButtonDrawable(R.color.Transparent);
            checkBox.setBackgroundResource(R.drawable.item_choose);

            addView(checkBox);
        }
    }
    /*switch*/
    private void initSwicth() {
        if (isSwitch) {
            switchCompat = new SwitchCompat(context);
            switchCompat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            switchCompat.setBackgroundResource(R.color.Transparent);

            addView(switchCompat);
        }
    }

/*-------------------------------------------公共方法----------------------------------------------*/

    private void initCenterLayout(LinearLayout layout, String gravity,int leftMargin,int topMargin,int rightMargin,int bottomMargin) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(dp2px(leftMargin),dp2px(topMargin),dp2px(rightMargin),dp2px(bottomMargin));
        layout.setLayoutParams(lp);
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(returnGravity(gravity));
    }

    private void initCenterText(TextView textView, String content, int size, int color, int leftMargin,int topMargin,int rightMargin,int bottomMargin,boolean isLeft,boolean isBetween) {
        LinearLayout.LayoutParams lp = null;
        if (isLeft) {
            if (isBetween) lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
            else lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }else {
            if (isBetween) lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            else lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        }

        lp.setMargins(dp2px(leftMargin), dp2px(topMargin), dp2px(rightMargin), dp2px(bottomMargin));

        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
    }


    private void initText(TextView textView, String content, int textSize, int textColor, Drawable drawable, int drawableWidth, int drawableHight, int drawablePadding, int leftMargin,int topMargin,int rightMargin,int bottomMargin) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(dp2px(leftMargin), dp2px(topMargin), dp2px(rightMargin),dp2px(bottomMargin));
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setText(content);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);

        if (drawable != null) {
            if (drawableWidth != -1 && drawableHight != -1) {
                drawable.setBounds(0, 0, dp2px(drawableWidth), dp2px(drawableHight));
                textView.setCompoundDrawables(null, null, drawable, null);
            } else textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
        textView.setCompoundDrawablePadding(dp2px(drawablePadding));
        addView(textView);
    }

    private void initImage(ImageView imageView, int imageWidth, int imageHeight, Drawable drawable,  int leftMargins,int topMargins,int rightMargins,int bottomMargins) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp2px(imageWidth), dp2px(imageHeight));
        lp.setMargins(dp2px(leftMargins), dp2px(topMargins), dp2px(rightMargins), dp2px(bottomMargins));
        imageView.setLayoutParams(lp);
        imageView.setBackgroundDrawable(drawable);
        addView(imageView);
        goneText(drawable, imageView);
    }

    private void goneText(Object content, View view) {
        if (content == null) view.setVisibility(GONE);
    }

    private void visibleText(View view) {
        if (view.getVisibility() == GONE) view.setVisibility(VISIBLE);
    }

    private void glideImage(ImageView view,String url, boolean isRound) {
        if (isRound) Glide.with(context).load(url).transform(new CircleTransform(context)).into(view);
        else Glide.with(context).load(url).into(view);
    }

    private int returnGravity(String gravity) {
        if (gravity==null)return Gravity.LEFT;
        switch (gravity) {
            case "center":
                return Gravity.CENTER;
            case "left":
                return Gravity.LEFT;
            case "right":
                return Gravity.RIGHT;
            case "top":
                return Gravity.TOP;
            case "bottom":
                return Gravity.BOTTOM;
            default:
                return Gravity.LEFT;
        }
    }

    /*----------------------------------------------动态修改----------------------------------------*/
    /*-------------------------------------------left-----------------------------------------------*/
    /*left--left图片*/
    public MySuperItem setLeftImageL(String url,boolean isRound) {
        visibleText(leftImageL);
        glideImage(leftImageL, url, isRound);
        return this;
    }

    /*left文字content*/
    public MySuperItem setLeftText(String mLeftText) {
        visibleText(leftText);
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
        leftText.setTextColor(getResources().getColor(color));
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
        topLeftText.setTextColor(getResources().getColor(color));
        return this;
    }

    /*top--right文字content*/
    public MySuperItem setTopRightText(String content) {
        visibleText(topRightText);
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
        topRightText.setTextColor(getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------bottom-----------------------------------------------*/
    public MySuperItem setBottomGravity(int gravity) {
        bottomLayout.setGravity(gravity);
        return this;
    }

    /*bottom--left文字content*/
    public MySuperItem setBottomLeftText(String content) {
        visibleText(bottomLayout);
        bottomLeftText.setText(content);
        return this;
    }

    /*top--left文字size*/
    public MySuperItem setBottomLeftTextSize(int size) {
        bottomLeftText.setTextSize(size);
        return this;
    }

    /*bottom--left文字color*/
    public MySuperItem setBottomLeftTextColor(int color) {
        bottomLeftText.setTextColor(getResources().getColor(color));
        return this;
    }

    /*bottom--right文字content*/
    public MySuperItem setBottomRightText(String content) {
        visibleText(bottomLayout);
        visibleText(bottomRightText);
        bottomRightText.setText(content);
        return this;
    }

    /*bottom--right文字size*/
    public MySuperItem setBottomRightTextSize(int size) {
        bottomRightText.setTextSize(size);
        return this;
    }

    /*top--right文字color*/
    public MySuperItem setBottomRightTextColor(int color) {
        bottomRightText.setTextColor(getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------right-----------------------------------------------*/
    /*right---left图片*/
    public MySuperItem setRightImageL(String url,boolean isRound) {
        visibleText(rightImageL);
        glideImage(rightImageL, url, isRound);
        return this;
    }

    /*right文字content*/
    public MySuperItem setRightText(String mRightText) {
        visibleText(rightText);
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
        rightText.setTextColor(getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------click----------------------------------------------*/

    public MySuperItem setOnClickItem(final IItemClick iItemClick) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*left---left图片点击事件*/
    public MySuperItem setOnClickLeftImgL(final IItemClick iItemClick) {
        leftImageL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*bottom---left文字点击事件*/
    public MySuperItem setOnClickBottomLeftText(final IItemClick iItemClick) {
        bottomLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*bottom---right文字点击事件*/
    public MySuperItem setOnClickBottomRightText(final IItemClick iItemClick) {
        bottomRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*checkBox点击事件*/
    public MySuperItem setOnClickCheckBox(final IChangeClick iChangeClick) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iChangeClick.onClick(buttonView,isChecked);
            }
        });
        return this;
    }

    public MySuperItem setOnClickSwitch(final IChangeClick iChangeClick) {
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iChangeClick.onClick(buttonView, isChecked);
            }
        });
        return this;
    }

    public interface IItemClick {
        void onClick(View view);
    }

    public interface IChangeClick {
        void onClick(CompoundButton buttonView, boolean isChecked);
    }
}
//    Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.note_play);
//    ImageSpan imgSpan = new ImageSpan(context,bitmap);
//    SpannableString spanString = new SpannableString("icon");
//        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                vh.outline1.append(spanString);
//                vh.outline2.append(spanString);