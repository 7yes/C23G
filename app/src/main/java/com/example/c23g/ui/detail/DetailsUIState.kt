package com.example.c23g.ui.detail

sealed class DetailsUIState{
    data object Loading:DetailsUIState()
    data class Error(val error:String):DetailsUIState()
    data class Sucess(val prediction:String, val sing:String):DetailsUIState()
}
