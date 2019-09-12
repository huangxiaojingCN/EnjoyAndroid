package com.hxj.enjoyandroid.views.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import com.hxj.enjoyandroid.R;

public class PaintBasicView extends View {

    private Paint mPaint;

    private int mWidth;

    private int mHeight;
    private Path mPath;

    private Bitmap mBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Bitmap mHeartBitmap;

    private static final float[] COMMON = new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0
    };

    public static final float[] common() {
        return COMMON.clone();
    }

    private static final float[] GREY_SCALE = new float[] {
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] greyScale() {
        return GREY_SCALE.clone();
    }

    private static final float[] INVERT = new float[] {
            -1, 0, 0, 0, 255,
            0, -1, 0, 0, 255,
            0, 0, -1, 0, 255,
            0, 0, 0, 1, 0,
    };

    public static final float[] invert() {
        return INVERT.clone();
    }

    private static final float[] RGB_TO_BGR = new float[] {
            0, 0, 1, 0, 0,
            0, 1, 0, 0, 0,
            1, 0, 0, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] rgbToBgr() {
        return RGB_TO_BGR.clone();
    }

    private static final float[] SEPIA = new float[] {
            0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] sepia() {
        return SEPIA.clone();
    }

    private static final float[] BRIGHT = new float[] {
            1.438F, -0.122F, -0.016F, 0, 0,
            -0.062F, 1.378F, -0.016F, 0, 0,
            -0.062F, -0.122F, 1.483F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] bright() {
        return BRIGHT.clone();
    }

    private static final float[] BLACK_AND_WHITE = new float[] {
            1.5F, 1.5F, 1.5F, 0, -255,
            1.5F, 1.5F, 1.5F, 0, -255,
            1.5F, 1.5F, 1.5F, 0, -255,
            0, 0, 0, 1, 0,
    };

    public static final float[] blackAndWhite() {
        return BLACK_AND_WHITE.clone();
    }

    private static final float[] VINTAGE_PINHOLE = new float[] {
            0.6279345635605994F, 0.3202183420819367F, -0.03965408211312453F, 0, 9.651285835294123F,
            0.02578397704808868F, 0.6441188644374771F, 0.03259127616149294F, 0, 7.462829176470591F,
            0.0466055556782719F, -0.0851232987247891F, 0.5241648018700465F, 0, 5.159190588235296F,
            0, 0, 0, 1, 0
    };

    public static final float[] vintagePinhole() {
        return VINTAGE_PINHOLE.clone();
    }

    private static final float[] KODACHROME = new float[] {
            1.1285582396593525F, -0.3967382283601348F, -0.03992559172921793F, 0, 63.72958762196502F,
            -0.16404339962244616F, 1.0835251566291304F, -0.05498805115633132F, 0, 24.732407896706203F,
            -0.16786010706155763F, -0.5603416277695248F, 1.6014850761964943F, 0, 35.62982807460946F,
            0, 0, 0, 1, 0
    };

    public static final float[] kodachrome() {
        return KODACHROME.clone();
    }

    private static final float[] TECHNICOLOR = new float[] {
            1.9125277891456083F, -0.8545344976951645F, -0.09155508482755585F, 0, 11.793603434377337F,
            -0.3087833385928097F, 1.7658908555458428F, -0.10601743074722245F, 0, -70.35205161461398F,
            -0.231103377548616F, -0.7501899197440212F, 1.847597816108189F, 0, 30.950940869491138F,
            0, 0, 0, 1, 0
    };

    public static final float[] technicolor() {
        return TECHNICOLOR.clone();
    }

    public PaintBasicView(Context context) {
        this(context, null);
    }

    public PaintBasicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        // 创建画笔.
        mPaint = new Paint();
        // 画笔的颜色
        mPaint.setColor(Color.parseColor("#ff00ff"));
//        mPaint.setColor(Color.argb(200, 200, 200,200));
//        mPaint.setARGB(200, 200, 200, 200);
        // 描边即画线,有三种模式: FILL、STROKE、FILL_AND_STROKE
        mPaint.setStyle(Paint.Style.FILL);
        // 线条的宽度，对设置 STROKE 和 FILL_AND_STROKE 有效
        mPaint.setStrokeWidth(50);

        // 设置抗锯齿
