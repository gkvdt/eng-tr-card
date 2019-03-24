package com.engtrcard.vdtgk.eng_trkartlar.db;

import android.content.Context;

import com.engtrcard.vdtgk.eng_trkartlar.retrofit.MyWord;

import java.util.List;

public class AddDataFromJson {

    private Database database;
    private List<MyWord> myWords;

    public AddDataFromJson(Context context){
        database = new Database(context);
    }
    public AddDataFromJson(Context context,List<MyWord> myWords){
        database = new Database(context);
        this.myWords = myWords;



    }

    public void DataFromJsonToSqlite(){
        for (int i = 0; i<myWords.size();i++){
            database.AddData(myWords.get(i).geteWord()
                    ,myWords.get(i).gettWord1()
                    ,myWords.get(i).gettWord2()
                    ,myWords.get(i).gettWord3()
                    ,myWords.get(i).gettWord4()
                    ,myWords.get(i).gettWord5()

            );
        }
    }

    public Database getDatabase() {
        return database;
    }
}
