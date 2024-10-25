package com.example.catfood.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catfood.R;
import com.example.catfood.model.asqltk;
import java.util.ArrayList;

public class aad_qltaikhoancuslvitem extends ArrayAdapter<asqltk> {
    private Activity content;
    private ArrayList<asqltk> ar;
    int layd;

    public aad_qltaikhoancuslvitem(Activity content, int resource, ArrayList<asqltk> ar) {
        super(content, resource, ar);
        this.content = content;
        this.ar = ar;
        layd = resource;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        LayoutInflater layi = content.getLayoutInflater();
        view = layi.inflate(R.layout.aad_quanlytaikhoancusitem, viewGroup,false);
        ImageView imgtk = view.findViewById(R.id.imgtk);
        TextView etten = view.findViewById(R.id.etten);
        TextView etpa = view.findViewById(R.id.etpa);
        TextView etchucvu = view.findViewById(R.id.etchucvu);

        String aa = ar.get(i).getCol_pic();
        Bitmap bitmap = BitmapFactory.decodeFile(aa);
        if (bitmap != null) {
            imgtk.setImageBitmap(bitmap);
        } else {
            imgtk.setImageResource(R.drawable.adtongmua);
        }
        etten.setText(ar.get(i).getCol_name());
        etpa.setText(ar.get(i).getCol_pas());
        String chucvu = ar.get(i).getCol_chucvu().equals("Admin")? "Chức vụ: Admin" : "Chức vụ: Khách";
        etchucvu.setText(chucvu);
        return view;
    }

}
