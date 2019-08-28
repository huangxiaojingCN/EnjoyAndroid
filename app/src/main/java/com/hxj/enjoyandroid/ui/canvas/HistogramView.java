package com.hxj.enjoyandroid.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hxj.enjoyandroid.model.HistogramBean;

import java.util.ArrayList;
import java.util.List;

/**
 *  自定义view基础学习，绘制直方图。
 */
public class HistogramView<T extends HistogramBean> extends View {

    /**
     *  直方图之间的间隙
     */
    private int space = 30;

    private Paint mPaint;

    /**
     *  绘制坐标系.
     */
    private Paint mCoordinatePaint;

    private int mWidth;

    private int mHeight;

    private int mXCoordinateWidth;

    private int mYCoordinateHeight;

    private int columns = 5;

    private int totoalSpaces = 6;

    private List<T> datas = new ArrayList<>();

    private Matrix mMatrix;

    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        // 创建画笔，设置抗拒址和防抖动.
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 设置画笔颜色
        mPaint.setColor(Color.parseColor("#1375CD"));
        // 设置画笔填充样式为 STROKE.
        mPaint.setStyle(Paint.Style.FILL);

        mCoordinatePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mCoordinatePaint.setColor(Color.BLACK);
        mCoordinatePaint.setStrokeWidth(3);

        // 用以图形变换操作.
        mMatrix = new Matrix();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mXCoordinateWidth = mWidth * 2 / 3;

        mYCoordinateHeight = mHeight * 2 /3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*if (datas.size() == 0) {
            return;
        }



        // 得到直方图列可绘制区域
        int avialableSpace = mXCoordinateWidth - totoalSpaces * space;
        // 列的可用控件
        int avaiableColumnSpace = avialableSpace / columns;*/

        // 情况变化操作
        mMatrix.reset();

        // 绘制 x 轴坐标.
        int startX = (mWidth - mXCoordinateWidth) / 2;
        int startY = mHeight - (mHeight - mYCoordinateHeight) / 2;
        int endX = startX + mXCoordinateWidth;
        int endY = startY;

        canvas.drawLine(startX, startY, endX, endY, mCoordinatePaint);

        // 绘制 y 坐标.
        int yCoordinateStartX = startX;
        int yCoordinateStartY = startY;

        int yCoordinateEndX = startX;
        int yCoordinateEndY = (mHeight - mYCoordinateHeight) / 2;
        canvas.drawLine(yCoordinateStartX, yCoordinateStartY, yCoordinateEndX, yCoordinateEndY, mCoordinatePaint);

        // x 轴箭头
        int layer = canvas.saveLayer(0, 0, mWidth, mHeight, null);
        canvas.translate(endX + 30, endY);
        canvas.drawLine(-30, 0, 0, 0, mCoordinatePaint);

        canvas.rotate(150);
        canvas.drawLine(0, 0, 30, 0, mCoordinatePaint);

        canvas.rotate(-150);
        canvas.rotate(210);
        canvas.drawLine(0, 0, 30, 0, mCoordinatePaint);
        canvas.restoreToCount(layer);

        // y 轴箭头
        int layer1 = canvas.saveLayer(0, 0, mWidth, mHeight, null);
        canvas.translate(yCoordinateEndX, yCoordinateEndY - 30);
        canvas.drawLine(0, 30, 0, 0, mCoordinatePaint);
        canvas.rotate(60);
        canvas.drawLine(0, 0, 30, 0, mCoordinatePaint);

        canvas.rotate(60);
        canvas.drawLine(0, 0, 30, 0, mCoordinatePaint);
        canvas.restoreToCount(layer1);

        // 绘制直方图数据.
        // 得到直方图列可绘制区域
        int avialableSpace = mXCoordinateWidth - totoalSpaces * space;
        // 列的可用控件
        int avaiableColumnSpace = avialableSpace / columns;

        // 计算最高数值与坐标轴实际高度的比值，
        if (datas.size() > 0) {
            int lastX = 0;
            for (int i = 0; i < columns; i++) {
                HistogramBean bean =  datas.get(i);
                int height = bean.height;
                int max = bean.max;
                // 按着最大值的比列进行缩放.
                int relalHeight = mYCoordinateHeight * height / max;

                int left = startX + space + lastX;
                if (lastX > 0) {
                    left = lastX + space;
                }
                int top = (yCoordinateEndY + mYCoordinateHeight - relalHeight);
                int right = (left + avaiableColumnSpace);
                int bottom = startY;

                canvas.drawRect(left, top, right, bottom, mPaint);
                lastX = right;
            }

        }

        /*if (datas.size() > 0) {
            for (int i = 1; i <= columns; i++) {

                int left = (startX + space) * i;
                int top = (yCoordinateEndY + 100);
                int right = (left + avaiableColumnSpace);
                int bottom = startY;

                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }*/

        /*int lastX = 0;
        for (int i = 1; i <= columns; i++) {
            int left = startX + space + lastX;
            if (lastX > 0) {
                left = lastX + space;
            }
            int top = (yCoordinateEndY + 100 / i + 100);
            int right = (left + avaiableColumnSpace);
            int bottom = startY;

            canvas.drawRect(left, top, right, bottom, mPaint);
            lastX = right;
        }*/
    }

    public void addData(List<T> datas) {
        // TODO 做数据比较.
        this.datas = datas;
        if (datas.size() > 0) {
            totoalSpaces = datas.size() + 1;
            columns = totoalSpaces - 1;
            requestLayout();
        }
    }
}
