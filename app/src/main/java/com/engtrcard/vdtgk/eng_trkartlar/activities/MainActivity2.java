package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.engtrcard.vdtgk.eng_trkartlar.R;
import com.engtrcard.vdtgk.eng_trkartlar.db.AddDataFromJson;
import com.engtrcard.vdtgk.eng_trkartlar.retrofit.API;
import com.engtrcard.vdtgk.eng_trkartlar.retrofit.MyWord;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onRestart() {
        super.onRestart();

        startActivity(new Intent(this, Card.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("*****************************************************");


        new MyTask().execute("");





    }

    protected class MyTask extends AsyncTask<String,String,List<MyWord>>{
        List<MyWord>  myWords;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<MyWord> doInBackground(String... strings) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API api = retrofit.create(API.class);

            Call<List<MyWord>> getMyWord = api.getWords();

            getMyWord.enqueue(new Callback<List<MyWord>>() {

                @Override
                public void onResponse(Call<List<MyWord>> call, Response<List<MyWord>> response) {
                     myWords = response.body();
                    AddDataFromJson data = new AddDataFromJson(MainActivity2.this,myWords);
                    data.getDatabase().ReCreateDB();
                    data.DataFromJsonToSqlite();

                }

                @Override
                public void onFailure(Call<List<MyWord>> call, Throwable t) {

                }
            });



            return myWords;
        }

        @Override
        protected void onPostExecute(List<MyWord> myWords) {
            super.onPostExecute(myWords);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity2.this, Card.class));
                }
            }, 1200);


        }
    }
}
