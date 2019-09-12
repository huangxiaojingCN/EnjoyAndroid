package com.hxj.enjoyandroid.views.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.hxj.enjoyandroid.R;

/**
 *  基础的 Canvas API, 包含点，线段，圆，矩形，椭圆，颜色填充，画笔设置大小， 文字
 */
public class CanvasBasicView extends View {

    private Bitmap mGrilBitmap;

    private Paint mPaint;

    private TextPaint mTextPaint;

    public CanvasBasicView(Context context) {
        this(context, null);
    }

    public CanvasBasicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

        mGrilBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
    }

    private void initPaint() {
        // 初始化画笔
        mPaint = new Paint();
        // 设置画笔的颜色.
        mPaint.setColor(Color.parseColor("#FD7575"));
        // 设置画笔的填充样式，共有三种: FILL、STROKE、FILL_AND_STROKE，分别是填充，描边，填充和描边
        mPaint.setStyle(Paint.Style.FILL);

        // 设置抗锯齿
        mPaint.setAntiAlias(true);
        // 设置抗抖动
        mPaint.setDither(true);

        // 设置画笔会出线段的形状，有 ROUND、BUTT、SQUARE
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        // 设置画笔的粗细
        mPaint.setStrokeWidth(20);

        // 设置线段连接处的形状, 也有三种方式. MITER、ROUND、BEVEL
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        // 设置文字的大小.
        mPaint.setTextSize(20);

        // 创建文本画笔.
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制点.
        canvas.drawPoint(10, 10, mPaint);

        // 绘制线段.
        canvas.drawLine(40, 20, 200, 20, mPaint);

        // 绘制圆
        canvas.drawCircle(100, 130, 50, mPaint);

        // 绘制矩形.
        mPaint.setStrokeWidth(5);
        canvas.drawRect(10, 220, 210, 440, mPaint);

        canvas.drawRoundRect(10, 220, 210, 440, 10, 10, mPaint);

        // 绘制椭圆.
        canvas.drawOval(10, 500, 210, 600, mPaint);

        // 绘制文本.
        String text =  "Hello world";
        Rect textRect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), textRect);
        canvas.drawText(text, 10, 650, mTextPaint);

        // 绘制多个点, 不建议在 onDraw 中创建对象，因为 onDraw 方法会被多次调用.
        mPaint.setStrokeWidth(10);
        canvas.drawPoints(new float[] {
                10, 700,
                50, 750,
                100, 800,
        }, 2, 2, mPaint);

        // 绘制多条线段
        canvas.drawLines(new float[] {
                10, 850, 210, 850,
                10, 900, 210, 900,
                10, 950, 210, 950
        }, mPaint);

        // 绘制圆弧, 如果 userCenter 设置为 true 则为扇形.
        canvas.drawArc(300, 20, 500, 220,
                0,
                60,
                true,
                mPaint);

        // 绘制图片.
        canvas.drawBitmap(mGrilBitmap, 300, 50, null);
    }
}
