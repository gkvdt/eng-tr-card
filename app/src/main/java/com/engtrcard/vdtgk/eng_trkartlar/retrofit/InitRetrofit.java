package com.engtrcard.vdtgk.eng_trkartlar.retrofit;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class InitRetrofit {


    Retrofit retrofit;
    List<MyWord> myWords;
    public InitRetrofit(){

        retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        Call<List<MyWord>> getMyWords = api.getWords();

        getMyWords.enqueue(new Callback<List<MyWord>>() {
            @Override
            public void onResponse(Call<List<MyWord>> call, Response<List<MyWord>> response) {
                myWords = response.body();
            }

            @Override
            public void onFailure(Call<List<MyWord>> call, Throwable t) {

            }
        });
    }





    public List<MyWord> getMyWords() {
        Log.d(TAG, "onResponse: " + myWords.get(0).getEngWord());

        //mConnect();
        return myWords;
    }
}