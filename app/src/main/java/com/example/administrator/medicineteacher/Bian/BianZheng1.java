package com.example.administrator.medicineteacher.Bian;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.medicineteacher.R;
import com.example.administrator.medicineteacher.SearchResult;

import java.util.ArrayList;

public class BianZheng1 extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerView;

    ArrayList<String> bianzheng1=new ArrayList<String>();

    private final String DATABASE_PATH = "/data/data/com.example.administrator.medicineteacher/databases";
    private final String DATABASE_FILENAME = "datamedicine.db";

    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_zheng1);

        String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
        sqldb = SQLiteDatabase.openDatabase(databaseFilename, null,
                SQLiteDatabase.OPEN_READONLY);

        Cursor cursor=sqldb.rawQuery("select distinct 证型 from bizheng where 证型 not null",null);
        cursor.moveToFirst();
        for(int i=0;i<20;i++){
            bianzheng1.add(cursor.getString(0));
            cursor.moveToNext();
        }

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder=new Holder(LayoutInflater.from(BianZheng1.this).inflate(R.layout.item_bian_zheng1,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((Holder)holder).bian_zheng1.setText(bianzheng1.get(position));
            ((Holder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(BianZheng1.this, SearchResult.class);
                    intent.putExtra("flag",1);
                    intent.putExtra("jiansuo",bianzheng1.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return bianzheng1.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView bian_zheng1;

            public Holder(View v) {
                super(v);
                bian_zheng1=(TextView)v.findViewById(R.id.bian_zheng1);
            }
        }
    }
}
