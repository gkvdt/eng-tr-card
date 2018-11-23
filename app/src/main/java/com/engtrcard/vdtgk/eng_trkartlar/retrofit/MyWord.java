package com.engtrcard.vdtgk.eng_trkartlar.retrofit;

import com.google.gson.annotations.SerializedName;

public class MyWord {
    @SerializedName("eng")
    private String engWord;
    @SerializedName("tr")
    private String trWord;



    public String getEngWord() {
        return engWord;
    }

    public String getTrWord() {
        return trWord;
    }

    public MyWord(String engWord, String trWord) {

        this.engWord = engWord;
        this.trWord = trWord;
    }



}
