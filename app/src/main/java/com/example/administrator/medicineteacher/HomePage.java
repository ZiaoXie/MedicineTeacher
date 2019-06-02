package com.example.administrator.medicineteacher;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.medicineteacher.toolClass.BottomNavigationViewHelper;
import com.example.administrator.medicineteacher.toolClass.DatabaseUtil;

import java.io.IOException;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fm;
    FragmentTransaction transaction;

    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        DatabaseUtil util = new DatabaseUtil(HomePage.this,"datamedicine.db");
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

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        flag=getIntent().getIntExtra("flag",1);

        flag=getIntent().getIntExtra("flag",1);
        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();
        switch (flag){
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.item_zhuye);
                transaction.replace(R.id.detail,new UserUI());
                break;
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.item_wenzhen);
                transaction.replace(R.id.detail,new Medicine());
                break;
            case 3:
                bottomNavigationView.setSelectedItemId(R.id.item_jiangtang);
                transaction.replace(R.id.detail,new JiangTang());
                break;
            case 4:
                bottomNavigationView.setSelectedItemId(R.id.item_personal);
                transaction.replace(R.id.detail,new Personal());
        }
        transaction.commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                transaction=fm.beginTransaction();
                switch (item.getItemId()){
                    case R.id.item_zhuye:
                        transaction.replace(R.id.detail,new UserUI());
                        break;
                    case R.id.item_wenzhen:
                        transaction.replace(R.id.detail,new Medicine());
                        break;
                    case R.id.item_jiangtang:
                        transaction.replace(R.id.detail,new JiangTang());
                        break;
                    case R.id.item_personal:
                        transaction.replace(R.id.detail,new Personal());
                        break;
                    default:
                        break;
                }
                transaction.commit();
                return true;
            }
        });


    }
}
