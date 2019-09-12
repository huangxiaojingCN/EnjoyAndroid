package com.hxj.enjoyandroid.fragments.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hxj.enjoyandroid.R;
import com.hxj.enjoyandroid.fragments.BaseFragment;
import com.hxj.enjoyandroid.views.canvas.ColorMatrixView;

public class ColorMatrixFragment extends BaseFragment {

    /**
     *  [ a, b, c, d, e,
     *     f, g, h, i, j,
     *     k, l, m, n, o,
     *     p, q, r, s, t ]
     */
    private Button mBtnSetting;
    private EditText mEtRa;
    private EditText mEtRb;
    private EditText mEtRc;
    private EditText mEtRd;
    private EditText mEtRe;

    private EditText mEtGf;
    private EditText mEtGg;
    private EditText mEtGh;
    private EditText mEtGi;
    private EditText mEtGj;

    private EditText mEtBk;
    private EditText mEtBl;
    private EditText mEtBm;
    private EditText mEtBn;
    private EditText mEtBo;

    private EditText mEtAp;
    private EditText mEtAq;
    private EditText mEtAr;
    private EditText mEtAs;
    private EditText mEtAt;


    float[] colorMatrix;
    private ColorMatrixView mColorMatrixView;

    @Override
    public int loadLayoutId() {
        return R.layout.fragment_color_matrix;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {
        mBtnSetting = view.findViewById(R.id.btn_setting);
        mColorMatrixView = view.findViewById(R.id.colorMatrix);

        mEtRa = view.findViewById(R.id.et1);
        mEtRb = view.findViewById(R.id.et2);
        mEtRc = view.findViewById(R.id.et3);
        mEtRd = view.findViewById(R.id.et4);
        mEtRe = view.findViewById(R.id.et5);

        mEtGf = view.findViewById(R.id.et6);
        mEtGg = view.findViewById(R.id.et7);
        mEtGh = view.findViewById(R.id.et8);
        mEtGi = view.findViewById(R.id.et9);
        mEtGj = view.findViewById(R.id.et10);


        mEtBk = view.findViewById(R.id.et11);
        mEtBl = view.findViewById(R.id.et12);
        mEtBm = view.findViewById(R.id.et13);
        mEtBn = view.findViewById(R.id.et14);
        mEtBo = view.findViewById(R.id.et15);

        mEtAp = view.findViewById(R.id.et16);
        mEtAq = view.findViewById(R.id.et17);
        mEtAr = view.findViewById(R.id.et18);
        mEtAs = view.findViewById(R.id.et19);
        mEtAt = view.findViewById(R.id.et20);


        colorMatrix = new float[20];

        mBtnSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isEmpty(mEtRa) && !isEmpty(mEtRb) && !isEmpty(mEtRc) && !isEmpty(mEtRd) && !isEmpty(mEtRe) &&
                        !isEmpty(mEtGf) && !isEmpty(mEtGg) && !isEmpty(mEtGh) && !isEmpty(mEtGi) && !isEmpty(mEtGj) &&
                        !isEmpty(mEtBk) && !isEmpty(mEtBl) && !isEmpty(mEtBm) && !isEmpty(mEtBn) && !isEmpty(mEtBo) &&
                        !isEmpty(mEtAp) && !isEmpty(mEtAq) && !isEmpty(mEtAr) && !isEmpty(mEtAs) && !isEmpty(mEtAt)) {

                    colorMatrix[0] = Float.parseFloat(mEtRa.getText().toString().trim());
                    colorMatrix[1] = Float.parseFloat(mEtRb.getText().toString().trim());
                    colorMatrix[2] = Float.parseFloat(mEtRc.getText().toString().trim());
                    colorMatrix[3] = Float.parseFloat(mEtRd.getText().toString().trim());
                    colorMatrix[4] = Float.parseFloat(mEtRe.getText().toString().trim());

                    colorMatrix[5] = Float.parseFloat(mEtGf.getText().toString().trim());
                    colorMatrix[6] = Float.parseFloat(mEtGg.getText().toString().trim());
                    colorMatrix[7] = Float.parseFloat(mEtGh.getText().toString().trim());
                    colorMatrix[8] = Float.parseFloat(mEtGi.getText().toString().trim());
                    colorMatrix[9] = Float.parseFloat(mEtGj.getText().toString().trim());

                    colorMatrix[10] = Float.parseFloat(mEtBk.getText().toString().trim());
                    colorMatrix[11] = Float.parseFloat(mEtBl.getText().toString().trim());
                    colorMatrix[12] = Float.parseFloat(mEtBm.getText().toString().trim());
                    colorMatrix[13] = Float.parseFloat(mEtBn.getText().toString().trim());
                    colorMatrix[14] = Float.parseFloat(mEtBo.getText().toString().trim());

                    colorMatrix[15] = Float.parseFloat(mEtAp.getText().toString().trim());
                    colorMatrix[16] = Float.parseFloat(mEtAq.getText().toString().trim());
                    colorMatrix[17] = Float.parseFloat(mEtAr.getText().toString().trim());
                    colorMatrix[18] = Float.parseFloat(mEtAs.getText().toString().trim());
                    colorMatrix[19] = Float.parseFloat(mEtAt.getText().toString().trim());


                    mColorMatrixView.setColorMatrix(colorMatrix);
                }
            }
        });
    }


    private boolean isEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString().trim());
    }
}
