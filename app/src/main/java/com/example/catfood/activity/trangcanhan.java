package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.catfood.R;

public class trangcanhan extends Activity {
    TextView tvname;
    Button btnmua, btncanhan, btnxuat;
    ImageButton btnthoat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangcanhan_a);

        tvname = findViewById(R.id.tvname);
        btnthoat = findViewById(R.id.btnthoat);
        btnmua = findViewById(R.id.btnmua);
        btncanhan = findViewById(R.id.btncanhan);
        btnxuat = findViewById(R.id.btnxuat);

        Intent itcn = getIntent();
        Bundle getb = itcn.getBundleExtra("paccc");
        String gb = getb.getString("na");
        tvname.setText(gb);

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itdm = new Intent(trangcanhan.this, trangcanhan_Tongdonmua.class);
                startActivity(itdm);
            }
        });
        btnxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itxuat = new Intent(trangcanhan.this, adangNhap.class);
                startActivity(itxuat);
            }
        });
    }
}
