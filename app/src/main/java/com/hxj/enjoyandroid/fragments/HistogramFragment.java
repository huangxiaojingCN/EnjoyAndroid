package com.hxj.enjoyandroid.fragments;

import android.view.View;

import com.hxj.enjoyandroid.R;
import com.hxj.enjoyandroid.model.HistogramBean;
import com.hxj.enjoyandroid.ui.canvas.HistogramView;

import java.util.ArrayList;
import java.util.List;

public class HistogramFragment extends BaseFragment {

    private HistogramView<HistogramBean> mHistogramView;

    @Override
    protected void initView(View view) {
       mHistogramView = view.findViewById(R.id.histogramView);
    }

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
        bean.color = "#DD7532";
        bean.columnName = "小明";

        HistogramBean bean1 = new HistogramBean();
        bean1.height = 80;
        bean1.max = 100;
        bean1.color = "#DB4B4C";
        bean1.columnName = "张三";

        HistogramBean bean2 = new HistogramBean();
        bean2.height = 50;
        bean2.max = 100;
        bean2.color = "#DBFFAF";
        bean2.columnName = "李四";

        HistogramBean bean3 = new HistogramBean();
        bean3.height = 100;
        bean3.max = 100;
        bean3.color = "#1375CD";
        bean3.columnName = "王二";

        datas.add(bean);
        datas.add(bean1);
        datas.add(bean2);
        datas.add(bean3);

        mHistogramView.addData(datas);
    }
}
