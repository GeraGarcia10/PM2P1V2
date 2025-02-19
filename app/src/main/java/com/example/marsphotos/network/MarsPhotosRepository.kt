package com.example.marsphotos.network

import javax.inject.Inject

class MarsPhotosRepository @Inject constructor(private val marsApiService: MarsApiService) {

    suspend fun getPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotosPerro()
    }
}