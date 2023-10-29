package com.nhom02.stumanager.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom02.stumanager.helper.DateTimeHelper;
import com.nhom02.stumanager.model.Major;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MajorDao {
    private SQLiteDatabase db;

    public MajorDao(Context context) {
        DBHelper helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public long insert(Major emp) {
        ContentValues values = new ContentValues();
        values.put("ma_nganh", emp.getMajorId());
        values.put("ten_nganh", emp.getMajorName());
        values.put("sdt_lien_he", emp.getMajorPhone());
        values.put("web_nganh", emp.getMajorLink());
        values.put("ma_daotao", emp.getEduProgId());


        return db.insert("tb_chuyennganh", null, values);
    }

    public long update(Major emp) {
        ContentValues values = new ContentValues();
        values.put("ten_nganh", emp.getMajorName());
        values.put("sdt_lien_he", emp.getMajorPhone());
        values.put("web_nganh", emp.getMajorLink());
        values.put("ma_daotao", emp.getEduProgId());

        return db.update("tb_chuyennganh", values, "ma_nganh = ?", new String[]{emp.getMajorId()});
    }

    public long delete(String id) {
        return db.delete("tb_chuyennganh","ma_nganh = ?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Major> get(String sql, String ... selectArgs) throws ParseException {
        List<Major> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            Major std = new Major();
            std.setMajorId(cursor.getString(cursor.getColumnIndex("ma_nganh")));
            std.setMajorName(cursor.getString(cursor.getColumnIndex("ten_nganh")));
            std.setMajorPhone(cursor.getString(cursor.getColumnIndex("sdt_lien_he")));
            std.setMajorLink(cursor.getString(cursor.getColumnIndex("web_nganh")));
            std.setEduProgId(cursor.getString(cursor.getColumnIndex("ma_daotao")));
            list.add(std);
        }
        return list;
    }
    public List<Major> getALL() throws ParseException {
        String sql = "SELECT * FROM tb_chuyennganh";

        return get(sql);
    }

    public Major getById(String id) throws ParseException {
        String sql = "SELECT * FROM tb_chuyennganh WHERE ma_nganh = ?";
        List<Major> list = get(sql, id);

        return list.get(0);
    }

    public List<Major> getAllByEduProgId(String eduProgId) throws ParseException {
        String sql = "SELECT * FROM tb_chuyennganh WHERE ma_daotao = ?";

        return get(sql, "" + eduProgId);
    }
}
