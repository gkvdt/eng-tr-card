package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

        //utils = new AddDataFromJson(getApplicationContext());

        //hm = new HashMap<String, String>();

//        btn1=findViewById(R.id.btn1);
//        btn2=findViewById(R.id.btn2);
//        btn3=findViewById(R.id.btn3);

        // TODO: 12/1/18 check db version


        if (dataChanged()){
            new getData(this).execute("");

        }else {
            ChangeActivity(1);
        }



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



    }
    private boolean dataChanged(){
        // TODO: 12/1/18 veri değişikliği kontrol ettir
        // TODO: 12/1/18 suan veriyi sürekli güncelliyor


        return true;
    }

    public void ChangeActivity(int index){
        switch (index) {
            case 1:
                startActivity(new Intent(this, Card.class));
                break;
        }
    }

    protected class getData extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog;
        AddDataFromJson utils;
        MainActivity activity;
        public getData(MainActivity activity){
            this.activity = activity;
            //utils = new AddDataFromJson(activity.getApplicationContext());

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMax(100);
            // progressDialog.setCancelable(false);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            utils.getDatabase().ReCreateDB();
            utils.DataFromJsonToSqlite();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            activity.ChangeActivity(1);

        }
    }
}
