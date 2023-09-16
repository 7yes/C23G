package com.example.c23g.domain

import com.example.c23g.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sing:String): PredictionModel?
}