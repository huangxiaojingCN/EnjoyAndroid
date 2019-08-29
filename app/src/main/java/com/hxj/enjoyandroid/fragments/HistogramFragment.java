package com.hxj.enjoyandroid.fragments;

import android.view.View;

import com.hxj.enjoyandroid.R;
import com.hxj.enjoyandroid.model.HistogramBean;
import com.hxj.enjoyandroid.model.PieBean;
import com.hxj.enjoyandroid.ui.canvas.HistogramView;
import com.hxj.enjoyandroid.ui.canvas.PieView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HistogramFragment extends BaseFragment {

    private HistogramView<HistogramBean> mHistogramView;

    private PieView<PieBean> mPieView;

    @Override
    protected void initView(View view) {
       mHistogramView = view.findViewById(R.id.histogramView);

       mPieView = view.findViewById(R.id.pieView);
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
        bean3.columnName = "三毛";


        HistogramBean bean4 = new HistogramBean();
        bean4.height = 56;
        bean4.max = 100;
        bean4.color = "#F2294C";
        bean4.columnName = "刘明";


        HistogramBean bean5 = new HistogramBean();
        bean5.height = 34;
        bean5.max = 100;
        bean5.color = "#18294C";
        bean5.columnName = "李红";


        HistogramBean bean6 = new HistogramBean();
        bean6.height = 100;
        bean6.max = 100;
        bean6.color = "#18B44C";
        bean6.columnName = "曾鸣";


        HistogramBean bean7 = new HistogramBean();
        bean7.height = 100;
        bean7.max = 100;
        bean7.color = "#184B4C";
        bean7.columnName = "罗一风";

        datas.add(bean);
        datas.add(bean1);
        datas.add(bean2);
        datas.add(bean3);
        datas.add(bean4);
        datas.add(bean5);
        datas.add(bean6);
        datas.add(bean7);

        mHistogramView.addData(datas);


        List<PieBean> pieDatas = new ArrayList<>();
        PieBean pie1 = new PieBean();
        pie1.color = "#18B44C";
        pie1.item = "事例1";
        pie1.max = 100;
        pie1.value = 30;
        pieDatas.add(pie1);

        PieBean pie2 = new PieBean();
        pie2.color = "#18294C";
        pie2.item = "事例2";
        pie2.max = 100;
        pie2.value = 20;
        pieDatas.add(pie2);

        PieBean pie3 = new PieBean();
        pie3.color = "#DB4B4C";
        pie3.item = "事例2";
        pie3.max = 100;
        pie3.value = 10;
        pieDatas.add(pie3);

        PieBean pie4 = new PieBean();
        pie4.color = "#1375CD";
        pie4.item = "事例2";
        pie4.max = 100;
        pie4.value = 15;
        pieDatas.add(pie4);

        PieBean pie5 = new PieBean();
        pie5.color = "#DD7532";
        pie5.item = "事例2";
        pie5.max = 100;
        pie5.value = 25;
        pieDatas.add(pie5);

        mPieView.addPies(pieDatas);
    }
}
