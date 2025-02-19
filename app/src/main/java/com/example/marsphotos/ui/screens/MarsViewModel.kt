package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel @Inject constructor(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = marsPhotosRepository.getPhotos()
                marsUiState = MarsUiState.Success("Fotos recuperadas ${listResult.size}")
                listResult.forEach {
                    Log.d("ID foto: ${it.id}", "URL foto: ${it.imgSrc}")
                }
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
            }
        }
    }
}
