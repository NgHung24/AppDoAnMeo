package com.example.catfood.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseCuaTuan extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "SQLDoAnMeo.db";
    private static final int DATABASE_VERSION = 3;

    private static String TABLE_NAME = "giohang";
    private static String cl_anhsp = "anhsp";
    private static String cl_tensp = "tensp";
    private static String cl_gia = "giasp";
    private static String cl_sl = "soluong";
    private static String cl_loaihang = "loaihang";

    // Định nghĩa bảng đơn hàng
    public static String tb_name = "donhang";
    public static String cl_madon = "madon";
    private static String cl_tenkh = "tenkh";
    private static String cl_sdtkh = "sdtkh";
    private static String cl_emailkh = "emailkh";
    private static String cl_diachinhanhang = "diachinhanhang";
    private static String cl_phthucthanhtoan = "phthucthanhtoan";
    private static String cl_tongtien = "tongtien";
    private static String cl_trangthai = "trangthai";

    //dinh dang chi tiet don hang
    private static String tb_Name = "chitietdonhang";
    private static String cl_Madon = "Madon";
    private static String cl_Anhsp = "Anhsp";
    private static String cl_Tensp = "Tensp";
    private static String cl_Giasp = "Giapsp";
    private static String cl_Sl = "Sl";
    private static String cl_Tongtien = "Tongtien";

    public DataBaseCuaTuan(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng giỏ hàng
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                cl_anhsp + " TEXT, " +
                cl_tensp + " TEXT, " +
                cl_gia + " DOUBLE, " +
                cl_sl + " INTEGER, " +
                cl_loaihang + " TEXT);";
        db.execSQL(query);

        // Tạo bảng đơn hàng
        String donhangQuery = "CREATE TABLE " + tb_name + " (" +
                cl_madon + " TEXT, " +
                cl_tenkh + " TEXT, " +
                cl_sdtkh + " TEXT, " +
                cl_emailkh + " TEXT, " +
                cl_diachinhanhang + " TEXT, " +
                cl_phthucthanhtoan + " TEXT, " +
                cl_tongtien + " DOUBLE, " +
                cl_trangthai + " TEXT);";
        db.execSQL(donhangQuery);

        //tao bang chi tiet don hang
        String donhangchitietQuery = "CREATE TABLE " + tb_Name + " (" +
                cl_Madon + " TEXT, " +
                cl_Anhsp + " TEXT, " +
                cl_Tensp + " TEXT, " +
                cl_Giasp + " DOUBLE, " +
                cl_Sl + " INTEGER, " +
                cl_Tongtien + " DOUBLE, " +
                cl_loaihang + " TEXT);";
        db.execSQL(donhangchitietQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tb_name);
        db.execSQL("DROP TABLE IF EXISTS " + tb_Name);
        onCreate(db);
    }

    // Phương thức thêm sản phẩm vào giỏ hàng
    public Boolean addGioHang(String anhsp, String namesp, double gia, int sl, String loaihang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(cl_anhsp, anhsp);
        cv.put(cl_tensp, namesp);
        cv.put(cl_gia, gia);
        cv.put(cl_sl, sl);
        cv.put(cl_loaihang, loaihang);

        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    // Phương thức xóa sản phẩm
    public void xoasp(String anhsp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "anhsp=?", new String[]{anhsp});
        db.close();
    }
    public void xoaGioHang() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    // Phương thức cập nhật sản phẩm
    public boolean capnhatsp(String anhsp, String tensp, double giasp, int soluong, String loaihang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(cl_anhsp, anhsp);
        values.put(cl_tensp, tensp);
        values.put(cl_gia, giasp);
        values.put(cl_sl, soluong);
        values.put(cl_loaihang, loaihang);

        int result = db.update(TABLE_NAME, values, "anhsp=?", new String[]{anhsp});
        db.close();
        return result > 0;
    }

    // Phương thức lấy danh sách giỏ hàng
    public ArrayList<Itemsp> hienthigiohang() {
        ArrayList<Itemsp> giohangList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String anhsp = cursor.getString(cursor.getColumnIndex(cl_anhsp));
                String tensp = cursor.getString(cursor.getColumnIndex(cl_tensp));
                double gia = cursor.getDouble(cursor.getColumnIndex(cl_gia));
                int soluong = cursor.getInt(cursor.getColumnIndex(cl_sl));
                String loaihang = cursor.getString(cursor.getColumnIndex(cl_loaihang));

                Itemsp item = new Itemsp(anhsp, tensp, gia, soluong, loaihang);
                giohangList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return giohangList;
    }

    // Phương thức lấy sản phẩm theo tên
    public Itemsp getSanPhamByTen(String tensp) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, cl_tensp + "=?", new String[]{tensp}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String anhsp = cursor.getString(cursor.getColumnIndex(cl_anhsp));
            double gia = cursor.getDouble(cursor.getColumnIndex(cl_gia));
            int soluong = cursor.getInt(cursor.getColumnIndex(cl_sl));
            String loaihang = cursor.getString(cursor.getColumnIndex(cl_loaihang));
            cursor.close();
            return new Itemsp(anhsp, tensp, gia, soluong, loaihang);
        }

        return null;
    }

    public boolean updateGioHang(String tensp, double gia, int soluong) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(cl_gia, gia);
        values.put(cl_sl, soluong);

        int result = db.update(TABLE_NAME, values, cl_tensp + "=?", new String[]{tensp});
        db.close();
        return result > 0;
    }

    // Phương thức thêm đơn hàng
    public Boolean addDonHang(String madon,String tenkh, String sdtkh, String emailkh, String diachikh, String pttt, double tongtien, String trangthai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Đặt giá trị cho các cột
        cv.put(cl_madon, madon);
        cv.put(cl_tenkh, tenkh);
        cv.put(cl_sdtkh, sdtkh); // Đổi kiểu dữ liệu cho sdtkh thành TEXT
        cv.put(cl_emailkh, emailkh);
        cv.put(cl_diachinhanhang, diachikh);
        cv.put(cl_phthucthanhtoan, pttt);
        cv.put(cl_tongtien, tongtien);
        cv.put(cl_trangthai, trangthai);

        // Thực hiện chèn dữ liệu vào bảng
        long result = db.insert(tb_name, null, cv);
        return result != -1;
    }

    // Phương thức cập nhật trạng thái đơn hàng
    public boolean updateDonHang(String madon, String trangthai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(cl_trangthai, trangthai);

        int result = db.update(tb_name, values, cl_madon + "=?", new String[]{madon});
        db.close();
        return result > 0;
    }

    // Hàm này để tự động tạo mã đơn hàng
    public static String taoMa(String fieldName, String table, SQLiteDatabase database) {
        long num = 1;
        String sql = "SELECT " + fieldName + " FROM " + table + " ORDER BY " + fieldName + " DESC LIMIT 1";
        Cursor cursor = null;

        try {
            cursor = database.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                String lastId = cursor.getString(cursor.getColumnIndexOrThrow(fieldName));
                try {
                    num = Long.parseLong(lastId.substring(2)) + 1;
                } catch (NumberFormatException e) {
                    System.out.println("Không thể phân tích '" + lastId.substring(2) + "' thành một số.");
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return String.format("MD%04d", num);
    }


    public ArrayList<donhang> hienthidonhang() {
        ArrayList<donhang> donhangList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tb_name, null);

        if (cursor.moveToFirst()) {
            do {
                String madon = cursor.getString(cursor.getColumnIndex(cl_madon));
                String tenkh = cursor.getString(cursor.getColumnIndex(cl_tenkh));
                String sdtkh = cursor.getString(cursor.getColumnIndex(cl_sdtkh));
                String emailkh = cursor.getString(cursor.getColumnIndex(cl_emailkh));
                String diachinhanhang = cursor.getString(cursor.getColumnIndex(cl_diachinhanhang));
                String ptthanhtoan = cursor.getString(cursor.getColumnIndex(cl_phthucthanhtoan));
                double tongtien = cursor.getDouble(cursor.getColumnIndex(cl_tongtien));
                String trangthai = cursor.getString(cursor.getColumnIndex(cl_trangthai));


                donhang dhang = new donhang(madon, tenkh, sdtkh, emailkh, diachinhanhang, ptthanhtoan, tongtien, trangthai);
                donhangList.add(dhang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donhangList;
    }


    // Phương thức thêm tất cả sản phẩm từ giỏ hàng vào chi tiết đơn hàng
    public Boolean addChiTiet(String madon, String anhsp, String namesp, double gia, int sl, double tongtien, String loaihang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(cl_Madon, madon);
        cv.put(cl_Anhsp, anhsp);
        cv.put(cl_Tensp, namesp);
        cv.put(cl_Giasp, gia);
        cv.put(cl_Sl, sl);
        cv.put(cl_Tongtien, tongtien);
        cv.put(cl_loaihang, loaihang); // Thêm cột loaihang

        long result = db.insert(tb_Name, null, cv);
        return result != -1;
    }

    public ArrayList<donhang> timKiemDonHang(String searchQuery) {
        ArrayList<donhang> donhangList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Tạo điều kiện cho câu truy vấn
        String whereClause = "(" + cl_madon + " LIKE ? OR " +
                cl_tenkh + " LIKE ? OR " +
                cl_sdtkh + " LIKE ? OR " +
                cl_emailkh + " LIKE ? OR " +
                cl_diachinhanhang + " LIKE ? OR " +
                cl_trangthai + " LIKE ?)";
        String[] whereArgs = new String[]{
                "%" + searchQuery + "%",
                "%" + searchQuery + "%",
                "%" + searchQuery + "%",
                "%" + searchQuery + "%",
                "%" + searchQuery + "%",
                "%" + searchQuery + "%"
        };

        Cursor cursor = db.query(tb_name, null, whereClause, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String madon = cursor.getString(cursor.getColumnIndex(cl_madon));
                String tenkh = cursor.getString(cursor.getColumnIndex(cl_tenkh));
                String sdtkh = cursor.getString(cursor.getColumnIndex(cl_sdtkh)); // Chuyển về String
                String emailkh = cursor.getString(cursor.getColumnIndex(cl_emailkh));
                String diachinhanhang = cursor.getString(cursor.getColumnIndex(cl_diachinhanhang));
                String ptthanhtoan = cursor.getString(cursor.getColumnIndex(cl_phthucthanhtoan));
                double tongtien = cursor.getDouble(cursor.getColumnIndex(cl_tongtien));
                String trangthai = cursor.getString(cursor.getColumnIndex(cl_trangthai));

                donhang dhang = new donhang(madon, tenkh, sdtkh, emailkh, diachinhanhang, ptthanhtoan, tongtien, trangthai);
                donhangList.add(dhang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donhangList;
    }

    public ArrayList<ChiTietDonHang> hienthiChiTietDonHang(String madon) {
        ArrayList<ChiTietDonHang> chiTietList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tb_Name, null, cl_Madon + "=?", new String[]{madon}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String mdon = cursor.getString(cursor.getColumnIndex(cl_Madon));
                String anhsp = cursor.getString(cursor.getColumnIndex(cl_Anhsp));
                String tensp = cursor.getString(cursor.getColumnIndex(cl_Tensp));
                double giasp = cursor.getDouble(cursor.getColumnIndex(cl_Giasp));
                int soluong = cursor.getInt(cursor.getColumnIndex(cl_Sl));
                double tongtien = cursor.getDouble(cursor.getColumnIndex(cl_Tongtien));
                String loaihang = cursor.getString(cursor.getColumnIndex(cl_loaihang));

                ChiTietDonHang chiTiet = new ChiTietDonHang(mdon,anhsp, tensp, giasp, soluong, tongtien, loaihang);
                chiTietList.add(chiTiet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return chiTietList;
    }
    // Phương thức lấy thông tin đơn hàng theo mã đơn
    public donhang getDonHangByMaDon(String madon) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tb_name, null, cl_madon + "=?", new String[]{madon}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String tenkh = cursor.getString(cursor.getColumnIndex(cl_tenkh));
            String sdtkh = cursor.getString(cursor.getColumnIndex(cl_sdtkh));
            String emailkh = cursor.getString(cursor.getColumnIndex(cl_emailkh));
            String diachinhanhang = cursor.getString(cursor.getColumnIndex(cl_diachinhanhang));
            String ptthanhtoan = cursor.getString(cursor.getColumnIndex(cl_phthucthanhtoan));
            double tongtien = cursor.getDouble(cursor.getColumnIndex(cl_tongtien));
            String trangthai = cursor.getString(cursor.getColumnIndex(cl_trangthai));

            cursor.close();
            return new donhang(madon, tenkh, sdtkh, emailkh, diachinhanhang, ptthanhtoan, tongtien, trangthai);
        }

        if (cursor != null) {
            cursor.close();
        }
        return null;
    }








}