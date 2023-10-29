package com.nhom02.stumanager.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom02.stumanager.model.Course;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    private SQLiteDatabase db;

    public CourseDao(Context context) {
        DBHelper helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public long insert(Course emp) {
        ContentValues values = new ContentValues();
        values.put("ma_khoahoc", emp.getCourseId());
        values.put("ten_khoahoc", emp.getCourseName());
        values.put("nien_khoa", emp.getSchYear());
        values.put("ma_nganh", emp.getMajorId());

        return db.insert("tb_khoahoc", null, values);
    }

    public long update(Course emp) {
        ContentValues values = new ContentValues();
        values.put("ten_khoahoc", emp.getCourseName());
        values.put("nien_khoa", emp.getSchYear());
        values.put("ma_nganh", emp.getMajorId());

        return db.update("tb_khoahoc", values, "ma_khoahoc = ?", new String[]{emp.getCourseId()});
    }

    public long delete(String id) {
        return db.delete("tb_khoahoc","ma_khoahoc = ?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Course> get(String sql, String ... selectArgs) throws ParseException {
        List<Course> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            Course cou = new Course();
            cou.setCourseId(cursor.getString(cursor.getColumnIndex("ma_khoahoc")));
            cou.setCourseName(cursor.getString(cursor.getColumnIndex("ten_khoahoc")));
            cou.setSchYear(cursor.getString(cursor.getColumnIndex("nien_khoa")));
            cou.setMajorId(cursor.getString(cursor.getColumnIndex("ma_nganh")));
            list.add(cou);
        }
        return list;
    }
    public List<Course> getALL() throws ParseException {
        String sql = "SELECT * FROM tb_khoahoc";

        return get(sql);
    }

    public Course getById(String id) throws ParseException {
        String sql = "SELECT * FROM tb_khoahoc WHERE ma_khoahoc = ?";
        List<Course> list = get(sql, id);

        return list.get(0);
    }

    public List<Course> getAllByMajorId(String majorId) throws ParseException {
        String sql = "SELECT * FROM tb_khoahoc WHERE ma_nganh = ?";

        return get(sql, "" + majorId);
    }

    public List<Course> getCoursesByMajorName(String majorName) throws ParseException {
        String sql = "SELECT kh.*, cn.ten_nganh " +
                "FROM tb_khoahoc kh " +
                "INNER JOIN tb_chuyennganh cn ON kh.ma_nganh = cn.ma_nganh " +
                "WHERE cn.ten_nganh = ?";

        return get(sql, majorName);
    }

    @SuppressLint("Range")
    public List<String> getAllMajorNames() {
        List<String> majorNames = new ArrayList<>();
        String sql = "SELECT cn.ten_nganh " +
                "FROM tb_khoahoc kh " +
                "INNER JOIN tb_chuyennganh cn ON kh.ma_nganh = cn.ma_nganh";

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            majorNames.add(cursor.getString(cursor.getColumnIndex("ten_nganh")));
        }

        cursor.close();
        return majorNames;
    }

}
