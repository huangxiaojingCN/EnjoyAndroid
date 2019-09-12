package com.hxj.enjoyandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MotionEventView extends View {

    private Paint mPaint;

    private int mWidth;

    private int mHeight;

    private int radius = 80;

    /**
     *  圆改变的位置.
     */
    private PointF mPoint;

    private boolean mHasPressed;

    public MotionEventView(Context context) {
        this(context, null);
    }

    public MotionEventView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

        mPoint = new PointF();
    }

    private void initPaint() {
        // 初始化画笔并设置抗拒址以及抗抖动.
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 设置填充.
        mPaint.setStyle(Paint.Style.FILL);
        // 设置画笔的颜色
        mPaint.setColor(Color.parseColor("#E2C0D6"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        // 开始将原点设置为 view 的中心.
        mPoint.set((float) mWidth / 2, (float) mHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mPoint.x, mPoint.y, radius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时需要检查是否在圆上
                mHasPressed = hasPressed(event);
                if (mHasPressed) {
                    Toast.makeText(getContext(), "点击到圆上", Toast.LENGTH_SHORT).show();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (mHasPressed) {
                    mPoint.set(event.getX(), event.getY());
                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mPoint.set((float) mWidth / 2, (float) mHeight / 2);
                mHasPressed = false;
                invalidate();
                break;
        }


        return true; // 设置 true 会消费事件
    }

    /**
     *  检查是否 down 事件在圆上
     * @param event
     * @return
     */
    public boolean hasPressed(MotionEvent event) {
        // 当前按下的位置距离 view 的左上角位置.
        float x = event.getX();
        float y = event.getY();

        // 要判断一个点是否在圆上，根据公式 按下的点和圆心的距离小于等于半径, 实际上就是初中学的勾股定理
        double distance = Math.sqrt(Math.pow(x - mPoint.x, 2) + Math.pow(y - mPoint.y, 2));
        if (distance < radius) {
            return true;
        }

        return false;
    }
}
