package com.engtrcard.vdtgk.eng_trkartlar.db;

import android.content.Context;

import com.engtrcard.vdtgk.eng_trkartlar.retrofit.InitRetrofit;
import com.engtrcard.vdtgk.eng_trkartlar.retrofit.MyWord;

import java.util.List;

public class AddDataFromJson {

    private Database database;
    private InitRetrofit retrofit;
    private List<MyWord> myWords;
    public AddDataFromJson(Context context){
        database = new Database(context);
        retrofit = new InitRetrofit();

    }

    public void DataFromJsonToSqlite(){

        myWords = retrofit.getMyWords();
        for (int i = 0; i<myWords.size();i++){
            database.AddData(myWords.get(i).getEngWord(),myWords.get(i).getTrWord());
        }
    }

    public Database getDatabase() {
        return database;
    }
}
