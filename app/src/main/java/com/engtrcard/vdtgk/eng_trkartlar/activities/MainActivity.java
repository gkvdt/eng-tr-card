package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.engtrcard.vdtgk.eng_trkartlar.R;
import com.engtrcard.vdtgk.eng_trkartlar.db.AddDataFromJson;
import com.engtrcard.vdtgk.eng_trkartlar.db.Constants;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;
    TextView tv1,tv2,tv3;
    AddDataFromJson utils;

    HashMap<String,String> hm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utils = new AddDataFromJson(getApplicationContext());

        hm = new HashMap<String, String>();

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);



        //recreate db
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.getDatabase().ReCreateDB();
            }
        });

        //add data to db

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.DataFromJsonToSqlite();
            }
        });


        //start

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity(1);
            }
        });


    }

    public void ChangeActivity(int index){
        switch (index) {
            case 1:
                startActivity(new Intent(this, Card.class));
                break;
        }
    }
}
