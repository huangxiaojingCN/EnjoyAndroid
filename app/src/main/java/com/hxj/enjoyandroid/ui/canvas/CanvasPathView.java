package com.hxj.enjoyandroid.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 *  主要介绍 Canvas Path 相关.
 */
public class CanvasPathView extends View {

    private Paint mPaint;

    private Path mPath;

    public CanvasPathView(Context context) {
        this(context, null);
    }

    public CanvasPathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        // 创建画笔并设置抗锯齿和防抖动，等同于 setAntiAlias(true) 和 setDither(true)
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        // 设置画笔颜色
        mPaint.setColor(Color.parseColor("#1375CD"));

        // 设置画笔的宽度
        mPaint.setStrokeWidth(5);

        // 设置画笔画线段时拐角处的样子
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        // 设置画笔为描边
        mPaint.setStyle(Paint.Style.STROKE);

        // 绘制路径,绘制高级的基础, 只有牢固的掌握 path 的用法才能遇到更复杂的问题游刃有余.
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 通过 path 来绘制圆. 最后一个参数代表顺时针还是逆时针画圆，CW 顺时针，CCW 逆时针
        mPath.addCircle(100, 100, 50, Path.Direction.CW);

        // 添加圆弧路径
        mPath.addArc(new RectF(100, 200, 200, 400),
                0, 60);

        // 添加椭圆路径
        mPath.addOval(new RectF(100, 450, 300, 600),
                Path.Direction.CW);

        // 绘制四个圆角的矩形.
        mPath.addRoundRect(new RectF(100, 650,400, 850),
                10,
                10,
                Path.Direction.CW);

        // 绘制二阶贝塞尔曲线
        mPath.moveTo(400, 300);
        mPath.quadTo(600, 100, 700, 300);

        // 三阶贝塞尔曲线.
        mPath.moveTo(400, 500);
        mPath.cubicTo(500, 300, 600, 700, 700, 350);

        mPath.moveTo(100, 900);
        canvas.drawLine(100, 900, 200, 1100, mPaint);
        // 这里值得注意的就是最后一个参数 forceMoveTo: 如果为 false 表示将当前的起点和 path 最后一个点点击起来
        // 可以看到图形变化的过程.
        mPath.arcTo(100, 1100,300, 1200, 0, 60, false);


        // 绘制路径 mPath
        canvas.drawPath(mPath, mPaint);
    }
}
