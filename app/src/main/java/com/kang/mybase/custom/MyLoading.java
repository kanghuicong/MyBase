package com.kang.mybase.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.kang.mybase.R;

/**
 * Created by KangHuiCong on 2017/12/27.
 * E-Mail is 515849594@qq.com
 */

public class MyLoading extends View {
    Paint mPaint;
    int mWidth;
    int mHeight;
    int mCenterX;
    int mCenterY;
    int mLineLength;
    int mLineWidth;
    int control = 1;
    ValueAnimator valueAnimator;

    public MyLoading(Context context) {
        this(context,null);
    }

    public MyLoading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setValueAnimator();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLoading);
        mLineLength = typedArray.getDimensionPixelSize(R.styleable.MyLoading_height,15);
        mLineWidth = typedArray.getDimensionPixelSize(R.styleable.MyLoading_width,5);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.Grey));
        mPaint.setStrokeWidth(mLineWidth);

        for (int i=0;i<12;i++) {
            mPaint.setAlpha(((i + 1 + control) % 12 * 255) / 12);
            canvas.drawLine(mCenterX, mCenterY - mLineLength, mCenterX, mCenterY - mLineLength * 2, mPaint);
            canvas.rotate(30, mCenterX, mCenterY);
        }
    }

    private void setValueAnimator() {
        valueAnimator = ValueAnimator.ofInt(12, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                control = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void stopAnimator() {
        valueAnimator.end();
    }

    public void startAnimator() {
        valueAnimator.start();
    }

}
