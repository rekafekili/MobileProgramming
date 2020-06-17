package com.example.question2.repository;

import com.example.question2.model.Covid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidService {
    String BASE_URL = "https://api.covid19api.com/";

    @GET("summary")
    Call<Covid> fetchCovidInfo();
}
