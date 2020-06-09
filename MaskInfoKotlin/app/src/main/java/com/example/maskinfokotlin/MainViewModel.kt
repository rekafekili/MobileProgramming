package com.example.maskinfokotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfokotlin.model.Store
import com.example.maskinfokotlin.repository.MaskService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private val service: MaskService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(MaskService::class.java)

        fetchStoreInfo()
    }

    fun fetchStoreInfo() {
        // 로딩 시작
        loadingLiveData.value = true

        // Kotlin Coroutine : 코틀린의 비동기 작동 방식(자바의 쓰레드 대체)
        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(37.188078, 127.043002)
            itemLiveData.value = storeInfo.stores
            
            // 로딩 끝
            loadingLiveData.value = false
        }
    }
}