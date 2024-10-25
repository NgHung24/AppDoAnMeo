package com.example.catfood.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import com.example.catfood.R;
import com.example.catfood.model.asqltk;

public class aad_qltaikhoanthem extends Activity {
    EditText etname, etnum, etmail, pa1, etpic;
    RadioGroup rgcv;
    RadioButton rba, rbk;
    Button btnxn, btnql;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aad_quanlytaikhoanthem);

        etname = findViewById(R.id.etname); // edittext
        etnum = findViewById(R.id.etnum);
        etmail = findViewById(R.id.etmail);
        etpic = findViewById(R.id.etpic);
        rgcv = findViewById(R.id.rgcv);
        pa1 = findViewById(R.id.pa1);
        btnxn = findViewById(R.id.btnxn);
        btnql = findViewById(R.id.btnql);
        rba = findViewById(R.id.rba);
        rbk = findViewById(R.id.rbk);

        btnxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etname.getText().toString().trim();
                String num = etnum.getText().toString().trim();
                String mail = etmail.getText().toString().trim();
                String pic = etpic.getText().toString().trim();
                int rgchon = rgcv.getCheckedRadioButtonId();
                String ck = "";
                if(rgchon == R.id.rba){
                    ck = "Admin";
                }else{
                    ck = "Khách";
                }
                String p1 = pa1.getText().toString().trim();

                asqltk mydb = new asqltk(aad_qltaikhoanthem.this);

                // Kiểm tra quyền đọc bộ nhớ
                if (ActivityCompat.checkSelfPermission(aad_qltaikhoanthem.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(aad_qltaikhoanthem.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    // Thực hiện chèn dữ liệu vào cơ sở dữ liệu
                    mydb.addtaikhoan(aad_qltaikhoanthem.this ,name, num, mail, pic, ck, p1);

                    // Xóa nội dung sau khi thêm
                    etname.setText("");
                    etnum.setText("");
                    etmail.setText("");
                    etpic.setText("");
                    rba.setChecked(false);
                    rbk.setChecked(true);
                    pa1.setText("");
                    etname.requestFocus();
                }
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
