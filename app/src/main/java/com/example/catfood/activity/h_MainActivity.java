package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.catfood.R;

public class h_MainActivity extends Activity {
    Button btncanhan, btngiohang;
    String gg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_main);

        //dđoạn này hiện của hoàng anh để hiển thị tên cho bên trang cá nhân khách
        Intent ittg = getIntent();
        Bundle bd = ittg.getBundleExtra("pacc");
        gg = bd.getString("namee");


        btncanhan = findViewById(R.id.btncanhan);
        btngiohang = findViewById(R.id.btngiohang);

        btncanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itcn = new Intent(h_MainActivity.this, trangcanhan.class);
                Bundle g = new Bundle();
                g.putString("na", gg);
                itcn.putExtra("paccc",g);
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
    }
}