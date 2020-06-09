package com.example.maskinfokotlin.repository

import com.example.maskinfokotlin.model.StoreInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    companion object {
        const val BASE_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/"
    }

    @GET("storesByGeo/json/?m=5000")
    suspend fun fetchStoreInfo( // suspend : 비동기로 오랫동안 동작할 코드 표시
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): StoreInfo // Retrofit이 Kotlin을 지원하여 Call 객체 대신 StoreInfo 객체로 받을 수 있음
}