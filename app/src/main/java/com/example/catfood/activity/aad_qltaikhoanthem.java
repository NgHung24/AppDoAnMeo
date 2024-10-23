package com.example.catfood.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;

public class aad_qltaikhoanthem extends Activity {
    EditText etname, etnum, etmail, etpictk, pa1;
    CheckBox cb1,cb2;
    Button btnxn, btnql;
    SQLiteDatabase dbtk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aad_quanlytaikhoanthem);

        etname = findViewById(R.id.etname); // edittext
        etnum = findViewById(R.id.etnum);
        etmail = findViewById(R.id.etmail);
        etpictk = findViewById(R.id.etpictk);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        pa1 = findViewById(R.id.pa1);
        btnxn = findViewById(R.id.btnxn);
        btnql = findViewById(R.id.btnql);

        dbtk = openOrCreateDatabase("dbtaikhoan.db", MODE_PRIVATE, null);
        try{
            String tksql = "CREATE TABLE tbtaikhoan(tentk TEXT primary key, sdttk TEXT, mailtk TEXT, pictk TEXT, chucvu INTERGER, passtk TEXT)";
            dbtk.execSQL(tksql);
        }catch (Exception e){
            Log.e("Error","Table đã tồn tại");
        }

        btnxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etname.getText().toString();
                String num = etnum.getText().toString();
                String mail = etmail.getText().toString();
                String pic = etpictk.getText().toString();
                Boolean c1 = cb1.isChecked();
                Boolean c2 = cb2.isChecked();
                int ck = (c1 == true) ? 1: 0;
                String p1 = pa1.getText().toString();

                ContentValues cl = new ContentValues();
                cl.put("tentk", name);
                cl.put("sdttk", num);
                cl.put("mailtk", mail);
                cl.put("pictk", pic);
                cl.put("chucvu", ck);
                cl.put("passtk",p1);
                String mes;
                if(dbtk.insert("tbtaikhoan", null, cl) == -1){
                    mes = "Thêm thất bại";
                }else{
                    mes = "Đã thêm thành công !!!";
                }
                Toast.makeText(aad_qltaikhoanthem.this, mes, Toast.LENGTH_LONG).show();
                etname.setText("");
                etnum.setText("");
                etmail.setText("");
                etpictk.setText("");
                cb1.setChecked(false);
                cb2.setChecked(true);
                pa1.setText("");
                etname.requestFocus();
            }
        });
        btnql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
