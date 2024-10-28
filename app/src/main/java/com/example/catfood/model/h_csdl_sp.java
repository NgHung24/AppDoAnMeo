package com.example.catfood.model;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class h_csdl_sp extends SQLiteOpenHelper {

    private String TABLE_NAME = "QL_SP"; // Tên bảng
    private String COLUMN_ID = "masp";
    private String COLUMN_NAME = "tensp";
    private String COLUMN_PRICE = "gia";
    private String COLUMN_TYPE = "loaisp";
    private String COLUMN_QUANTITY = "soluong";
    private String COLUMN_DESCRIPTION = "mota";
    private String COLUMN_IMAGE_LINK = "linkanh";
    // Constructor
    public h_csdl_sp(Context context) {
        super(context, "ql_SP.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Câu lệnh tạo bảng
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME +" TEXT, " +
                COLUMN_TYPE +" TEXT, " +
                COLUMN_PRICE +" DOUBLE, " +
                COLUMN_QUANTITY +" INTEGER, " +
                COLUMN_DESCRIPTION +" TEXT, " +
                COLUMN_IMAGE_LINK +" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); // Tạo lại bảng mới
    }

    // Phương thức thêm sản phẩm
    public Boolean themsp(String ten, String loai, double gia, int soluong, String mota, String link_anh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Đặt giá trị cho các cột
        cv.put(COLUMN_NAME, ten);
        cv.put(COLUMN_TYPE, loai);
        cv.put(COLUMN_PRICE, gia);
        cv.put(COLUMN_QUANTITY, soluong);
        cv.put(COLUMN_DESCRIPTION, mota);
        cv.put(COLUMN_IMAGE_LINK, link_anh);

        // Thực hiện chèn dữ liệu vào bảng
        long result = db.insert(TABLE_NAME, null, cv);

        // Kiểm tra kết quả chèn và trả về Boolean
        return result != -1;
    }

    public void xoasp(int maSP) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "masp=?", new String[]{String.valueOf(maSP)});
        db.close();
    }

    public ArrayList<SanPham> hienthisp() {
        ArrayList<SanPham> sanPhamList = new ArrayList<>();

        // Truy vấn cơ sở dữ liệu để lấy dữ liệu sản phẩm
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null); // Cập nhật tên bảng

        if (cursor.moveToFirst()) {
            do {
                int masp = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String tensp = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                double gia = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
                int soluong = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
                String loaisp = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                String mota = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                String linkanh = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_LINK));

                // Kiểm tra trường hợp null và xử lý nếu cần
                if (tensp == null) {
                    // Xử lý trường hợp tên sản phẩm null
                }

                // Tạo đối tượng SanPham và thêm vào danh sách
                SanPham sanPham = new SanPham(masp, tensp, loaisp, gia, soluong,  mota, linkanh);
                sanPhamList.add(sanPham);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sanPhamList;
    }

    public boolean capnhatsp(int maSP, String tenSP, String loaiSP, double gia, int soLuong, String moTa, String linkAnh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, tenSP);
        values.put(COLUMN_TYPE, loaiSP);
        values.put(COLUMN_PRICE, gia);
        values.put(COLUMN_QUANTITY, soLuong);
        values.put(COLUMN_DESCRIPTION, moTa);
        values.put(COLUMN_IMAGE_LINK, linkAnh);

        int result = db.update(TABLE_NAME, values, "masp=?", new String[]{String.valueOf(maSP)});
        db.close();

        return result > 0;
    }

    // Phương thức lấy sản phẩm theo mã sản phẩm
    public SanPham laySanPhamTheoMa(int maSP) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        SanPham sanPham = null;

        try {
            // Truy vấn cơ sở dữ liệu để lấy sản phẩm theo mã sản phẩm
            cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=?", new String[]{String.valueOf(maSP)},
                    null, null, null);

            // Nếu tìm thấy sản phẩm với mã sản phẩm, khởi tạo đối tượng SanPham
            if (cursor != null && cursor.moveToFirst()) {
                int masp = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String tensp = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String loaisp = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                double gia = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
                int soluong = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
                String mota = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                String linkanh = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_LINK));

                sanPham = new SanPham(masp, tensp, loaisp, gia, soluong, mota, linkanh);
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return sanPham;
    }


}
