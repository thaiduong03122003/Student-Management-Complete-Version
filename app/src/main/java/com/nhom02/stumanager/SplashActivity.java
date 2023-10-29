package com.nhom02.stumanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.nhom02.stumanager.model.EducationProgram;
import com.nhom02.stumanager.sqlite.DBHelper;
import com.nhom02.stumanager.sqlite.EduProgDao;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.close();

        //Tạo dữ liệu sẵn cho tb_ct_daotao trong database
        EducationProgram edu = new EducationProgram();
        EduProgDao eduDao = new EduProgDao(this);
        if (eduDao.getAll().isEmpty()) {
            edu.setEduId("ĐT");
            edu.setEduName("Đại trà");
            eduDao.insert(edu);

            edu.setEduId("CLC");
            edu.setEduName("Chất lượng cao");
            eduDao.insert(edu);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}