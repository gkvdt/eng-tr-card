package com.engtrcard.vdtgk.eng_trkartlar.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://raw.githubusercontent.com/gkvdt/test/master/";



    @GET("engtr.json")
    Call<List<MyWord>> getWords();

}
