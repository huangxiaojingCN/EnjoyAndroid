package com.hxj.enjoyandroid.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hxj.enjoyandroid.model.PieBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 彬状图
 */
public class PieView<T extends PieBean> extends View {

    public static final String TAG = "PieView";

    private Paint mPaint;

    private TextPaint mTextPaint;

    private List<T> pieDatas = new ArrayList<>();

    /**
     *  各圆饼之间的间距.
     */
    private int space = 3;

    /**
     *  圆饼个数.
     */
    private int pieCount;

    private int mWidth;

    private int mHeight;

    private int mPieWidth;

    private int mPieHeight;


    private int mTempAngle;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setTextSize(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        mPieWidth = mWidth / 2;
        mPieHeight = mHeight /2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < pieCount; i++) {
            PieBean bean = pieDatas.get(i);
            int count = bean.value;
            int max = bean.max;
            String item = bean.item;
            String color = bean.color;

            int realAngle = count * (360 - pieCount * space) / max;
            int startAngle = 0;
            Log.i(TAG, "onDraw realAngle: " + realAngle);

            if (mTempAngle > 0) {
                startAngle = mTempAngle;
            }

            if (!TextUtils.isEmpty(color)) {
                mPaint.setColor(Color.parseColor(color));
            }

            // 绘制饼状图.
            canvas.drawArc(mWidth / 2 - mPieWidth / 2,
                    mHeight / 2 - mPieHeight / 2,
                    mWidth / 2 + mPieWidth / 2,
                    mHeight / 2 + mPieHeight / 2,
                    startAngle,
                    realAngle,
                    true,
                    mPaint);

            mTempAngle += realAngle;
            mTempAngle += space;
        }
    }

    public void addPies(List<T> pieDatas) {
        this.pieDatas = pieDatas;
        if (pieDatas.size() > 0) {
            pieCount = pieDatas.size();
            requestLayout();
        }
    }
}
