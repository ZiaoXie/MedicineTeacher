package com.example.administrator.medicineteacher.Bian;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.medicineteacher.R;
import com.example.administrator.medicineteacher.SearchResult;
import com.example.administrator.medicineteacher.toolClass.DividerGridItemDecoration;

import java.util.ArrayList;

import static com.example.administrator.medicineteacher.R.mipmap.bianzheng1;

public class BianBing extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView back;

    ArrayList<String> bizheng=new ArrayList<String>();

    private final String DATABASE_PATH = "/data/data/com.example.administrator.medicineteacher/databases";
    private final String DATABASE_FILENAME = "datamedicine.db";

    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_bing);

        String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
        sqldb = SQLiteDatabase.openDatabase(databaseFilename, null,
                SQLiteDatabase.OPEN_READONLY);

        Cursor cursor=sqldb.rawQuery("select distinct 病名 from bizheng where 病名 not null",null);
        cursor.moveToFirst();
        for(int i=0;i<20;i++){
            bizheng.add(cursor.getString(0));
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
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder=new Holder(LayoutInflater.from(BianBing.this).inflate(R.layout.item_bianbing,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ((Holder)holder).tv.setText(bizheng.get(position));
            ((Holder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BianBing.this, SearchResult.class);
                    intent.putExtra("flag", 0);
                    intent.putExtra("jiansuo", bizheng.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return bizheng.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView tv;

            public Holder(View v) {
                super(v);
                tv=(TextView)v.findViewById(R.id.bianbing);
            }
        }
    }
}
