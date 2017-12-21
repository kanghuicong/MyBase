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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kang.mybase.R;
import com.kang.mybase.fun.CircleTransform;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public class MySuperItem extends LinearLayout {
    int defaultColor = Color.BLACK;
    int defaultSize = 14;
    int defaultDrawablePadding = 5;
    int defaultMargins_0 = 0;
    int defaultMargins_20 = 20;

    /*left--left图片*/
    private Drawable mLeftImageL;
    private int mLeftImageLWidth;
    private int mLeftImageLHeight;
    private boolean noLeftImgL;
    /*left文字*/
    private String mLeftText;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftDrawablePadding;
    private int mLeftTextLeftMargins;
    private int mLeftTextRightMargins;
    private boolean noLeftText;
    /*left--right图片*/
    private Drawable mLeftImageR;
    private int mLeftImageRWidth;
    private int mLeftImageRHeight;
    /*right--left图片*/
    private Drawable mRightImageL;
    private int mRightImageLWidth;
    private int mRightImageLHeight;
    private boolean noRightImgL;
    /*right文字*/
    private String mRightText;
    private int mRightTextColor;
    private int mRightTextSize;
    private int mRightDrawablePadding;
    private int mRightTextLeftMargins;
    private int mRightTextRightMargins;
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
    /*top*/
    private int mTopGravity;
    private int mTopBetweenMargins;
    private int mTop_leftMargins;
    private int mTop_topMargins;
    private int mTop_bottomMargins;
    private int mTop_rightMargins;
    private boolean noTop;
    private boolean isTopBetween;
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
    private int mBottomBetweenMargins;
    private boolean noBottom;
    private boolean isBottomBetween;
    private int mBottom_leftMargins;
    private int mBottom_topMargins;
    private int mBottom_bottomMargins;
    private int mBottom_rightMargins;
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
    private CheckBox checkBox;

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
        noLeftImgL = typedArray.getBoolean(R.styleable.MySuperItem_noLeftImageL, false);
        /*left文字*/
        mLeftText = typedArray.getString(R.styleable.MySuperItem_left_text);
        mLeftTextColor = typedArray.getColor(R.styleable.MySuperItem_left_text_color, defaultColor);
        mLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_size, defaultSize);
        mLeftDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_drawable_padding, defaultDrawablePadding);
        mLeftTextLeftMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_leftMargins, defaultMargins_0);
        mLeftTextRightMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_text_rightMargins, defaultMargins_20);
        noLeftText = typedArray.getBoolean(R.styleable.MySuperItem_noLeftText, false);
        /*left--right图片*/
        mLeftImageR = typedArray.getDrawable(R.styleable.MySuperItem_left_rimg);
        mLeftImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_width, -1);
        mLeftImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_left_rimg_height, -1);
        /*right--left图片*/
        mRightImageL = typedArray.getDrawable(R.styleable.MySuperItem_right_limg);
        mRightImageLWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_width, -1);
        mRightImageLHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_limg_height, -1);
        noRightImgL = typedArray.getBoolean(R.styleable.MySuperItem_noRightImgL, false);
        /*right文字*/
        mRightText = typedArray.getString(R.styleable.MySuperItem_right_text);
        mRightTextColor = typedArray.getColor(R.styleable.MySuperItem_right_text_color, defaultColor);
        mRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_size, defaultSize);
        mRightDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_drawable_padding, defaultDrawablePadding);
        mRightTextLeftMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_leftMargins,defaultMargins_20 );
        mRightTextRightMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_text_rightMargins, defaultMargins_0);
        noRightText = typedArray.getBoolean(R.styleable.MySuperItem_noRightText, false);
        /*right--right图片*/
        mRightImageR = typedArray.getDrawable(R.styleable.MySuperItem_right_rimg);
        mRightImageRWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_width, -1);
        mRightImageRHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_right_rimg_height, -1);
        isArrow = typedArray.getBoolean(R.styleable.MySuperItem_isArrow, false);
        /*CheckBox*/
        isCheckBox = typedArray.getBoolean(R.styleable.MySuperItem_isCheckBox, false);
        mCheckBoxWidth = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_width, 80);
        mCheckBoxHeight = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_checkBox_height, 80);
        /*top*/
        mTopGravity = typedArray.getInteger(R.styleable.MySuperItem_top_gravity, Gravity.LEFT);
        mTopBetweenMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_betweenMargins, 20);
        mTop_leftMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_leftMargin, 0);
        mTop_topMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_topMargin, 0);
        mTop_bottomMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_bottomMargin, 0);
        mTop_rightMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_top_rightMargin, 0);
        noTop = typedArray.getBoolean(R.styleable.MySuperItem_noTop, false);
        isTopBetween = typedArray.getBoolean(R.styleable.MySuperItem_isTopBetween, false);
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
        mBottomBetweenMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_betweenMargins, 20);
        mBottom_topMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_topMargin, 0);
        mBottom_leftMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_leftMargin, 0);
        mBottom_bottomMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_bottomMargin, 0);
        mBottom_rightMargins = typedArray.getDimensionPixelSize(R.styleable.MySuperItem_bottom_rightMargin, 0);
        noBottom = typedArray.getBoolean(R.styleable.MySuperItem_noBottom, false);
        isBottomBetween = typedArray.getBoolean(R.styleable.MySuperItem_isBottomBetween, false);
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
        this.setGravity(Gravity.CENTER);

        initLeftImage();
        initLeftText();
        initCenter();
        initRightImage();
        initRightText();
        initCheckBox();
    }

    /*-----------------------------------------初始化布局------------------------------------------------*/
    /*left---left图片*/
    private void initLeftImage() {
        if (noLeftImgL)return;
        leftImageL = new ImageView(context);
        initImage(leftImageL, mLeftImageLWidth, mLeftImageLHeight, mLeftImageL, 20, 0);
    }

    /*left文字*/
    private void initLeftText() {
        if (noLeftText)return;
        leftText = new TextView(context);
        initText(leftText, mLeftText, mLeftTextSize, mLeftTextColor, mLeftImageR, mLeftImageRWidth, mLeftImageRHeight, mLeftDrawablePadding, mLeftTextLeftMargins,mLeftTextRightMargins);
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
        initCenterLayout(topLayout, mTopGravity, mTop_leftMargins, mTop_topMargins, mTop_rightMargins, mTop_bottomMargins);

        topLeftText = new TextView(context);
        initCenterLeft(topLeftText, mTopLeftText, mTopLeftTextSize, mTopLeftTextColor, mTopBetweenMargins,isTopBetween);
        topRightText = new TextView(context);
        initCenterRight(topRightText, mTopRightText, mTopRightTextSize, mTopRightTextColor, mTopBetweenMargins,isTopBetween);
        goneText(mTopRightText,topRightText);

        topLayout.addView(topLeftText);
        topLayout.addView(topRightText);
        centerLayout.addView(topLayout);
    }
    /*bottom*/
    private void initBottom() {
        if (noBottom)return;
        bottomLayout = new LinearLayout(context);
        initCenterLayout(bottomLayout, mBottomGravity,mBottom_leftMargins,mBottom_topMargins,mBottom_rightMargins,mBottom_bottomMargins);

        if (mBottomLeftText == null && mBottomRightText == null) bottomLayout.setVisibility(GONE);

        bottomLeftText = new TextView(context);
        initCenterLeft(bottomLeftText, mBottomLeftText, mBottomLeftTextSize, mBottomLeftTextColor, mBottomBetweenMargins,isBottomBetween);
        bottomRightText = new TextView(context);
        initCenterRight(bottomRightText, mBottomRightText, mBottomRightTextSize, mBottomRightTextColor, mBottomBetweenMargins,isBottomBetween);
        goneText(mBottomRightText,bottomRightText);

        bottomLayout.addView(bottomLeftText);
        bottomLayout.addView(bottomRightText);
        centerLayout.addView(bottomLayout);
    }

    /*right---left图片*/
    private void initRightImage() {
        if (noRightImgL)return;
        rightImageL = new ImageView(context);
        initImage(rightImageL, mRightImageLWidth, mRightImageLHeight, mRightImageL, 0, 20);
    }

    /*right文字*/
    private void initRightText() {
        if (noRightText)return;
        rightText = new TextView(context);
        if (!isArrow) {
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, mRightImageR, mRightImageRWidth, mRightImageRHeight, mRightDrawablePadding, mRightTextLeftMargins,mRightTextRightMargins);
            goneText(mRightText, rightText);
        } else
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, context.getResources().getDrawable(R.mipmap.arrow_right), 30, 60, 20, mRightTextLeftMargins,mRightTextRightMargins);
    }
    /*CheckBox*/
    private void initCheckBox() {
        if (isCheckBox) {
            checkBox = new CheckBox(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(mCheckBoxWidth,mCheckBoxHeight);
            lp.setMargins(20,0,0,0);
            checkBox.setLayoutParams(lp);
            checkBox.setButtonDrawable(null);
            checkBox.setBackgroundResource(R.drawable.item_choose);

            addView(checkBox);
        }
    }

/*-------------------------------------------公共方法----------------------------------------------*/

    private void initCenterLayout(LinearLayout layout, int gravity,int leftMargin,int topMargin,int rightMargin,int bottomMargin) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
        layout.setLayoutParams(lp);
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(gravity);
    }

    private void initCenterLeft(TextView textView, String content, int size, int color, int margins,boolean isBetween) {
        LinearLayout.LayoutParams lp = null;
        if (isBetween) {
            lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
            lp.setMargins(0, 0, margins, 0);
        }else lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
    }

    private void initCenterRight(TextView textView, String content, int size, int color, int margins,boolean isBetween) {
        LinearLayout.LayoutParams lp = null;
        if (isBetween) lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        else {
            lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
            lp.setMargins(margins, 0, 0, 0);
        }
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
    }

    private void initText(TextView textView, String content, int textSize, int textColor, Drawable drawable, int drawableWidth, int drawableHight, int drawablePadding, int leftMargins,int rightMargins) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(leftMargins, 0, rightMargins, 0);
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

    private void initImage(ImageView imageView, int imageWidth, int imageHeight, Drawable drawable, int rightMargins, int leftMargins) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        lp.setMargins(leftMargins, 0, rightMargins, 0);
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

    public MySuperItem setOnClickCheckBox(final ICheckBoxClick iCheckBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iCheckBox.onClick(buttonView,isChecked);
            }
        });
        return this;
    }

    public interface IItemClick {
        void onClick(View view);
    }

    public interface ICheckBoxClick {
        void onClick(CompoundButton buttonView, boolean isChecked);
    }
}
//    Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.note_play);
//    ImageSpan imgSpan = new ImageSpan(context,bitmap);
//    SpannableString spanString = new SpannableString("icon");
//        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                vh.outline1.append(spanString);
//                vh.outline2.append(spanString);