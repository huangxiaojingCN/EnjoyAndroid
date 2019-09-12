package com.hxj.enjoyandroid.views.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hxj.enjoyandroid.R;

/**
 *  颜色矩阵过滤
 */
public class ColorMatrixView extends View {
    public static final String TAG = "ColorMatrixView";

    private Bitmap mBitmap;

    private Paint mPaint;

    /**
     *  默认
     */
    private float[] mColorMatrix = new float[] {
           1, 0, 0, 0, 0,
           0, 1, 0, 0, 0,
           0, 0, 1, 0, 0,
           0, 0, 0, 1, 0
    };

    public ColorMatrixView(Context context) {
        this(context, null);
    }

    public ColorMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(mColorMatrix);
        mPaint.setColorFilter(colorFilter);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,
                (float) (getWidth() /2 - mBitmap.getWidth() / 2),
                (float) (getHeight() / 2 - mBitmap.getHeight() / 2),
                mPaint);
    }

    public void setColorMatrix(float[] colorMatrix) {
        Log.e(TAG, "setColorMatrix: " + colorMatrix);
        if (colorMatrix != null && colorMatrix.length == 20) {
            Log.i(TAG, "setColorMatrix: " + colorMatrix.length);
            mColorMatrix = colorMatrix;
            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(mColorMatrix);
            mPaint.setColorFilter(colorFilter);


            invalidate();
        }
    }
}
