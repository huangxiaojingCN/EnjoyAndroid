package com.hxj.enjoyandroid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hxj.enjoyandroid.R;

public class PercentRelativeLayout extends RelativeLayout {

    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams childLayoutParams = child.getLayoutParams();
            if (checkLayoutParams(childLayoutParams)) {
                float widthPercent = ((PercentRelativeLayoutParams) childLayoutParams).mWidthPercent;
                if (widthPercent > 0) {
                    childLayoutParams.width = (int) (widthSize * widthPercent);
                }

                float heightPercent = ((PercentRelativeLayoutParams) childLayoutParams).mHeightPercent;
                if (heightPercent > 0) {
                    childLayoutParams.height = (int) (heightSize * heightPercent);
                }

                float marginLeftPercent = ((PercentRelativeLayoutParams) childLayoutParams).mMarginLeftPercent;
                if (marginLeftPercent > 0) {
                    ((MarginLayoutParams)childLayoutParams).leftMargin = (int) (widthSize * marginLeftPercent);
                }

                float marginTopPercent = ((PercentRelativeLayoutParams) childLayoutParams).mMarginTopPercent;
                if (marginTopPercent > 0) {
                    ((MarginLayoutParams)childLayoutParams).topMargin = (int) (heightSize * marginTopPercent);
                }

                float marginRightPercent = ((PercentRelativeLayoutParams) childLayoutParams).mMarginRightPercent;
                if (marginRightPercent > 0) {
                    ((MarginLayoutParams)childLayoutParams).rightMargin = (int) (widthSize * marginRightPercent);
                }

                float marginBottomPercent = ((PercentRelativeLayoutParams) childLayoutParams).mMarginBottomPercent;
                if (marginBottomPercent > 0) {
                    ((MarginLayoutParams)childLayoutParams).bottomMargin = (int) (heightSize * marginBottomPercent);
                }
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof PercentRelativeLayoutParams;
    }

    @Override
    public PercentRelativeLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PercentRelativeLayoutParams(getContext(), attrs);
    }

    public static class PercentRelativeLayoutParams extends LayoutParams {

        public float mWidthPercent;
        public float mHeightPercent;
        public float mMarginLeftPercent;
        public float mMarginTopPercent;
        public float mMarginRightPercent;
        public float mMarginBottomPercent;

        public PercentRelativeLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            @SuppressLint("CustomViewStyleable")
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);

            try {
                mWidthPercent = a.getFloat(R.styleable.PercentRelativeLayout_layout_widthPercent, 0.0f);
                mHeightPercent = a.getFloat(R.styleable.PercentRelativeLayout_layout_heightPercent, 0.0f);
                mMarginLeftPercent = a.getFloat(R.styleable.PercentRelativeLayout_layout_marginLeftPercent, 0.0f);
                mMarginTopPercent = a.getFloat(R.styleable.PercentRelativeLayout_layout_marginTopPercent, 0.0f);
                mMarginRightPercent = a.getFloat(R.styleable.PercentRelativeLayout_layout_marginRightPercent, 0.0f);
                mMarginBottomPercent = a.getFloat(R.styleable.PercentRelativeLayout_layout_marginBottomPercent, 0.0f);
            } finally {
                a.recycle();
            }
        }
    }
}
