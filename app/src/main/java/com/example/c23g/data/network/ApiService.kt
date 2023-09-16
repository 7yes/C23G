package com.example.c23g.data.network

import com.example.c23g.data.network.model.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign :String):PredictionResponse
}