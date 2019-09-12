package com.hxj.enjoyandroid.views.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import com.hxj.enjoyandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  为了实现密码输入，这里采用继承 view 的方式
 */
public class NumberPasswordView extends View implements View.OnKeyListener {

    public static final String TAG = "NumberPasswordView";

    /**
     *  默认的密码个数为 6
     */
    private int defaultSize = 6;

    /**
     *  隐私密码
     */
    private int pointRadius = 14;

    /**
     *  密码宽的高度.
     */
    private int mBoxWidth;

    /**
     *  当前的密码个数.
     */
    private int mPasswordSize;

    private Paint mPaint;

    private List<String> passwordText;

    /**
     *  软件盘管理
     */
    private InputMethodManager im;

    private OnPasswordChangeListener mOnPasswordChangeListener;

    public NumberPasswordView(Context context) {
        this(context, null);
    }

    public NumberPasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberPasswordView);
        try {
            mPasswordSize = a.getInteger(R.styleable.NumberPasswordView_password_numbers, defaultSize);
            mBoxWidth = a.getDimensionPixelSize(R.styleable.NumberPasswordView_password_box_width, 0);

            passwordText = new ArrayList<>();

            if (mBoxWidth < 0) {
                throw new IllegalArgumentException("传递的宽度不能小于0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

        setFocusableInTouchMode(true);
        setFocusable(true);

        initPaint();
        initSoftManager();
        initKeyListener();
    }

    private void initKeyListener() {
        this.setOnKeyListener(this);
    }

    private void initSoftManager() {
        im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = mBoxWidth;
        int height = 0;

        if (mPasswordSize > 0) {
            height = mBoxWidth / mPasswordSize;
        } else {
            height = mBoxWidth / defaultSize;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawDivideLines(canvas);
        drawSecrecyPoint(canvas);
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        // 绘制 border
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#CDCDCD"));
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10, mPaint);

        // 绘制圆角矩形背景
        mPaint.setColor(Color.parseColor("#EEEEEE"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10, mPaint);
    }

    /**
     * 绘制密码之间分割线
     * @param canvas
     */
    private void drawDivideLines(Canvas canvas) {
        // 绘制中间分割线
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.parseColor("#CDCDCD"));
        for (int i = 1; i <= mPasswordSize - 1; i++) {
            int startX = getWidth() / mPasswordSize * i;
            canvas.drawLine(startX - 2, 0, startX,  getHeight(), mPaint);
        }
    }

    /**
     * 绘制加密点.
     * @param canvas
     */
    private void drawSecrecyPoint(Canvas canvas) {
        // 绘制密码
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        float tempValue = 0;
        int onePasswordWidth = getWidth() / mPasswordSize;
        int size = passwordText.size();

        for (int i = 0; i < size; i++) {
            float startX = 0;
            if (i == 0) {
                startX = (float) ((onePasswordWidth + 2 - pointRadius) / 2);
            } else {
                startX = onePasswordWidth + tempValue;
            }
            canvas.drawCircle(startX, (float) getHeight() / 2, pointRadius, mPaint);

            tempValue = startX;
        }
    }

    private void showKeyboard() {
        if (im != null) {
            im.showSoftInput(this, 0);
        }
    }

    private void closeKeyboard() {
        if (im != null) {
            im.hideSoftInputFromWindow(this.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            requestFocus();
            showKeyboard();

            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if (!hasWindowFocus) {
            closeKeyboard();
        }
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;
        return new NumberInputConnection(this, false);
    }

    public static final class NumberInputConnection extends BaseInputConnection {

        public NumberInputConnection(View targetView, boolean fullEditor) {
            super(targetView, fullEditor);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {

            if (beforeLength == 1 && afterLength == 0) {
                return super.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)) &&
                        super.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if (passwordText.size() < mPasswordSize) {
                    passwordText.add(keyCode - 7 + "");
                    invalidate();

                    doInputFinish();
                }

                return true;
            }

            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (passwordText.size() > 0) {
                    passwordText.remove(passwordText.size() - 1);
                    invalidate();
                }
                return true;
            }

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                doInputFinish();
                return true;
            }
        }

        return false;
    }

    private void doInputFinish() {
        if (passwordText.size() <= mPasswordSize) {
            if (mOnPasswordChangeListener != null) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < passwordText.size(); i++) {
                    buffer.append(passwordText.get(i));
                }

                Log.i(TAG, "passwordText: " + passwordText.toString());
                mOnPasswordChangeListener.onTextChanged(buffer.toString());
            }
        }
    }

    public interface OnPasswordChangeListener {

        void onTextChanged(String text);
    }

    public void setOnPasswordChangeListener(OnPasswordChangeListener l) {
        this.mOnPasswordChangeListener = l;
    }
}
