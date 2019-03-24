package com.engtrcard.vdtgk.eng_trkartlar.cardUtils;

import android.content.Context;
import com.engtrcard.vdtgk.eng_trkartlar.db.Constants;
import com.engtrcard.vdtgk.eng_trkartlar.db.Database;
import com.engtrcard.vdtgk.eng_trkartlar.retrofit.MyWord;

import java.util.HashMap;
import java.util.Random;

public class InitQuest {


    Database database;
    int count;


    public InitQuest(Context context) {
        database = new Database(context);
        count = database.DbSize();
    }


    public Database getDatabase() {
        return database;
    }

    public HashMap<String, String> getNextQuest() {
        int index;

        HashMap<String, String> hashMap;
        do {
            index = 1 + (new Random().nextInt(count));
            hashMap = getQuest(index);
        } while (Integer.valueOf(hashMap.get(Constants.TRUE)) > 1);

        int random;
        do {
            random = getRandom();

        } while (index == random);


        //hashmap e yanlış seçenekleri ekliyor

        hashMap.put(Constants.FALSE_ENG,
                database.GetFromID(random).get(Constants.ENG));
        hashMap.put(Constants.FALSE_TR,
                database.GetFromID(random).get(Constants.TR1));
        //--------------


        return hashMap;
    }

    private HashMap<String, String> getQuest(int index) {
        HashMap<String, String> req;
        req = database.GetFromID(index);

        return req;
    }

    public int getRandom() {

        Random random = new Random();
        int a = random.nextInt(count) + 1;

        return a;
    }


    public HashMap<String,String> getMyWord(){
        int index;

        HashMap<String, String> hashMap;
        do {
            index = 1 + (new Random().nextInt(count));
            hashMap = getQuest(index);
        } while (Integer.valueOf(hashMap.get(Constants.TRUE)) > 1);

        return hashMap;
    }


}
