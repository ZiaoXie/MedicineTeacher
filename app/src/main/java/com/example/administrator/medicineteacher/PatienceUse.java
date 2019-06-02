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

public class PatienceUse extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerView;

    String username[]={"子傲","王大锤","小苹果","a88754","maple leaves"};
    String times[]={"1分钟前","2小时前","1天前","4小时前","5分钟前"};
    String titles[]={"有朋友有治风湿的好方子吗？","求推荐一个按摩师","请问XX附近有没有比较好的针灸诊所啊？","哪家中医院校比较好啊？","各位老家那儿那儿枸杞怎么卖？"};
    String abstracts[]={"最近风湿病发作，想求个方子，这些天可疼死了","最近想做做按摩，不知道大家有没有推荐的地方",
            "一直想去找家不错的针灸诊所，我家住XX附近，请问周边有没有推荐的相关诊所啊？", "孩子要高考了，家里一直希望孩子能去学中医，不知道哪家学校中医专业比较好啊",
            "我们这儿最近枸杞可贵了，不知道各位那里枸杞怎么卖？"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patience_use);

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(PatienceUse.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(PatienceUse.this, LinearLayoutManager.VERTICAL));
    }

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder=new Holder(LayoutInflater.from(PatienceUse.this).inflate(R.layout.item_forum,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Holder)holder).username.setText(username[position]);
            ((Holder)holder).time.setText(times[position]);
            ((Holder)holder).title.setText(titles[position]);
            ((Holder)holder).talkabstract.setText(abstracts[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView username,time,title,talkabstract;

            Holder(View v){
                super(v);
                username=(TextView)v.findViewById(R.id.username);
                time=(TextView)v.findViewById(R.id.time);
                title=(TextView)v.findViewById(R.id.title);
                talkabstract=(TextView)v.findViewById(R.id.talkabstract);
            }
        }
    }
}
