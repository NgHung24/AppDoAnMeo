package com.example.catfood.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.catfood.R;

public class trangcanhan_Tongdonmua extends Activity {
    ImageButton imgbtne;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangcanhan_tongdonmua);

        imgbtne = findViewById(R.id.imgbtne);
        imgbtne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
