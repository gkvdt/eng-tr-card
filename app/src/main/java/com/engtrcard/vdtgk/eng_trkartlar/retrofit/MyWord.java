package com.engtrcard.vdtgk.eng_trkartlar.retrofit;

import com.google.gson.annotations.SerializedName;

public class MyWord {
    @SerializedName("eWord")
    private String eWord;
    @SerializedName("tWord1")
    private String tWord1;
    @SerializedName("tWord2")
    private String tWord2;
    @SerializedName("tWord3")
    private String tWord3;
    @SerializedName("tWord4")
    private String tWord4;
    @SerializedName("tWord5")
    private String tWord5;


    public MyWord(String eWord, String tWord1, String tWord2, String tWord3, String tWord4, String tWord5) {
        this.eWord = eWord;
        this.tWord1 = tWord1;
        this.tWord2 = tWord2;
        this.tWord3 = tWord3;
        this.tWord4 = tWord4;
        this.tWord5 = tWord5;
    }

    public String geteWord() {
        return eWord;
    }

    public String gettWord1() {
        return tWord1;
    }

    public String gettWord2() {
        return tWord2;
    }

    public String gettWord3() {
        return tWord3;
    }

    public String gettWord4() {
        return tWord4;
    }

    public String gettWord5() {
        return tWord5;
    }
}
