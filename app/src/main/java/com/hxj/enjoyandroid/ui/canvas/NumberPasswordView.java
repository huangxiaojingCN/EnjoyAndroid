package com.hxj.enjoyandroid.ui.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hxj.enjoyandroid.R;

/**
 *  为了实现密码输入，这里采用继承 view 的方式
 */
public class NumberPasswordView extends View {

    public static final String TAG = "PasswordView";

    /**
     *  默认的密码个数为 6
     */
    private int defaultSize = 6;

    /**
     *  密码宽的高度.
     */
    private int mBoxWidth;

    /**
     *  当前的密码个数.
     */
    private int mPasswordSize;

    private Paint mPaint;

    public NumberPasswordView(Context context) {
        this(context, null);
    }

    public NumberPasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberPasswordView);
        try {
            mPasswordSize = a.getInteger(R.styleable.NumberPasswordView_password_numbers, defaultSize);
            mBoxWidth = a.getDimensionPixelSize(R.styleable.NumberPasswordView_password_box_width, 0);

            if (mBoxWidth < 0) {
                throw new IllegalArgumentException("传递的宽度不能小于0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = mBoxWidth;
        int height = 0;

        if (mPasswordSize > 0) {
            height = mBoxWidth / mPasswordSize;
        } else {
            height = mBoxWidth / defaultSize;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制 border
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#CDCDCD"));
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10, mPaint);

        // 绘制圆角矩形背景
        mPaint.setColor(Color.parseColor("#EEEEEE"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10, mPaint);

        // 绘制中间分割线
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.parseColor("#CDCDCD"));
        Log.i(TAG, "mPasswordSize: " + mPasswordSize);
        for (int i = 1; i <= mPasswordSize - 1; i++) {
            int startX = getWidth() / mPasswordSize * i;
            canvas.drawLine(startX - 2, 0, startX,  getHeight(), mPaint);
        }
    }
}
