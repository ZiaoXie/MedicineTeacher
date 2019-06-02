package com.example.administrator.medicineteacher;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

import static com.example.administrator.medicineteacher.R.id.recyclerView;
import static com.example.administrator.medicineteacher.R.id.title;

public class SearchResult extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView back,photo;

    int flag;

    String title[]={"病机","治则","药物","方名","简便方","食疗方","预后","宜忌","古籍来源"};
    ArrayList<String> content=new ArrayList<String>();
    String jiansuo,leixing;

    private final String DATABASE_PATH = "/data/data/com.example.administrator.medicineteacher/databases";
    private final String DATABASE_FILENAME = "datamedicine.db";

    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        flag=getIntent().getIntExtra("flag",0);
        jiansuo=getIntent().getStringExtra("jiansuo");

        switch (flag){
            case 0:
                leixing="病名";
                break;
            case 1:
                leixing="证型";
                break;
            case 2:
                leixing="症状";
                break;
        }
        System.out.println(leixing+"    "+jiansuo);
        String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
        sqldb = SQLiteDatabase.openDatabase(databaseFilename, null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=sqldb.query("bizheng",new String[]{"病机","治则","药物","方名","简便方","食疗方","预后","宜忌","古籍来源"},
                leixing+"=?",new String[]{jiansuo}, null,null,null);
        cursor.moveToFirst();
        for(int i=0;i<title.length;i++){
            content.add(cursor.getString(i));
        }



        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photo=(ImageView)findViewById(R.id.photo);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchResult.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(SearchResult.this, LinearLayoutManager.VERTICAL));
    }

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder=new Holder(LayoutInflater.from(SearchResult.this).inflate(R.layout.item_search,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Holder)holder).title.setText(title[position]);
            ((Holder)holder).content.setText(content.get(position));
        }

        @Override
        public int getItemCount() {
            return title.length;
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView title,content;

            Holder(View v){
                super(v);
                title=(TextView)v.findViewById(R.id.title);
                content=(TextView)v.findViewById(R.id.content);
            }
        }
    }
}
