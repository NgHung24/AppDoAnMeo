package com.example.catfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.catfood.R;
import com.example.catfood.model.SanPham;

import java.util.ArrayList;

public class h_MainActivity extends AppCompatActivity {
    Button btncanhan, btngiohang;
    String[] loai = {"Tất cả", "Thức ăn hạt", "Thức ăn ướt"};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.h_activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btTimKiem = findViewById(R.id.imageButton_main_TimKiem);
        EditText TimKiem = findViewById(R.id.editText_main_TimKiem);

        listView = findViewById(R.id.listView_main_LV);

        btncanhan = findViewById(R.id.btncanhan);
        btngiohang = findViewById(R.id.btngiohang);


        btncanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itcn = new Intent(h_MainActivity.this, trangcanhan.class);
                startActivity(itcn);
            }
        });
        btngiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itgh = new Intent(h_MainActivity.this, agiohang.class);
                startActivity(itgh);
            }
        });

        Spinner spinner = findViewById(R.id.spinner_main);

// Tạo Adapter cho Spinner từ mảng dữ liệu categories
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

// Xử lý sự kiện chọn mục trên Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = loai[position];
                // Xử lý logic dựa trên mục đã chọn
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Xử lý khi không có mục nào được chọn
            }
        });

        updateListView();

    }

    private void updateListView() {
        h_csdl_sp db = new h_csdl_sp(this);
        ArrayList<SanPham> productList = db.hienthisp();
        h_mainAdapter adapter = new h_mainAdapter(this, productList);
        listView.setAdapter(adapter);
    }
}