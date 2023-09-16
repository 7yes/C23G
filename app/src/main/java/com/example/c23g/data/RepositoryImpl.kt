package com.example.c23g.data

import android.util.Log
import com.example.c23g.data.network.ApiService
import com.example.c23g.domain.Repository
import com.example.c23g.domain.model.PredictionModel
import com.example.c23g.domain.model.toDomain
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository {
    override suspend fun getPrediction(sing: String): PredictionModel? {
        runCatching { api.getHoroscope(sing) }
            .onFailure { Log.i("TAG", "RepositoryImpl getPrediction: error ${it.message} ") }
            .onSuccess { return it.toDomain() }
        return null
    }
}