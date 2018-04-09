package com.kang.mybase.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 *                                     top↓
 *                             <TextView1><TextView2>
 * left→ <ImgView><TextView>                         <TextView><ImgView> ←right
 *                             <TextView3><TextView4>
 *                                   bottom↑
 * left的ImgView →设置图片setLeftImageL
 * left的TextView →设置文字内容setLeftText，设置文字大小setLeftTextSize,设置文字颜色setLeftTextColor
 * <p>
 * top和bottom的文字分left和right
 * TextView1→设置文字内容setTopLeftText，设置文字大小setTopLeftTextSize,设置文字颜色setTopLeftTextColor
 * TextView2→设置文字内容setTopRightText，设置文字大小setTopRightTextSize,设置文字颜色setTopRightTextColor
 * TextView3→设置文字内容setBottomLeftText，设置文字大小setBottomLeftTextSize,设置文字颜色setBottomLeftTextColor
 * TextView4→设置文字内容setBottomRightText，设置文字大小setBottomRightTextSize,设置文字颜色setBottomRightTextColor
 * <p>
 * right的TextView →设置文字内容setRightText，设置文字大小setRightTextSize,设置文字颜色setRightTextColor
 * right的ImgView →设置图片setRightImageL
 * <p>
 * xml设置
 * 参照attrs.xml,命名都是有按一定规律命名的
 * 上述动态设置方法也可在xml里设置，在xml里除了可以设置size,color和text外还可以设置margin
 * 特别说明：noLeft,noRight,noTop,noBottom,noLeftImageL,noRightImageL是用来设置是否需要该子View,减少布局复杂度
 * 比如：如果只需要设置左右两边的textView,那么left和right的ImgView，top和bottom皆不需要
 * 则设置noLeftImageL，noRightImageL，noTop，noBottom
 * isPadding默认为false，需要自己设置padding；设置为true时则整个LinearLayout有个默认padding
 * isCheckBox默认false；设置为true时则显示单选CheckBox
 * isSwitch默认false；设置为true时则显示Switch
 * isTopBetween默认false；设置为true时则TextView1权重为1
 * isBottomBetween默认false；设置为true时则TextView3权重为1
 */

public class MySuperItem extends LinearLayout {
    int defaultColor = 0xFF333333;
    int defaultColor2 = 0xFF808080;
    int backgroundColor = R.color.White;
    int defaultSize = 14;
    int defaultDrawablePadding = 5;
    int defaultMargins_0 = 0;
    int defaultMargins_10 = 10;
    int defaultMargins_20 = 20;

    private boolean isPadding;
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

