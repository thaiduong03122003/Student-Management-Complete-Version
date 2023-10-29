package com.nhom02.stumanager.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "stu_manager";
    private static final int DB_VERSION = 2;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String chuyenNganhSql = "CREATE TABLE \"tb_chuyennganh\" (\n" +
                "\t\"ma_nganh\"\tvarchar(10),\n" +
                "\t\"ten_nganh\"\tTEXT NOT NULL,\n" +
                "\t\"sdt_lien_he\"\tTEXT,\n" +
                "\t\"web_nganh\"\tTEXT,\n" +
                "\t\"ma_daotao\"\tvarchar(10),\n" +
                "\tFOREIGN KEY(\"ma_daotao\") REFERENCES \"tb_ct_daotao\"(\"ma_daotao\") ON DELETE SET NULL ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"ma_nganh\")\n" +
                ");";

        String ctDaoTaoSql = "CREATE TABLE \"tb_ct_daotao\" (\n" +
                "\t\"ma_daotao\"\tTEXT,\n" +
                "\t\"ten_ct_daotao\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ma_daotao\")\n" +
                ")";

        String diemSql = "CREATE TABLE \"tb_diem\" (\n" +
                "\t\"ma_diem\"\tvarchar(10),\n" +
                "\t\"gpa_sv\"\tREAL,\n" +
                "\t\"tong_tin_chi\"\tINTEGER,\n" +
                "\t\"ma_sv\"\tvarchar(10) UNIQUE,\n" +
                "\tFOREIGN KEY(\"ma_sv\") REFERENCES \"tb_sinhvien\"(\"ma_sv\") ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"ma_diem\")\n" +
                ")";

        String chiTietDiemSql = "CREATE TABLE \"tb_diem_chi_tiet\" (\n" +
                "\t\"ma_diem\"\tvarchar(10),\n" +
                "\t\"ma_sv\"\tvarchar(10),\n" +
                "\t\"ma_monhoc\"\tvarchar(10),\n" +
                "\t\"diem_chuyen_can\"\tREAL,\n" +
                "\t\"diem_giua_ky\"\tREAL,\n" +
                "\t\"diem_cuoi_ky\"\tREAL,\n" +
                "\t\"diem_tb_mon\"\tREAL,\n" +
                "\t\"ket_qua\"\tTEXT,\n" +
                "\tFOREIGN KEY(\"ma_sv\") REFERENCES \"tb_diem\"(\"ma_sv\") ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"ma_diem\") REFERENCES \"tb_diem\"(\"ma_diem\") ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"ma_monhoc\") REFERENCES \"tb_monhoc_chuyennganh\"(\"ma_monhoc\") ON DELETE SET NULL ON UPDATE CASCADE\n" +
                ")";

        String khoaHocSql = "CREATE TABLE \"tb_khoahoc\" (\n" +
                "\t\"ma_khoahoc\"\tvarchar(10),\n" +
                "\t\"ten_khoahoc\"\tvarchar(5) NOT NULL,\n" +
                "\t\"nien_khoa\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ma_khoahoc\")\n" +
                ")";

        String lichSuSql = "CREATE TABLE \"tb_lichsu\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"thoi_gian\"\tTEXT,\n" +
                "\t\"user_id\"\tTEXT,\n" +
                "\t\"hanh_dong\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT),\n" +
                "\tFOREIGN KEY(\"user_id\") REFERENCES \"tb_user\"(\"user_id\")\n" +
                ")";

        String lopSql = "CREATE TABLE \"tb_lop\" (\n" +
                "\t\"ma_lop\"\tvarchar(10),\n" +
                "\t\"ten_lop\"\tTEXT NOT NULL,\n" +
                "\t\"si_so\"\tINTEGER,\n" +
                "\t\"id_cvht\"\tvarchar(10) UNIQUE,\n" +
                "\t\"ten_cvht\"\tTEXT,\n" +
                "\t\"sdt_cvht\"\tTEXT,\n" +
                "\t\"ma_nganh\"\tvarchar(10),\n" +
                "\t\"ma_khoahoc\"\tvarchar(10),\n" +
                "\tPRIMARY KEY(\"ma_lop\"),\n" +
                "\tFOREIGN KEY(\"ma_nganh\") REFERENCES \"tb_chuyennganh\"(\"ma_nganh\") ON DELETE SET NULL ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"ma_khoahoc\") REFERENCES \"tb_khoahoc\"(\"ma_khoahoc\") ON DELETE SET NULL ON UPDATE CASCADE\n" +
                ")";

        String monChungSql = "CREATE TABLE \"tb_monhoc_chung\" (\n" +
                "\t\"ma_monhoc\"\tvarchar(10),\n" +
                "\t\"ten_monhoc\"\tTEXT NOT NULL,\n" +
                "\t\"so_tin_chi\"\tINTEGER NOT NULL,\n" +
                "\t\"diem_gk\"\tINTEGER NOT NULL,\n" +
                "\t\"diem_ck\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"ma_monhoc\")\n" +
                ")";

        String monChuyenSql = "CREATE TABLE \"tb_monhoc_chuyennganh\" (\n" +
                "\t\"ma_monhoc\"\tvarchar(10),\n" +
                "\t\"ma_nganh\"\tvarchar(10),\n" +
                "\t\"ten_monhoc\"\tTEXT NOT NULL,\n" +
                "\t\"so_tin_chi\"\tINTEGER NOT NULL,\n" +
                "\t\"diem_gk\"\tINTEGER NOT NULL,\n" +
                "\t\"diem_ck\"\tINTEGER NOT NULL,\n" +
                "\tFOREIGN KEY(\"ma_nganh\") REFERENCES \"tb_chuyennganh\"(\"ma_nganh\") ON DELETE SET NULL ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"ma_monhoc\")\n" +
                ")";

        String sinhVienSql = "CREATE TABLE \"tb_sinhvien\" (\n" +
                "\t\"ma_sv\"\tvarchar(10),\n" +
                "\t\"ho_dem_sv\"\tTEXT NOT NULL,\n" +
                "\t\"ten_sv\"\tTEXT NOT NULL,\n" +
                "\t\"nam_sinh\"\tTEXT NOT NULL,\n" +
                "\t\"gioi_tinh\"\tTEXT NOT NULL,\n" +
                "\t\"noi_sinh\"\tTEXT,\n" +
                "\t\"anh_sv\"\tBLOB,\n" +
                "\t\"sdt_sv\"\tTEXT,\n" +
                "\t\"email_sv\"\tTEXT,\n" +
                "\t\"ma_lop\"\tvarchar(10),\n" +
                "\tFOREIGN KEY(\"ma_lop\") REFERENCES \"tb_lop\"(\"ma_lop\") ON DELETE SET NULL ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"ma_sv\")\n" +
                ")";

        String userSql = "CREATE TABLE \"tb_user\" (\n" +
                "\t\"user_id\"\tvarchar(10),\n" +
                "\t\"ten_nguoi_dung\"\tTEXT,\n" +
                "\t\"username\"\tTEXT UNIQUE,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\t\"email\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"user_id\")\n" +
                ")";

        db.execSQL(userSql);
        db.execSQL(lichSuSql);
        db.execSQL(khoaHocSql);
        db.execSQL(monChungSql);
        db.execSQL(ctDaoTaoSql);
        db.execSQL(chuyenNganhSql);
        db.execSQL(lopSql);
        db.execSQL(sinhVienSql);
        db.execSQL(monChuyenSql);
        db.execSQL(diemSql);
        db.execSQL(chiTietDiemSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String chuyenNganhSql = "DROP TABLE IF EXISTS tb_chuyennganh";
        String ctDaotaoSql = "DROP TABLE IF EXISTS tb_ct_daotao";
        String userSql = "DROP TABLE IF EXISTS tb_user";
        String lichSuSql = "DROP TABLE IF EXISTS tb_lichsu";
        String khoaHocSql = "DROP TABLE IF EXISTS tb_khoahoc";
        String monChungSql = "DROP TABLE IF EXISTS tb_monhoc_chung";
        String chiTietDiemSql = "DROP TABLE IF EXISTS tb_diem_chi_tiet";
        String diemSql = "DROP TABLE IF EXISTS tb_diem";
        String sinhVienSql = "DROP TABLE IF EXISTS tb_sinhvien";
        String lopSql = "DROP TABLE IF EXISTS tb_lop";
        String monChuyenSql = "DROP TABLE IF EXISTS tb_monhoc_chuyennganh";

        db.execSQL(lichSuSql);
        db.execSQL(chiTietDiemSql);
        db.execSQL(diemSql);
        db.execSQL(sinhVienSql);
        db.execSQL(monChungSql);
        db.execSQL(monChuyenSql);
        db.execSQL(lopSql);
        db.execSQL(khoaHocSql);
        db.execSQL(chuyenNganhSql);
        db.execSQL(ctDaotaoSql);
        db.execSQL(userSql);

        onCreate(db);
    }
}
