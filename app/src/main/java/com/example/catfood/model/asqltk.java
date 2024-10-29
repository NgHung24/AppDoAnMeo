package com.example.catfood.model;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import com.example.catfood.activity.aad_qltaikhoan;
import com.example.catfood.activity.aad_qltaikhoanthem;
import com.example.catfood.activity.aquenmatKhau;

public class asqltk extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "qltaikhoan.db";
    private static final int DATABASE_VERSION = 1;
    private String tbname = "qltk";
    private String col_id = "id";
    private String col_name = "name";
    private String col_sdt = "sdt";
    private String col_email = "email";
    private String col_pic = "picture";
    private String col_chucvu = "cv";
    private String col_pas = "pass";

    public String getCol_id() {
        return col_id;
    }

    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }

    public String getTbname() {
        return tbname;
    }

    public String getCol_name() {
        return col_name;
    }

    public String getCol_sdt() {
        return col_sdt;
    }

    public String getCol_email() {
        return col_email;
    }

    public String getCol_pic() {
        return col_pic;
    }

    public String getCol_chucvu() {
        return col_chucvu;
    }

    public String getCol_pas() {
        return col_pas;
    }

    public void setTbname(String tbname) {
        this.tbname = tbname;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public void setCol_sdt(String col_sdt) {
        this.col_sdt = col_sdt;
    }

    public void setCol_email(String col_email) {
        this.col_email = col_email;
    }

    public void setCol_pic(String col_pic) {
        this.col_pic = col_pic;
    }

    public void setCol_chucvu(String col_chucvu) {
        this.col_chucvu = col_chucvu;
    }

    public void setCol_pas(String col_pas) {
        this.col_pas = col_pas;
    }

    public asqltk(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + tbname + " ("
                + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_name + " TEXT, "
                + col_sdt + " TEXT, "
                + col_email + " TEXT, "
                + col_pic + " BLOB, "
                + col_chucvu + " TEXT, "
                + col_pas + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu nếu cần
        db.execSQL("DROP TABLE IF EXISTS " + tbname);
        onCreate(db);
    }
    public void addtaikhoan(Context context, String name, String num, String mail, String pic, String ck, String p1){
        SQLiteDatabase dbtk = this.getWritableDatabase();
        ContentValues cl = new ContentValues();
        cl.put(col_name, name);
        cl.put(col_sdt, num);
        cl.put(col_email, mail);
        cl.put(col_pic, pic);
        cl.put(col_chucvu, ck);
        cl.put(col_pas,p1);
        String mes;

        if(dbtk.insert(tbname, null, cl) == -1){
            mes = "THẤT BẠI";
        }else{
            mes = "THÀNH CÔNG !!!";
        }
        Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
        dbtk.close();

    }
    public void suatk(Context context,String id, String name){
        SQLiteDatabase upsql = this.getWritableDatabase();
        ContentValues cl = new ContentValues();
        cl.put("name",name);
        String mes;

        if(upsql.update(tbname, cl, col_id + " = ?", new String[]{id}) == -1){
            mes = "Cập nhật thât bai" ;
        }else{
            mes = "Cập nhật thành công !!!";
        }
        Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
        upsql.close();
    }
    public void xoatk(Context context, String id){
        SQLiteDatabase sqlxoa = this.getWritableDatabase();
        Intent bay = new Intent(context, aad_qltaikhoan.class);
        String mes;
        if(sqlxoa.delete(tbname, col_id +" = ?", new String[]{id}) == -1){
            mes = "Xóa thất bại";
        }else{
            mes = "Xóa thành công";
        }
        bay.putExtra("tbao", mes);
        context.startActivity(bay);
        sqlxoa.close();
    }
}
