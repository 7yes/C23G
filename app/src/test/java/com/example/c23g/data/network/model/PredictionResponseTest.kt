package com.example.c23g.data.network.model

import com.example.c23g.domain.model.toDomain
import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Test

class PredictionResponseTest{
    @Test
    fun `toDomain should return a correct PredictionModel`(){
        //Given
        val horoscopeResponse = PredictionResponse("date","prediction", "libra")

        //When
        val predictionModel = horoscopeResponse.toDomain()

        //Then
        predictionModel.sign shouldBe  horoscopeResponse.sign
        predictionModel.horoscopeResult shouldBe horoscopeResponse.horoscope
    }
}