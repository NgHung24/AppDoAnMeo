package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.SanPham;
import com.example.catfood.model.h_csdl_sp;

public class h_activity_subitemcenter extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_subitemcenter);
        TextView TenSP = findViewById(R.id.textView_subitemcenter_TenSP);
        TextView Gia = findViewById(R.id.textView_subitemcenter_GIA);
        TextView SoLuong = findViewById(R.id.textView_subitemcenter_SoLuong);
        TextView LoaiSP = findViewById(R.id.textView_subitemcenter_LoaiSP);
        TextView ThongTinSP = findViewById(R.id.textView_subitemcenter_ThongTinSP);

        EditText SoMua = findViewById(R.id.editText_subitemcenter_SoMua);

        Button ThemGioHang = findViewById(R.id.Button_subitemcenter_ThemGioHang);

        ImageButton QuayLai = findViewById(R.id.imageButton_subitemcenter_QuayLai);
        ImageView AnhSp = findViewById(R.id.imageView_subitemcenter_AnhSP);

        // Nhận mã sản phẩm từ Intent
        int maSP = getIntent().getIntExtra("MASP", -1);
        if (maSP != -1) {
            Log.d("h_activity_subitemcenter", "Received MASP: " + maSP);
        } else {
            Log.e("h_activity_subitemcenter", "Received invalid MASP");
        }

// Khởi tạo database helper
        h_csdl_sp databaseHelper = new h_csdl_sp(this);

// Kiểm tra và lấy thông tin sản phẩm nếu mã sản phẩm hợp lệ
        if (maSP != -1) {
            SanPham sanPham = databaseHelper.laySanPhamTheoMa(maSP);
            if (sanPham != null) {
                // Hiển thị thông tin sản phẩm lên giao diện
                TenSP.setText(sanPham.getTenSP());
                Gia.setText("Giá: " + sanPham.getGia() + "đ");
                SoLuong.setText(String.valueOf(sanPham.getSoLuong()));
                LoaiSP.setText("Loại sản phẩm: " + sanPham.getTheLoai());
                ThongTinSP.setText("Thông tin: " + sanPham.getMota());

                // Thiết lập ảnh sản phẩm từ link ảnh trong thư mục drawable
                int resID = getResources().getIdentifier(sanPham.getLinkAnh(), "drawable", getPackageName());
                if (resID != 0) {
                    AnhSp.setImageResource(resID);
                } else {
                    Log.e("h_activity_subitemcenter", "Image resource not found for linkAnh: " + sanPham.getLinkAnh());
                    AnhSp.setImageResource(R.drawable.ic_launcher_background); // đặt ảnh mặc định nếu không tìm thấy ảnh
                }
            }
        }



        QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(h_activity_subitemcenter.this, h_MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
