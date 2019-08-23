package com.hxj.enjoyandroid.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hxj.enjoyandroid.R;

public class PercentView extends View {

    public static final String TAG = "MyView";

    private Bitmap mBackgroundImage;

    /**
     *  背景图片的宽度
     */
    private int mBitmapWidth;

    /**
     * 背景图片的高度
     */
    private int mBitmapHeight;

    /**
     *  原始图片，白色输入框在原图的绝对位置
     */
    private int mOldTop = 476;

    /**
     *  原始图片，白色输入框在原图的绝对位置
     */
    private int mOldLeft = 282;

    /**
     *  原始图片，白色输入框在原图的绝对位置
     */
    private int mOldRight = 736;

    /**
     *  原始图片，白色输入框在原图的绝对位置
     */
    private int mOldBottom = 1140;

    /**
     *  原始图片的宽
     */
    private int mOldWidth = 1080;

    /**
     *  原始图片的高
     */
    private int mOldHeight = 1619;

    private int mleft;

    private int mTop;

    private int mRight;

    private int mBottom;

    private Paint mPaint;

    public PercentView(Context context) {
        this(context, null);
    }

    public PercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 对图片缩放处理.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background, options);

        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
        mBitmapWidth = mBackgroundImage.getWidth();
        mBitmapHeight = mBackgroundImage.getHeight();

        // 根据背景图片空白框的比列计算位置.
        mleft = mBitmapWidth * mOldLeft / mOldWidth;
        mTop = mBitmapHeight * mOldTop / mOldHeight;
        mRight = mBitmapWidth * mOldRight / mOldWidth;
        mBottom = mBitmapHeight * mOldBottom / mOldHeight;


        // 初始化画笔.
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBitmapWidth, mBitmapHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制背景图片.
        canvas.drawBitmap(mBackgroundImage, 0, 0, null);

        // 绘制红色输入框边界.
        canvas.drawRect(mleft, mTop, mRight, mBottom, mPaint);

        // 绘制白色输入框中的圆
        mPaint.setColor(Color.parseColor("#E2C0D6"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle( (float) (mRight - mleft) / 2 + mleft,
                (float) (mBottom - mTop) / 2 + mTop,
                (float) (mRight - mleft) / 2,
                mPaint);
    }
}
