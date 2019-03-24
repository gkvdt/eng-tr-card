package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
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

    private String TAG = "Main activty";
    AddDataFromJson data;
    private TextView tv;

    @Override
    protected void onRestart() {
        super.onRestart();

        startActivity(new Intent(this, activity_menu.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("*****************************************************");

        tv = findViewById(R.id.textView);

        init();

    }

    private void init() {
        new MyTask().execute("");
        Log.e(TAG, "onClick: asdfasdfsadfsadfsadf");
    }


    protected class MyTask extends AsyncTask<String, String, List<MyWord>> {
        List<MyWord> myWords;

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
                    tv.setText("Veri Tabanı Değişiklikleri Kontrol Ediliyor.");
                    myWords = response.body();
                    data = new AddDataFromJson(MainActivity2.this, myWords);
                    Log.e(TAG, "onResponse:  db size" + data.getDatabase().DbSize());
                    Log.e(TAG, "onResponse:  myword size" + myWords.size());
                    if (data.getDatabase().DbSize() < myWords.size()) {
                        tv.setText("Yeni Kelimeler Bulundu. \nYeni Kelimeler Ekleniyor.");

                        data.getDatabase().ReCreateDB();
                        data.DataFromJsonToSqlite();
                        Log.e(TAG, "onResponse: data  inserted");
                    } else {
                        tv.setText("Veri Tabanı Değişiklikliği Yok. \nKartlar Açılıyor.");

                        Log.e(TAG, "onResponse: data  not inserted");
                    }
                }

                @Override
                public void onFailure(Call<List<MyWord>> call, Throwable t) {

                    Log.e(TAG, "onFailure: data created");

                }
            });


            return myWords;
        }

        @Override
        protected void onPostExecute(List<MyWord> myWords) {
            super.onPostExecute(myWords);

            data = new AddDataFromJson(MainActivity2.this);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (data.getDatabase().DbSize() > 0) {
                        startActivity(new Intent(MainActivity2.this, activity_menu.class));
                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                        alert.setMessage("İnternet bağlantısı olmadığı için kelimeler alınamadı.\nİnternet bağlantınızı bir seferlik açarak kelimelerin eklenmesini sağlamanız gerekiyor.");
                        alert.setTitle("Bağlantı Hatası");
                        alert.setPositiveButton("Yeniden Dene", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                init();
                            }
                        });
                        alert.show();
                    }
                }
            }, 1200);


        }
    }
}
