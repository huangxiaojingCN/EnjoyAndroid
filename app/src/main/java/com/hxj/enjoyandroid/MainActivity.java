package com.hxj.enjoyandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    private List<String> datas = new ArrayList<>();

    private String[] items = new String[] {"屏幕适配", "自定义view系列", "NDK系列"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = findViewById(R.id.recyclerView);

        initData();

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(datas);
        mRecycleView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                startActivity(position);
            }
        });
    }

    public void startActivity(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, UIAdapter2Activity.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, NdkActivity.class));
                break;
        }
    }

    private void initData() {
        for (String item : items) {
            datas.add(item);
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {

        private final List<String> mDatas;

        private OnItemClickListener mOnItemClickListener;

        public MyAdapter(List<String> datas) {
            this.mDatas = datas;
        }

        @NonNull
        @Override
        public MyViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHodler(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,
                            viewGroup,
                            false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHodler myViewHodler, final int position) {
            myViewHodler.mTvContent.setText(mDatas.get(position));

            myViewHodler.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        public class MyViewHodler extends RecyclerView.ViewHolder {

            private TextView mTvContent;

            public MyViewHodler(@NonNull View itemView) {
                super(itemView);

                mTvContent = itemView.findViewById(R.id.tv_content);
            }
        }

        public interface OnItemClickListener {

            void onItemClick(View view, int position);
        }

        public void setOnItemClickListener(OnItemClickListener l) {
            this.mOnItemClickListener = l;
        }
    }


}
