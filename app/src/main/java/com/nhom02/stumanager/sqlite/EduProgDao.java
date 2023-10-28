package com.nhom02.stumanager.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom02.stumanager.model.EducationProgram;

import java.util.ArrayList;
import java.util.List;

public class EduProgDao {
    private SQLiteDatabase db;

    public EduProgDao(Context context) {
        DBHelper helper = new DBHelper(context);

        this.db = helper.getWritableDatabase();
    }
    public long insert(EducationProgram emp) {
        ContentValues values = new ContentValues();
        values.put("ma_daotao", emp.getEduId());
        values.put("ten_ct_daotao", emp.getEduName());

        return db.insert("tb_ct_daotao", null, values);
    }

    @SuppressLint("Range")
    public List<EducationProgram> get(String sql, String ... selectArgs) {
        List<EducationProgram> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            EducationProgram edu = new EducationProgram();
            edu.setEduId(cursor.getString(cursor.getColumnIndex("ma_daotao")));
            edu.setEduName(cursor.getString(cursor.getColumnIndex("ten_ct_daotao")));
            list.add(edu);
        }
        return list;
    }

    public List<EducationProgram> getAll() {
        String sql = "SELECT * FROM tb_ct_daotao";

        return get(sql);
    }
    public int delete (String id) {
        return db.delete("tb_ct_daotao", "ma_daotao=?", new String[]{id});
    }
}
