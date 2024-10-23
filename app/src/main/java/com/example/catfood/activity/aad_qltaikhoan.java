package com.example.catfood.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import com.example.catfood.R;

import java.util.ArrayList;

public class aad_qltaikhoan extends Activity {
    ImageButton btnqlaitk, imgtimtk;
    Button btnttk, btnxuatdata;
    EditText ettimtk;
    ListView lvtk;
    ArrayList<String> altk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aad_quanlytaikhoan);

        ettimtk = findViewById(R.id.ettimtk); //edittext
        btnqlaitk = findViewById(R.id.btnqlaitk);
        imgtimtk = findViewById(R.id.imgtimtk);
        btnqlaitk = findViewById(R.id.btnqlaitk);
        btnttk = findViewById(R.id.btnttk);
        btnxuatdata = findViewById(R.id.btnxuatdata);
        lvtk = findViewById(R.id.lvtk);


    }

}
