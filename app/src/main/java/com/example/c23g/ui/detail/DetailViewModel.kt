package com.example.c23g.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c23g.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {
    private var _state = MutableStateFlow<DetailsUIState>(DetailsUIState.Loading)
    val state: StateFlow<DetailsUIState> = _state

    fun getHoroscope(sing: String) {
        viewModelScope.launch {
            _state.value = DetailsUIState.Loading
            //hilo main
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sing) } //Hilo secundario
            //hilo main
            if (result != null) _state.value = DetailsUIState.Sucess(result.horoscopeResult, result.sign)
            else _state.value = DetailsUIState.Error("Error t") }
    }
}