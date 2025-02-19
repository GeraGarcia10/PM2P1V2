package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    /*var marsUiState: String by mutableStateOf("")
        private set*/

    var marsUiState: MarsUiState by mutableStateOf(  MarsUiState.Loading)

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        //marsUiState = "Set the Mars API status response here!"

                viewModelScope.launch {

                    try {
                        val listResult = MarsApi.retrofitService.getPhotosPerro()
                    marsUiState =            MarsUiState.Success(
                        "Fotos recuperadas ${listResult.size}  "
                    )
                        listResult.forEach {
                            Log.d("ID foto: ${it.id}", "URL foto: ${it.imgSrc}")
                        }
                    }catch (e : IOException){
                       marsUiState = MarsUiState.Error
                    }

                }

    }
}
