package com.hxj.enjoyandroid.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hxj.enjoyandroid.model.HistogramBean;

import java.util.ArrayList;
import java.util.List;

/**
 *  自定义view基础学习，绘制直方图。
 */
public class HistogramView<T extends HistogramBean> extends View {

    public static final String TAG = "HistogramView";

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

    private int DEFAULT_WIDTH = 50;

    private List<T> datas = new ArrayList<>();

    final private List<T> temps = new ArrayList<>();

    private TextPaint mTextPaint;

    /**
     *  x 轴起点x坐标. 这里是为了好理解，实际上 startX == yCoordinateStartX
     *  因为是原点.
     */
    private int startX;
    /**
     *  x 轴起点y坐标
     */
    private int startY;

    /**
     *  y 轴终点 x 坐标
     */
    private int yCoordinateEndX;

    /**
     *  y 轴终点 y 坐标
     */
    private int yCoordinateEndY;


    private int lastX;

    private int availableSpace;

    private int availableColumnSpace;

    private int xScaleStep;


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

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setTextSize(30);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // view 的宽度
        mWidth = w;

        // view 高度.
        mHeight = h;

        // x 轴坐标的宽度
        mXCoordinateWidth = mWidth * 2 / 3;

        // y 轴坐标的高度.
        mYCoordinateHeight = mHeight * 2 /3;
    }

    /**
     *  绘制 x,y 轴
     * @param canvas
     */
    private void drawXYCoordinate(Canvas canvas) {
        // 绘制 x 轴坐标.
        startX = (mWidth - mXCoordinateWidth) / 2;
        startY = mHeight - (mHeight - mYCoordinateHeight) / 2;
        int endX = startX + mXCoordinateWidth;
        int endY = startY;

        canvas.drawLine(startX, startY, endX, endY, mCoordinatePaint);

        // 绘制 y 坐标.
        int yCoordinateStartX = startX;
        int yCoordinateStartY = startY;

        yCoordinateEndX = startX;
        yCoordinateEndY = (mHeight - mYCoordinateHeight) / 2;
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
        canvas.translate(yCoordinateEndX, yCoordinateEndY - 60);
        canvas.drawLine(0, 60, 0, 0, mCoordinatePaint);
        canvas.rotate(60);
        canvas.drawLine(0, 0, 30, 0, mCoordinatePaint);

        canvas.rotate(60);
        canvas.drawLine(0, 0, 30, 0, mCoordinatePaint);
        canvas.restoreToCount(layer1);
    }

    private void drawHistogram(Canvas canvas, int height, int max, String color) {
        // 根据最大值的比列进行缩放.
        int relalHeight = mYCoordinateHeight * height / max;

        int left = startX + space + lastX;
        if (lastX > 0) {
            left = lastX + space;
        }
        int top = (yCoordinateEndY + mYCoordinateHeight - relalHeight);
        int right = (left + availableColumnSpace);
        int bottom = startY - 3;

        if (!TextUtils.isEmpty(color)) {
            mPaint.setColor(Color.parseColor(color));
        }

        canvas.drawRect(left, top, right, bottom, mPaint);
        lastX = right;
    }

    private void drawXScaleValue(Canvas canvas, String columnName) {
        // 绘制 x 轴刻度.
        int xScalex = 0;
        if (xScaleStep > 0) {
            xScalex = xScaleStep;
        } else {
            xScalex = startX + space + availableColumnSpace / 2;
        }
        int xScaley = startY;

        int xScaleEndx = xScalex;
        int xScaleEndy = startY + 10;

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(xScalex, xScaley, xScaleEndx, xScaleEndy, mPaint);

        xScaleStep = xScalex + 2 * availableColumnSpace / 2 + space;

        // 绘制 x 轴刻度值.
        Rect columnNameRect = new Rect();
        mTextPaint.getTextBounds(columnName, 0, columnName.length(), columnNameRect);
        canvas.drawText(columnName,
                xScalex - columnNameRect.width() / 2,
                xScaleEndy + columnNameRect.height(),
                mTextPaint);
    }

    private void drawYScaleValue(Canvas canvas, int yScaleValue, String yScaleValueStr) {
        // 绘制 y 轴刻度.
        // 根据列的数量进行刻度实现.
        int yScaley = yCoordinateEndY + mYCoordinateHeight - yScaleValue;
        int yScalex = yCoordinateEndX;

        int yScaleEndx = yCoordinateEndX - 10;
        int yScaleEndy = yScaley;

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(yScalex, yScaley, yScaleEndx, yScaleEndy, mPaint);

        // 绘制 y 轴刻度值.
        Rect textRect = new Rect();
        mTextPaint.getTextBounds(yScaleValueStr, 0, yScaleValueStr.length(), textRect);
        canvas.drawText(yScaleValueStr, startX - textRect.width() - 20, yScaley + textRect.height() / 2, mTextPaint);
    }

    private void drawColumn(Canvas canvas) {
        // 得到直方图列可绘制区域
        availableSpace = mXCoordinateWidth - totoalSpaces * space;

        // 列的可用宽度, 由于屏幕的宽度有限，如果要更好的适配，并且需要显示多列数据，考虑通过滑动切换的方式.
        // TODO. 如果这里的有效宽度小于 50 像素，则后面的列不显示. 未处理滑动显示.
        availableColumnSpace = availableSpace / columns;
        if (availableColumnSpace < DEFAULT_WIDTH) {
            temps.clear();
            for (int i = 0; i < datas.size(); i++) {
                temps.add(datas.get(i));
                int availableSpaceTemp = 0;
                if (i == 0) {
                    availableSpaceTemp = DEFAULT_WIDTH;
                } else {
                    availableSpaceTemp = i * DEFAULT_WIDTH;
                }

                if (availableSpaceTemp > availableSpace) {
                    break;
                }
            }

            datas = temps;
            columns = datas.size();
            totoalSpaces = columns + 1;
            availableColumnSpace = DEFAULT_WIDTH;
        }

        // 计算最高数值与坐标轴实际高度的比值，
        if (datas.size() > 0) {
            for (int i = 0; i < columns; i++) {
                HistogramBean bean =  datas.get(i);
                int height = bean.height;
                int max = bean.max;
                String color = bean.color;
                String columnName = bean.columnName;

                if (max == 0 || max < height) {
                    throw new IllegalStateException("最大值不能为0");
                }

                // 绘制直方图.
                drawHistogram(canvas, height, max, color);

                // 绘制 x 轴刻度.
                drawXScaleValue(canvas, columnName);

                // 根据列以及最大值来分配刻度值，可以通过计算更换刻度的密度.
                int yScaleValue = (i + 1) * max / columns * mYCoordinateHeight / max;
                String yScaleValueStr = (i + 1) * max / columns + "";
                drawYScaleValue(canvas, yScaleValue, yScaleValueStr);
            }

            // 补画 y 轴 0 刻度
            int yScale0x = yCoordinateEndX;
            int yScale0y = mYCoordinateHeight + yCoordinateEndY;

            int yScaleEnd0x = yCoordinateEndX - 10;
            int yScaleEnd0y = yScale0y;
            canvas.drawLine(yScale0x, yScale0y, yScaleEnd0x, yScaleEnd0y, mPaint);
            // 绘制刻度值.
            String yScaleValueStr = "0";
            Rect textRect = new Rect();
            mTextPaint.getTextBounds(yScaleValueStr, 0, yScaleValueStr.length(), textRect);
            canvas.drawText(yScaleValueStr,
                    startX - textRect.width() - 20,
                    yScale0y + textRect.height() / 2,
                    mTextPaint);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawXYCoordinate(canvas);

        drawColumn(canvas);
    }

    public void addData(List<T> datas) {
        this.datas = datas;
        if (datas.size() > 0) {
            totoalSpaces = datas.size() + 1;
            columns = totoalSpaces - 1;
            requestLayout();
        }
    }
}
