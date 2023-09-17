package com.example.c23g.data.network.model

import com.example.c23g.data.network.motherobjet.HoroscopeMotherObject
import com.example.c23g.domain.model.toDomain
import io.kotlintest.shouldBe
import org.junit.Test

class PredictionResponseTest{
    @Test
    fun `toDomain should return a correct PredictionModel`(){
        //Given aca uestro 3 formas de hacer el antes
       // val horoscopeResponse = PredictionResponse("date","prediction", "libra") // mock
       // val horoscopeResponse = HoroscopeMotherObject.anyResponse  // mejor si se saca tod de un object que centralice lo q se necesite
        val horoscopeResponse = HoroscopeMotherObject.anyResponse.copy(sign = "Virgo") //usa un copy por si necesitas cambiar una entraga generica

        //When
        val predictionModel = horoscopeResponse.toDomain()

        //Then
        predictionModel.sign shouldBe  horoscopeResponse.sign
        predictionModel.horoscopeResult shouldBe horoscopeResponse.horoscope
    }
}