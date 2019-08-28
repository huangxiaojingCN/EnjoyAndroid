package com.hxj.enjoyandroid.fragments;

import com.hxj.enjoyandroid.R;
import com.hxj.enjoyandroid.model.HistogramBean;

import java.util.ArrayList;
import java.util.List;

public class HistogramFragment extends BaseFragment {

    @Override
    int loadLayoutId() {
        return R.layout.fragment_histogram;
    }

    @Override
    void initData() {
        List<HistogramBean> datas = new ArrayList<>();
        HistogramBean bean = new HistogramBean();
        bean.height = 30;
        bean.max = 100;
        HistogramBean bean1 = new HistogramBean();
        bean.height = 80;
        bean.max = 100;
        HistogramBean bean2 = new HistogramBean();
        bean.height = 50;
        bean.max = 100;
        HistogramBean bean3 = new HistogramBean();
        bean.height = 90;
        bean.max = 100;

        datas.add( bean);
        datas.add( bean1);
        datas.add( bean2);
        datas.add(bean3);
    }
}
