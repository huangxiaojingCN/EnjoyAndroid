package com.hxj.enjoyandroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class ScreenCoordinateView extends View {

    private Paint mPaint;

    /**
     *  view 的宽度
     */
    private int mWidth;

    private int mHeight;

    /**
     *  矩形的宽度
     */
    private int rectWidth = 400;

    /**
     *  矩形的高度.
     */
    private int rectHeight = 300;

    private int mHalfWidth;

    private int mHalfHeight;


    private TextPaint mTextPaint;

    public ScreenCoordinateView(Context context) {
        this(context, null);
    }

    public ScreenCoordinateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenCoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        // 初始化画笔并设置抗锯齿以及抗抖动.
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 设置描边，共有三种模式, FILL(填充), STROKE(描边), FILL_AND_STROKE(填充且描边)
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔的颜色.
        mPaint.setColor(Color.BLACK);
        // 设置画笔的粗细
        mPaint.setStrokeWidth(3);

        // 绘制文字的画笔
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(30);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        mHalfWidth = mWidth / 2;
        mHalfHeight = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 由于屏幕的坐标原点在屏幕的左上角. 在这里就是view 的左上角。因此我们将坐标系原点移到 view 的中点.
        canvas.translate(mHalfWidth, mHalfHeight);

        // 绘制原点.
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 10,mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        // 绘制正半轴， 即第一象限. x 为 +, y 为 +
        canvas.drawLine(0, 0, mHalfWidth, 0, mPaint);
        canvas.drawLine(0, 0, 0,mHalfHeight, mPaint);

        // 并绘制第一象限文本.
        String text1 = "(+, +)";
        Rect text1Rect = new Rect();
        mTextPaint.getTextBounds(text1, 0, text1.length(), text1Rect);
        canvas.drawText(text1, rectWidth / 2 / 2 - text1Rect.width() / 2,
                rectHeight / 2 / 2 + text1Rect.height() / 2,
                mTextPaint);

        // 绘制第二象限，x 为 -，y 为 +. 不建议在 onDraw 中重建对象.
        mPaint.setPathEffect(new DashPathEffect(new float[] {
                8, 8
        }, 0));
        canvas.drawLine(-mHalfWidth, 0, 0, 0, mPaint);

        // 并绘制第二象限文本.
        String text2 = "(-, +)";
        Rect text2Rect = new Rect();
        mTextPaint.getTextBounds(text2, 0, text2.length(), text2Rect);
        canvas.drawText(text2, -rectWidth / 2 / 2 - text2Rect.width() / 2,
                rectHeight / 2 / 2 + text2Rect.height() / 2,
                mTextPaint);

        // 绘制第三象限, x 为 -, y 为 -
        canvas.drawLine(0, -mHalfHeight, 0, 0, mPaint);
        // 并绘制第三象限文本.
        String text3 = "(-, -)";
        Rect text3Rect = new Rect();
        mTextPaint.getTextBounds(text3, 0, text3.length(), text3Rect);
        canvas.drawText(text3, -rectWidth / 2 / 2 - text3Rect.width() / 2,
                -rectHeight / 2 / 2 + text3Rect.height() / 2,
                mTextPaint);

        // 并绘制第四象限文本.
        String text4 = "(+, -)";
        Rect text4Rect = new Rect();
        mTextPaint.getTextBounds(text4, 0, text4.length(), text4Rect);
        canvas.drawText(text4, rectWidth / 2 / 2 - text4Rect.width() / 2,
                -rectHeight / 2 / 2 + text4Rect.height() / 2,
                mTextPaint);

        // 清除绘制的虚线.
        mPaint.setPathEffect(null);
        // 绘制在 view 居中的矩形. 用来描述坐标系.
        canvas.drawRect(
                -rectWidth / 2,
                -rectHeight / 2,
                rectWidth / 2,
                 rectHeight / 2,
                mPaint);

        // 验证坐标系，绘制四个点. 这里的点用圆来表示, 当然也可以用 drawPoint + setStrokeCap(Paint.Cap.ROUND)
        // 来实现.
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(10);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(120, 120, mPaint);
        canvas.drawCircle(120, 120, 5, mPaint);
        canvas.drawCircle(-120, 120, 5, mPaint);
        canvas.drawCircle(-120, -120, 5, mPaint);
        canvas.drawCircle(120, -120, 5, mPaint);
    }
}
