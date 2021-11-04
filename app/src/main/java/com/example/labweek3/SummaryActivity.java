package com.example.labweek3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        PersonInfo pi = (PersonInfo) getIntent().getParcelableExtra("pi");

        TextView nl=findViewById(R.id.name_lastname);
        TextView cz=findViewById(R.id.city_zip);
        TextView dtl=findViewById(R.id.detailTextview);

        Log.d("Summary", "Detail data added: "+ pi.getDetail());

        nl.setText("Name : "+pi.getName()+" LastName : "+pi.getLastname());
        cz.setText("City : "+pi.getCity()+" Zip : "+pi.getZip());
        dtl.setText("Details : "+pi.getDetail());
    }
}