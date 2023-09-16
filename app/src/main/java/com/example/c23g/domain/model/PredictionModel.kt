package com.example.c23g.domain.model

import com.example.c23g.data.network.model.PredictionResponse

data class PredictionModel (
    val horoscopeResult: String,
    val sign: String
)

fun PredictionResponse.toDomain() = PredictionModel( horoscope, sign)