package com.example.administrator.medicineteacher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.administrator.medicineteacher.toolClass.DatabaseUtil;
import com.example.administrator.medicineteacher.toolClass.SearchView;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Medicine#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Medicine extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Integer integer[]=new Integer[]{R.mipmap.danggui,R.mipmap.fuling,R.mipmap.fangfeng,R.mipmap.fuzi,R.mipmap.baishu,R.mipmap.chuanxiong,R.mipmap.rougui};
    ArrayList<String> titles=new ArrayList<String>();
    ArrayList<String> abstr=new ArrayList<String>();

    SQLiteDatabase sqldb;
    View view;

    RecyclerView recyclerView;
    private final String DATABASE_PATH = "/data/data/com.example.administrator.medicineteacher" + "/databases";
    private final String DATABASE_FILENAME = "datamedicine.db";


    public Medicine() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Medicine.
     */
    // TODO: Rename and change types and number of parameters
    public static Medicine newInstance(String param1, String param2) {
        Medicine fragment = new Medicine();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_medicine, container, false);


        DatabaseUtil util = new DatabaseUtil(getContext(),"datamedicine.db");
        // 判断数据库是否存在
        boolean dbExist = util.checkDataBase();

        if (dbExist) {
            Log.i("tag", "The database is exist.");
        } else {// 不存在就把raw里的数据库写入手机
            try {
                util.copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

        String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
        sqldb = SQLiteDatabase.openDatabase(databaseFilename, null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=sqldb.query("medicine",new String[]{"medicinename","othername"},"othername not null",null,null,null,null);
        cursor.moveToFirst();
        do{
            titles.add(cursor.getString(0));
            abstr.add(cursor.getString(1));
        } while (cursor.moveToNext());
        System.out.println(cursor.getCount());

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder=new Holder(LayoutInflater.from(getActivity()).inflate(R.layout.item_medicine,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Holder)holder).iv.setImageResource(integer[position]);
            ((Holder)holder).title.setText(titles.get(position));
            ((Holder)holder).abst.setText(abstr.get(position));
        }

        @Override
        public int getItemCount() {
            return titles.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            ImageView iv;
            TextView title,abst;

            public Holder(View v) {
                super(v);
                iv=(ImageView) v.findViewById(R.id.articlepic);
                abst=(TextView)v.findViewById(R.id.articleabstract);
                title=(TextView)v.findViewById(R.id.title);
            }
        }
    }

}
