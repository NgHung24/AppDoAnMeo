package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.SanPham;

import java.util.ArrayList;

public class h_ad_quanlysp extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_ad_quanlysp);

        ImageButton btQL = findViewById(R.id.imageButton_quanlysp_QuayLai);
        ImageButton btTimKiem = findViewById(R.id.imageButton_quanlysp_TimKiem);
        Button btThemSP = findViewById(R.id.button_quanlysp_ThemSP);
        Button btLamMoi = findViewById(R.id.button_quanlysp_LamMoiDL);
        EditText TimKiem = findViewById(R.id.editText_quanlysp_TimKiem);

        listView = findViewById(R.id.listView_quanlysp_LV);

        // Lấy dữ liệu từ cơ sở dữ liệu và hiển thị trong ListView
        updateListView();

        btThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(h_ad_quanlysp.this, h_qualyspthem.class);
                startActivity(intent);
            }
        });

        btLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListView();
            }
        });
    }

    // Phương thức cập nhật ListView với dữ liệu mới
    private void updateListView() {
        h_csdl_sp db = new h_csdl_sp(this);
        ArrayList<SanPham> productList = db.hienthisp();
        h_quanlyspAdapter adapter = new h_quanlyspAdapter(this, productList);
        listView.setAdapter(adapter);
    }
}