//        mPaint.setAntiAlias(true);
        // 设置抖动.
//        mPaint.setDither(true);
        // 线条断点的形状：圆、切割、矩形
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 线条与线条之间，拐角处的形状：圆润、以线条端点进行延伸的交点、
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // 设置文字的大小
//        mPaint.setTextSize(20);
        // 与 setAntiAlias 和 setDither 相同
//        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        //回复默认画笔设置
//        mPaint.reset();
        // 从某个画笔拷贝.
//        mPaint.set();

//
//        LinearGradient linearGradient = new LinearGradient(
//                300,
//                300,
//                600,
//                600,
//                Color.RED,
//                Color.BLUE,
//                Shader.TileMode.REPEAT);
//
//        SweepGradient sweepGradient = new SweepGradient(700, 700,
//                new int[]{Color.RED, Color.GREEN, Color.BLUE},
//                new float[]{
//                        0.1f, 0.7f, 1.0f
//                });
//
//
//        RadialGradient radialGradient = new RadialGradient(700, 700, 150,
//                Color.RED, Color.BLUE, Shader.TileMode.MIRROR);

//        mPaint.setShader(radialGradient);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 6;
        BitmapFactory.decodeResource(getResources(), R.drawable.beauty, options);
        options.inJustDecodeBounds = true;

        // 关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);

//        BitmapFactory.Options options1 = new BitmapFactory.Options();
//        options1.inJustDecodeBounds = false;
//        options1.inSampleSize = 3;
//        BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options1);
//        options1.inJustDecodeBounds = true;
     /*   mHeartBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        BitmapShader bitmapShader1 = new BitmapShader(mBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        BitmapShader bitmapShader2 = new BitmapShader(mHeartBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        ComposeShader composeShader = new ComposeShader(bitmapShader2, bitmapShader1, PorterDuff.Mode.DST_OUT);
        mPaint.setShader(composeShader);
*/
        LightingColorFilter colorFilter = new LightingColorFilter(
                Color.parseColor("#ffffff"),
                Color.parseColor("#660000"));

        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(
                Color.parseColor("#ED29AB"),
                PorterDuff.Mode.SRC_OVER);

        float[] colorMatrix = new float[] {
                1,0,0,0,100,
                0,1,0,0,100,
                0,0,1,0,0,
                0,0,0,0,255
        };

        ColorMatrix colorMatrix1 = new ColorMatrix();
//        colorMatrix1.setRotate(1, 180);

        colorMatrix1.setSaturation(100.0f);

        colorMatrix1.setScale(3.0f, 1.0f, 1.0f,1.0f);

        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix1);

        mPaint.setColorFilter(colorMatrixColorFilter);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*mPaint.setStrokeMiter(10);

        mPaint.setColor(Color.parseColor("#3429AB"));
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPath.moveTo(100, 400);
        mPath.lineTo(400, 400);
        mPath.lineTo(200, 600);
        mPath.close();
        //canvas.drawRect(100, 100, 500, 500, mPaint);
        canvas.drawPath(mPath, mPaint);

        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPath.reset();
        mPath.moveTo(500, 400);
        mPath.lineTo(800, 400);
        mPath.lineTo(600, 600);
        mPath.close();
//        canvas.drawLine(500, 400, 800, 400, mPaint);
//        canvas.drawLine(800, 400, 600, 600, mPaint);
        canvas.drawPath(mPath, mPaint);

        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPath.reset();
        mPath.moveTo(900, 400);
        mPath.lineTo(1200, 400);
        mPath.lineTo(800, 600);
        mPath.close();

        canvas.drawPath(mPath, mPaint);*/


//        canvas.drawRect(300, 300, 900, 900, mPaint);

//        canvas.drawCircle(700, 700, 300, mPaint);

//        canvas.drawCircle(400, 400, 250, mPaint);
        canvas.drawBitmap(mBitmap, 300, 300, mPaint);
    }
}
