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
import com.example.catfood.adapter.h_mainAdapter;
import com.example.catfood.model.SanPham;
import com.example.catfood.model.h_csdl_sp;

import java.util.ArrayList;

public class h_MainActivity extends AppCompatActivity {
    Button btncanhan, btngiohang;
    String[] loai = {"Tất cả", "Thức ăn hạt", "Thức ăn ướt"};
    private ListView listView;
    private ArrayList<SanPham> originalProductList; // Danh sách sản phẩm ban đầu
    private h_mainAdapter adapter; // Adapter chính

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Xử lý sự kiện chọn mục trên Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = loai[position];
                filterProductList(selectedCategory); // Lọc danh sách dựa trên loại được chọn
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không cần xử lý khi không có mục nào được chọn
            }
        });

        btTimKiem.setOnClickListener(view -> {
            String keyword = TimKiem.getText().toString().trim();
            if (!keyword.isEmpty()) {
                timkiemSP(keyword);
            } else {
                updateListView();
            }
        });


        // Lấy dữ liệu ban đầu từ CSDL
        updateListView();
    }

    private void updateListView() {
        h_csdl_sp db = new h_csdl_sp(this);
        originalProductList = db.hienthisp(); // Lưu danh sách gốc của sản phẩm
        adapter = new h_mainAdapter(this, originalProductList);
        listView.setAdapter(adapter);
    }

    // Phương thức để lọc sản phẩm dựa trên loại
    private void filterProductList(String selectedCategory) {
        ArrayList<SanPham> filteredList = new ArrayList<>();

        if ("Tất cả".equals(selectedCategory)) {
            filteredList.addAll(originalProductList); // Hiển thị tất cả sản phẩm
        } else {
            // Thực hiện lọc danh sách theo loại sản phẩm
            for (SanPham sp : originalProductList) {
                if ((selectedCategory.equals("Thức ăn hạt") && "ThucAnHat".equals(sp.getTheLoai())) ||
                        (selectedCategory.equals("Thức ăn ướt") && "ThucAnUot".equals(sp.getTheLoai()))) {
                    filteredList.add(sp);
                }
            }
        }

        // Cập nhật lại adapter với danh sách đã lọc
        adapter = new h_mainAdapter(this, filteredList);
        listView.setAdapter(adapter);
    }

    private void timkiemSP(String keyword) {
        ArrayList<SanPham> filteredList = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // Để kiểm tra không phân biệt hoa thường

        for (SanPham sp : originalProductList) {
            // Kiểm tra từ khóa trong các thuộc tính khác nhau
            if (sp.getTenSP().toLowerCase().contains(lowerCaseKeyword) ||
                    String.valueOf(sp.getGia()).contains(lowerCaseKeyword) ||
                    sp.getTheLoai().toLowerCase().contains(lowerCaseKeyword)) {

                filteredList.add(sp); // Thêm sản phẩm vào danh sách nếu có thuộc tính nào chứa từ khóa
            }
        }

        // Cập nhật lại adapter với danh sách lọc
        adapter = new h_mainAdapter(this, filteredList);
        listView.setAdapter(adapter);
    }

}
