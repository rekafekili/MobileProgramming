package com.example.maskinfo.repository;

import com.example.maskinfo.model.StoreInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MaskService {
    String BASE_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/";

    @GET("storesByGeo/json/")
    Call<StoreInfo> fetchStoreInfo();
}
