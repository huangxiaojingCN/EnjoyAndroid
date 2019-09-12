package com.hxj.enjoyandroid.views.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class TextPaintView extends View {

    public static final String TAG = "TextPaintView";

    private String text1 = "抽烟、喝酒、烫头";

    private String text2 = "乡音未改鬓毛衰";

    private String text3 = "儿童相见不相识";

    private String text4 = "笑问客从何处来";


    private char[] chars = new char[] {'我', '是', '中', '国', '人'};

    private String text = "滚滚长江东逝水, 浪花淘尽英雄,是非成败转头, 青山依旧在,几度夕阳红";


    private TextPaint mTextPaint;

    private Rect mTextRect;

    private int mHeight;

    private int mWidth;

    private Paint mPaint;
    private float[] widths;
    private float[] measureWidths = {0};

    private Path mPath;

    private StaticLayout layout;

    public TextPaintView(Context context) {
        this(context, null);
    }

    public TextPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setPathEffect(new CornerPathEffect(20));

        // 创建文本画笔.
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        // 设置文本的大小
        mTextPaint.setTextSize(100);


//        mTextPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "ygyqianmt.ttf"));


        // 设置文本对齐方式.
//        mTextPaint.setTextAlign(Paint.Align.CENTER);

//        // 设置此文字使用地区
//        mTextPaint.setTextLocale(Locale.CHINA);
//        // 在 api 21 以上可以设置多个地区的语言.
////        mTextPaint.setTextLocales(new LocaleList(Locale.CHINA, Locale.US));
//
//        // 设置文字水平缩放.
//        mTextPaint.setTextScaleX(3.0f);
//
//        // 设置文字的错切大小. 大于 0 逆时针， 小于 0 顺时针
//        mTextPaint.setTextSkewX(0.5f);
//
//        // 设置文字是否显示下划线.
//        mTextPaint.setUnderlineText(true);
//
//        // 设置是否显示删除线
//        mTextPaint.setStrikeThruText(true);
//
//        // 设置文字是否加粗，这里的加粗实际上在绘制的时候机进行描边处理
//        mTextPaint.setFakeBoldText(true);

//        mTextPaint.setSubpixelText(true);

//        mTextPaint.setLinearText(true);

//        mTextPaint.setElegantTextHeight(true);

        mPath = new Path();

        for (int i = 0; i < 10; i++) {
            mPath.lineTo((float)(Math.random() * 500 + i * 35), (float)(Math.random() * 500 + 250));
        }

        mTextRect = new Rect();
        widths = new float[chars.length];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        layout = new StaticLayout(text, mTextPaint,
                mWidth,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


       /* canvas.drawPath(mPath, mPaint);

        canvas.drawTextOnPath(text, mPath, 100, 0, mTextPaint);*/


        layout.draw(canvas);

        /*mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            float runAdvance = mTextPaint.getRunAdvance(text,
                    0,
                    text.length(),
                    0,
                    text.length(),
                    false,
                    text.length());

            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(3);
            canvas.drawLine(mTextRect.left + 300 + runAdvance,
                    mTextRect.top + 300,
                    mTextRect.left + 300 + runAdvance,
                    mTextRect.bottom + 300,
                    mPaint);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean b = mTextPaint.hasGlyph("\uD83C\uDDE8\uD83C\uDDF3");
            boolean aa = mTextPaint.hasGlyph("aa");
            boolean ab = mTextPaint.hasGlyph("ab");

            Log.i(TAG, "b: " + b);
            Log.i(TAG, "aa: " + aa);
            Log.i(TAG, "ab: " + ab);

        }

        canvas.drawText(text, 300, 300, mTextPaint);*/

    /*    Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        Log.i(TAG, "bottom - top + leading: " + (fontMetrics.bottom - fontMetrics.top + fontMetrics.leading));
        Log.i(TAG, "descent - ascent: " + (fontMetrics.descent - fontMetrics.ascent));
        Log.i(TAG, "getFontSpacing: " + mTextPaint.getFontSpacing());

        mTextPaint.getTextBounds(text1, 0, text1.length(), mTextRect);
        Log.i(TAG, "width: " + mTextRect.width());
        Log.i(TAG, "height: " + mTextRect.height());

        canvas.drawText(text1, 300, 300, mTextPaint);


        mPaint.setColor(Color.BLACK);
        canvas.drawRect(mTextRect.left + 300,
                mTextRect.top + 300,
                mTextRect.right + 300,
                mTextRect.bottom + 300, mPaint);*/

       /* mTextPaint.getTextBounds(chars, 0, chars.length, mTextRect);
        Log.i(TAG, "width: " + mTextRect.width());
        Log.i(TAG, "height: " + mTextRect.height());

        float measureTextWidth = mTextPaint.measureText(chars, 0, chars.length);
        Log.i(TAG, "measureTextWidth: " + measureTextWidth);

        mTextPaint.getTextWidths(chars, 0, 3, widths);
        Log.i(TAG, "width: " + widths[3]);


        int number = mTextPaint.breakText(chars, 0, chars.length, 400, measureWidths);
        Log.i(TAG, "breakText width: " + measureWidths[0]);

        canvas.drawText(chars, 0, chars.length , 300, 300, mTextPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            float runAdvance = mTextPaint.getRunAdvance(chars,
                    0,
                    3,
                    0,
                    chars.length,
                    false,
                    2);
            Log.i(TAG, "runAdvance: " + runAdvance);

            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(3);
            canvas.drawLine(mTextRect.left + runAdvance + 300, mTextRect.top + 300,
                    mTextRect.left + runAdvance + 300, mTextRect.bottom + 300, mPaint);
        }*/

      /*  mPaint.setColor(Color.BLACK);
        canvas.drawRect(mTextRect.left + 300,
                mTextRect.top + 300,
                mTextRect.right + 300,
                mTextRect.bottom + 300, mPaint);*/


       /* canvas.drawText(text2, 300, 300 + mTextPaint.getFontSpacing(), mTextPaint);
        canvas.drawText(text3, 300, 300 + 2 * mTextPaint.getFontSpacing(), mTextPaint);
        canvas.drawText(text4, 300, 300 + 3 * mTextPaint.getFontSpacing(), mTextPaint);*/

       /* mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, (float)(mWidth / 2 - width / 2),
                400 + height,
                mTextPaint);

        mTextPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(text, (float)(mWidth / 2 - width / 2) ,
                400 + 2 * height,
                mTextPaint);*/

    }
}
