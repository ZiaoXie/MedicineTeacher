package com.example.administrator.medicineteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.gujun.android.taggroup.TagGroup;

public class ChangJianSearch extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerView;

    String titles[]={"常见病","常见证","常见症","常见药"};
    String tags[][]={
            {"骨痹","行痹","痛痹","周痹","肠痹","热痹","风毒脚弱痹","胸痹"},
            {"肝肾亏虚证","风寒痹阻证","寒湿痹阻证","心脉痹阻证","气滞血瘀证","肝肾阳虚证"},
            {"脚气","脚部麻木","肌肉萎弱麻痹","腰膝酸冷","身冷、身痛","关节红肿疼痛"},
            {"川芎","枸杞","附子","独活","防风","川芎","丹参","萆薢","石菖蒲","天麻","桂枝","当归","黄芪","细辛","山茱萸","白术",
                    "菊花","牛膝","炙甘草","枳壳","人参"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_jian_search);

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(ChangJianSearch.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder=new Holder(LayoutInflater.from(ChangJianSearch.this).inflate(R.layout.item_changyong,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Holder)holder).title.setText(titles[position]);
            ((Holder)holder).tagGroup.setTags(tags[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView title;
            TagGroup tagGroup;

            public Holder(View v) {
                super(v);
                title=(TextView)v.findViewById(R.id.title);
                tagGroup=(TagGroup)v.findViewById(R.id.tag_group);
            }
        }
    }

}
