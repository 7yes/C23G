package com.example.c23g.domain.usecase

import com.example.c23g.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(sing: String) = repository.getPrediction(sing)
}