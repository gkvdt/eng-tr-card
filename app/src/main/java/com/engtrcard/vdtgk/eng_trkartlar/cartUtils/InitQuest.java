package com.engtrcard.vdtgk.eng_trkartlar.cartUtils;

import android.content.Context;

import com.engtrcard.vdtgk.eng_trkartlar.db.Constants;
import com.engtrcard.vdtgk.eng_trkartlar.db.Database;
import com.engtrcard.vdtgk.eng_trkartlar.retrofit.MyWord;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class InitQuest {

    int index = 1;
    Database database;
    int count;

    public InitQuest(Context context){
        database = new Database(context);
        count = database.DbSize();
    }


    public HashMap<String,String> getNextQuest(){

        HashMap<String,String> hashMap = database.GetFromID(index);
        int random;

        do {
            random = getRandom();

        }while (index==random);

        //hashmap e yanlış seçenekleri ekliyor

        hashMap.put(Constants.FALSE_ENG,
                database.GetFromID(random).get(Constants.ENG));
        hashMap.put(Constants.FALSE_TR,
                database.GetFromID(random).get(Constants.TR));
        //--------------


        /* todo bug var
        if (Integer.valueOf(hashMap.get(Constants.TRUE))>=5){
            index++;
            getNextQuest();
        }

        */
        index++;

        return hashMap;
    }

    public int getRandom(){

        Random random = new Random();
        int a =random.nextInt(count)+1;

        return a;
    }

    // TODO: 11/22/18 doğru bilinen sorunun true değeri 1 arttırılacak yanlışsa 1 azaltılacak

    // 11/22/18 sıradaki sorunun true değeri 5 ise soru atlanacak

    // 11/22/18  yanlış şık random getirilecek

    // TODO: 11/22/18 sorular bitince napacak ?????

     
}