        isPadding = typedArray.getBoolean(R.styleable.MySuperItem_isPadding, false);
        /*left--left图片*/
        mLeftImageL = typedArray.getDrawable(R.styleable.MySuperItem_left_limg);
        mLeftImageLWidth = typedArray.getInteger(R.styleable.MySuperItem_left_limg_width, LayoutParams.WRAP_CONTENT);
        mLeftImageLHeight = typedArray.getInteger(R.styleable.MySuperItem_left_limg_height, LayoutParams.WRAP_CONTENT);
        mLeftImageLLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_left_limg_leftMargin, defaultMargins_0);
        mLeftImageLRightMargin = typedArray.getInteger(R.styleable.MySuperItem_left_limg_rightMargin, defaultMargins_10);
        mLeftImageLTopMargin = typedArray.getInteger(R.styleable.MySuperItem_left_limg_topMargin, defaultMargins_0);
        mLeftImageLBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_left_limg_bottomMargin, defaultMargins_0);
        noLeftImgL = typedArray.getBoolean(R.styleable.MySuperItem_noLeftImageL, false);
        /*left文字*/
        mLeftText = typedArray.getString(R.styleable.MySuperItem_left_text);
        mLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_left_text_color, defaultColor);
        mLeftTextSize = typedArray.getInteger(R.styleable.MySuperItem_left_text_size, defaultSize);
        mLeftDrawablePadding = typedArray.getInteger(R.styleable.MySuperItem_left_drawable_padding, defaultDrawablePadding);
        mLeftTextLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_left_text_leftMargin, defaultMargins_0);
        mLeftTextTopMargin = typedArray.getInteger(R.styleable.MySuperItem_left_text_topMargin, defaultMargins_0);
        mLeftTextBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_left_text_bottomMargin, defaultMargins_0);
        mLeftTextRightMargin = typedArray.getInteger(R.styleable.MySuperItem_left_text_rightMargin, defaultMargins_10);
        noLeftText = typedArray.getBoolean(R.styleable.MySuperItem_noLeftText, false);
        /*left--right图片*/
        mLeftImageR = typedArray.getDrawable(R.styleable.MySuperItem_left_rimg);
        mLeftImageRWidth = typedArray.getInteger(R.styleable.MySuperItem_left_rimg_width, -1);
        mLeftImageRHeight = typedArray.getInteger(R.styleable.MySuperItem_left_rimg_height, -1);
        /*right--left图片*/
        mRightImageL = typedArray.getDrawable(R.styleable.MySuperItem_right_limg);
        mRightImageLWidth = typedArray.getInteger(R.styleable.MySuperItem_right_limg_width, -1);
        mRightImageLHeight = typedArray.getInteger(R.styleable.MySuperItem_right_limg_height, -1);
        mRightImageLLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_right_limg_leftMargin, defaultMargins_10);
        mRightImageLRightMargin = typedArray.getInteger(R.styleable.MySuperItem_right_limg_rightMargin, defaultMargins_0);
        mRightImageLTopMargin = typedArray.getInteger(R.styleable.MySuperItem_right_limg_topMargin, defaultMargins_0);
        mRightImageLBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_right_limg_bottomMargin, defaultMargins_0);
        noRightImgL = typedArray.getBoolean(R.styleable.MySuperItem_noRightImgL, false);
        /*right文字*/
        mRightText = typedArray.getString(R.styleable.MySuperItem_right_text);
        mRightTextColor = typedArray.getColor(R.styleable.MySuperItem_right_text_color, defaultColor2);
        mRightTextSize = typedArray.getInteger(R.styleable.MySuperItem_right_text_size, defaultSize);
        mRightDrawablePadding = typedArray.getInteger(R.styleable.MySuperItem_right_drawable_padding, defaultDrawablePadding);
        mRightTextLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_right_text_leftMargin, defaultMargins_10);
        mRightTextRightMargin = typedArray.getInteger(R.styleable.MySuperItem_right_text_rightMargin, defaultMargins_0);
        mRightTextTopMargin = typedArray.getInteger(R.styleable.MySuperItem_right_text_topMargin, defaultMargins_0);
        mRightTextBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_right_text_bottomMargin, defaultMargins_0);
        noRightText = typedArray.getBoolean(R.styleable.MySuperItem_noRightText, false);
        /*right--right图片*/
        mRightImageR = typedArray.getDrawable(R.styleable.MySuperItem_right_rimg);
        mRightImageRWidth = typedArray.getInteger(R.styleable.MySuperItem_right_rimg_width, -1);
        mRightImageRHeight = typedArray.getInteger(R.styleable.MySuperItem_right_rimg_height, -1);
        isArrow = typedArray.getBoolean(R.styleable.MySuperItem_isArrow, false);
        /*CheckBox*/
        isCheckBox = typedArray.getBoolean(R.styleable.MySuperItem_isCheckBox, false);
        mCheckBoxWidth = typedArray.getInteger(R.styleable.MySuperItem_checkBox_width, defaultMargins_20);
        mCheckBoxHeight = typedArray.getInteger(R.styleable.MySuperItem_checkBox_height, defaultMargins_20);
        mCheckBoxLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_checkBox_leftMargin, defaultMargins_20);
        mCheckBoxTopMargin = typedArray.getInteger(R.styleable.MySuperItem_checkBox_topMargin, defaultMargins_0);
        mCheckBoxRightMargin = typedArray.getInteger(R.styleable.MySuperItem_checkBox_rightMargin, defaultMargins_0);
        mCheckBoxBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_checkBox_bottomMargin, defaultMargins_0);
        /*switch*/
        isSwitch = typedArray.getBoolean(R.styleable.MySuperItem_isSwitch, false);
        /*top*/
        mTopGravity = typedArray.getString(R.styleable.MySuperItem_top_gravity);
        mTop_leftMargin = typedArray.getInteger(R.styleable.MySuperItem_top_leftMargin, defaultMargins_0);
        mTop_topMargin = typedArray.getInteger(R.styleable.MySuperItem_top_topMargin, defaultMargins_0);
        mTop_bottomMargin = typedArray.getInteger(R.styleable.MySuperItem_top_bottomMargin, defaultMargins_0);
        mTop_rightMargin = typedArray.getInteger(R.styleable.MySuperItem_top_rightMargin, defaultMargins_0);
        noTop = typedArray.getBoolean(R.styleable.MySuperItem_noTop, false);
        isTopBetween = typedArray.getBoolean(R.styleable.MySuperItem_isTopBetween, false);
        /*top--left文字*/
        mTopLeftText = typedArray.getString(R.styleable.MySuperItem_top_left_text);
        mTopLeftTextSize = typedArray.getInteger(R.styleable.MySuperItem_top_left_textSize, defaultSize);
        mTopLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_top_left_textColor, defaultColor);
        mTopLeftLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_top_left_leftMargin, defaultMargins_0);
        mTopLeftTopMargin = typedArray.getInteger(R.styleable.MySuperItem_top_left_topMargin, defaultMargins_0);
        mTopLeftRightMargin = typedArray.getInteger(R.styleable.MySuperItem_top_left_rightMargin, defaultMargins_0);
        mTopLeftBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_top_left_bottomMargin, defaultMargins_0);
        /*top--right文字*/
        mTopRightText = typedArray.getString(R.styleable.MySuperItem_top_right_text);
        mTopRightTextSize = typedArray.getInteger(R.styleable.MySuperItem_top_right_textSize, defaultSize);
        mTopRightTextColor = typedArray.getColor(R.styleable.MySuperItem_top_right_textColor, defaultColor);
        mTopRightLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_top_right_leftMargin, defaultMargins_10);
        mTopRightTopMargin = typedArray.getInteger(R.styleable.MySuperItem_top_right_topMargin, defaultMargins_0);
        mTopRightRightMargin = typedArray.getInteger(R.styleable.MySuperItem_top_right_rightMargin, defaultMargins_0);
        mTopRightBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_top_right_bottomMargin, defaultMargins_0);
        /*bottom*/
        mBottomGravity = typedArray.getString(R.styleable.MySuperItem_bottom_gravity);
        mBottom_topMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_topMargin, defaultMargins_0);
        mBottom_leftMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_leftMargin, defaultMargins_0);
        mBottom_bottomMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_bottomMargin, defaultMargins_0);
        mBottom_rightMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_rightMargin, defaultMargins_0);
        noBottom = typedArray.getBoolean(R.styleable.MySuperItem_noBottom, false);
        isBottomBetween = typedArray.getBoolean(R.styleable.MySuperItem_isBottomBetween, false);
        /*bottom--left文字*/
        mBottomLeftText = typedArray.getString(R.styleable.MySuperItem_bottom_left_text);
        mBottomLeftTextSize = typedArray.getInteger(R.styleable.MySuperItem_bottom_left_textSize, defaultSize);
        mBottomLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_bottom_left_textColor, defaultColor);
        mBottomLeftLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_left_leftMargin, defaultMargins_0);
        mBottomLeftTopMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_left_topMargin, defaultMargins_0);
        mBottomLeftRightMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_left_rightMargin, defaultMargins_0);
        mBottomLeftBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_left_bottomMargin, defaultMargins_0);
        /*bottom--right文字*/
        mBottomRightText = typedArray.getString(R.styleable.MySuperItem_bottom_right_text);
        mBottomRightTextSize = typedArray.getInteger(R.styleable.MySuperItem_bottom_right_textSize, defaultSize);
        mBottomRightTextColor = typedArray.getColor(R.styleable.MySuperItem_bottom_right_textColor, defaultColor);
        mBottomRightLeftMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_right_leftMargin, defaultMargins_10);
        mBottomRightTopMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_right_topMargin, defaultMargins_0);
        mBottomRightRightMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_right_rightMargin, defaultMargins_0);
        mBottomRightBottomMargin = typedArray.getInteger(R.styleable.MySuperItem_bottom_right_bottomMargin, defaultMargins_0);

        typedArray.recycle();
    }

    private void init() {
        if (isPadding) {
            int padding = fitSize(12);
            this.setPadding(padding, padding, padding, padding);
        } else
            this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        if (this.getBackground() == null) {
//            this.setBackgroundColor(context.getResources().getColor(backgroundColor));
            this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.item_white_click));
        }

        initLeftImage();
        initLeftText();
        initCenter();
        initRightImage();
        initRightText();
        initCheckBox();
        initSwitch();
    }

    /*-----------------------------------------初始化布局------------------------------------------------*/
    /*left---left图片*/
    private void initLeftImage() {
        if (noLeftImgL) return;
        leftImageL = new ImageView(context);
        initImage(leftImageL, mLeftImageLWidth, mLeftImageLHeight, mLeftImageL, mLeftImageLLeftMargin, mLeftImageLTopMargin, mLeftImageLRightMargin, mLeftImageLBottomMargin);
    }

    /*left文字*/
    private void initLeftText() {
        if (noLeftText) return;
        leftText = new TextView(context);
        initText(leftText, mLeftText, mLeftTextSize, mLeftTextColor, mLeftImageR, mLeftImageRWidth, mLeftImageRHeight, mLeftDrawablePadding, mLeftTextLeftMargin, mLeftTextTopMargin, mLeftTextRightMargin, mLeftTextBottomMargin);
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
        if (noTop) return;
        topLayout = new LinearLayout(context);
        initCenterLayout(topLayout, mTopGravity, mTop_leftMargin, mTop_topMargin, mTop_rightMargin, mTop_bottomMargin);

        topLeftText = new TextView(context);
        initCenterText(topLeftText, mTopLeftText, mTopLeftTextSize, mTopLeftTextColor, mTopLeftLeftMargin, mTopLeftTopMargin, mTopLeftRightMargin, mTopLeftBottomMargin, 1,true, isTopBetween);
        topRightText = new TextView(context);
        initCenterText(topRightText, mTopRightText, mTopRightTextSize, mTopRightTextColor, mTopRightLeftMargin, mTopRightTopMargin, mTopRightRightMargin, mTopRightBottomMargin, 1,false, isTopBetween);
        goneText(mTopRightText, topRightText);

        topLayout.addView(topLeftText);
        topLayout.addView(topRightText);
        centerLayout.addView(topLayout);
    }

    /*bottom*/
    private void initBottom() {
        if (noBottom) return;
        bottomLayout = new LinearLayout(context);
        initCenterLayout(bottomLayout, mBottomGravity, mBottom_leftMargin, mBottom_topMargin, mBottom_rightMargin, mBottom_bottomMargin);

        if (mBottomLeftText == null && mBottomRightText == null) bottomLayout.setVisibility(GONE);

        bottomLeftText = new TextView(context);
        initCenterText(bottomLeftText, mBottomLeftText, mBottomLeftTextSize, mBottomLeftTextColor, mBottomLeftLeftMargin, mBottomLeftTopMargin, mBottomLeftRightMargin, mBottomLeftBottomMargin, 1.2f,true, isBottomBetween);
        bottomRightText = new TextView(context);
        initCenterText(bottomRightText, mBottomRightText, mBottomRightTextSize, mBottomRightTextColor, mBottomRightLeftMargin, mBottomRightTopMargin, mBottomRightRightMargin, mBottomRightBottomMargin, 1.2f,false, isBottomBetween);
        goneText(mBottomRightText, bottomRightText);

        bottomLayout.addView(bottomLeftText);
        bottomLayout.addView(bottomRightText);
        centerLayout.addView(bottomLayout);
    }

    /*right---left图片*/
    private void initRightImage() {
        if (noRightImgL) return;
        rightImageL = new ImageView(context);
        initImage(rightImageL, mRightImageLWidth, mRightImageLHeight, mRightImageL, mRightImageLLeftMargin, mRightImageLTopMargin, mRightImageLRightMargin, mRightImageLBottomMargin);
    }

    /*right文字*/
    private void initRightText() {
        if (noRightText) return;
        rightText = new TextView(context);
        if (!isArrow) {
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, mRightImageR, mRightImageRWidth, mRightImageRHeight, mRightDrawablePadding, mRightTextLeftMargin, mRightTextTopMargin, mRightTextRightMargin, mRightTextBottomMargin);
            goneText(mRightText, rightText);
        } else {
            Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.mipmap.arrow_right);
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, context.getResources().getDrawable(R.mipmap.arrow_right), image.getWidth(), image.getHeight(), mRightDrawablePadding, mRightTextLeftMargin, mRightTextTopMargin, mRightTextRightMargin, mRightTextBottomMargin);

        }
    }

    /*CheckBox*/
    private void initCheckBox() {
        if (isCheckBox) {
            checkBox = new CheckBox(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(fitSize(mCheckBoxWidth), fitSize(mCheckBoxHeight));
            lp.setMargins(mCheckBoxLeftMargin, mCheckBoxTopMargin, mCheckBoxRightMargin, mCheckBoxBottomMargin);
            checkBox.setLayoutParams(lp);
            checkBox.setButtonDrawable(R.color.Transparent);
            checkBox.setBackgroundResource(R.drawable.item_choose);

            addView(checkBox);
        }
    }

    /*switch*/
    private void initSwitch() {
        if (isSwitch) {
            this.setPadding(fitSize(12), fitSize(10), fitSize(12), fitSize(10));
            switchCompat = new SwitchCompat(context);
            switchCompat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            switchCompat.setBackgroundResource(R.color.Transparent);

            addView(switchCompat);
        }
    }

/*-------------------------------------------公共方法----------------------------------------------*/

    private void initCenterLayout(LinearLayout layout, String gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(fitSize(leftMargin), fitSize(topMargin), fitSize(rightMargin), fitSize(bottomMargin));
        layout.setLayoutParams(lp);
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(returnGravity(gravity));
    }

    private void initCenterText(TextView textView, String content, int size, int color, int leftMargin, int topMargin, int rightMargin, int bottomMargin, float line, boolean isLeft, boolean isBetween) {
        LinearLayout.LayoutParams lp = null;
        if (isLeft) {
            if (isBetween)
                lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
            else
                lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        } else {
            if (isBetween)
                lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            else
                lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        }

        lp.setMargins(fitSize(leftMargin), fitSize(topMargin), fitSize(rightMargin), fitSize(bottomMargin));

        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setLineSpacing(2,line);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
    }


    private void initText(TextView textView, String content, int textSize, int textColor, Drawable drawable, int drawableWidth, int drawableHight, int drawablePadding, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(fitSize(leftMargin), fitSize(topMargin), fitSize(rightMargin), fitSize(bottomMargin));
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setText(content);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);

        if (drawable != null) {
            if (drawableWidth != -1 && drawableHight != -1) {
                drawable.setBounds(0, 0, drawableWidth, drawableHight);
                textView.setCompoundDrawables(null, null, drawable, null);
            } else textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        textView.setCompoundDrawablePadding(fitSize(drawablePadding));

        addView(textView);
    }


    private void initImage(ImageView imageView, int imageWidth, int imageHeight, Drawable drawable, int leftMargins, int topMargins, int rightMargins, int bottomMargins) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(fitSize(imageWidth), fitSize(imageHeight));
        lp.setMargins(fitSize(leftMargins), fitSize(topMargins), fitSize(rightMargins), fitSize(bottomMargins));
        imageView.setLayoutParams(lp);
        imageView.setBackgroundDrawable(drawable);
        addView(imageView);
        goneText(drawable, imageView);
    }

    public int fitSize(int size) {
        return dp2px(size);
//        return size;
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
        if (gravity == null) return Gravity.LEFT;
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
    public MySuperItem setLeftTextColor(int color,boolean isDrawable) {
        if (isDrawable)leftText.setTextColor(context.getResources().getColorStateList(color));
        else leftText.setTextColor(context.getResources().getColor(color));
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

    /*top--left文字-左边img*/
    public MySuperItem setTopLeftTextImg(Drawable drawable, int drawableWidth, int drawableHight) {
        drawable.setBounds(0, 0, drawableWidth, drawableHight);
        topLeftText.setCompoundDrawablePadding(5);
        topLeftText.setCompoundDrawables(drawable, null, null, null);

        return this;
    }

    /*top--left文字size*/
    public MySuperItem setTopLeftTextSize(int size) {
        topLeftText.setTextSize(size);
        return this;
    }

    /*top--left文字color*/
    public MySuperItem setTopLeftTextColor(int color,boolean isDrawable) {
        if (isDrawable)topLeftText.setTextColor(context.getResources().getColorStateList(color));
        else topLeftText.setTextColor(context.getResources().getColor(color));
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
    public MySuperItem setTopRightTextColor(int color,boolean isDrawable) {
        if (isDrawable)topRightText.setTextColor(context.getResources().getColorStateList(color));
        else topRightText.setTextColor(context.getResources().getColor(color));
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
    public MySuperItem setBottomLeftTextColor(int color,boolean isDrawable) {
        if (isDrawable)bottomLeftText.setTextColor(context.getResources().getColorStateList(color));
        else bottomLeftText.setTextColor(context.getResources().getColor(color));
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

    /*-------------------------------------------arrow----------------------------------------------*/
    public MySuperItem setArrow(boolean isArrow) {
        if (!isArrow) {
            rightText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        return this;
    }

    /*-------------------------------------------switch----------------------------------------------*/
    public MySuperItem setSwitch(boolean isSwitch) {
        switchCompat.setChecked(isSwitch);
        return this;
    }

    public MySuperItem setSwitchClickable(boolean isClickable) {
        switchCompat.setClickable(isClickable);
        return this;
    }


    /*-------------------------------------------click----------------------------------------------*/

    public MySuperItem setOnClickItem(final IClick iItemClick) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*left---left图片点击事件*/
    public MySuperItem setOnClickLeftImgL(final IClick iItemClick) {
        leftImageL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*bottom---left文字点击事件*/
    public MySuperItem setOnClickBottomLeftText(final IClick iTextClick) {
        bottomLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iTextClick.onClick(view);
            }
        });
        return this;
    }

    /*bottom---right文字点击事件*/
    public MySuperItem setOnClickBottomRightText(final IClick iTextClick) {
        bottomRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iTextClick.onClick(view);
            }
        });
        return this;
    }

    /*checkBox点击事件*/
    public MySuperItem setOnClickCheckBox(final IChangeClick iChangeClick) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iChangeClick.onClick(buttonView, isChecked);
            }
        });
        return this;
    }

    /*Switch点击事件*/
    public MySuperItem setOnClickSwitch(final IChangeClick iChangeClick) {
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iChangeClick.onClick(buttonView, isChecked);
            }
        });
        return this;
    }

    /*Switch点击事件*/
    public MySuperItem setOnClickRightText(final IClick iClick) {
        rightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iClick.onClick(v);
            }
        });
        return this;
    }

    /*-------------------------------------------------------------------------------------------------*/
    public String getRightText() {
        return rightText.getText().toString();
    }

    public boolean isCheck() {
        return switchCompat.isChecked();
    }


    /*---------------------------------------------接口--------------------------------------------*/

    public interface IClick {
        void onClick(View view);
    }

    public interface IChangeClick {
        void onClick(CompoundButton buttonView, boolean isChecked);
    }
}
