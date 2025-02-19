package com.example.marsphotos

import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMarsApiService(): MarsApiService {
        return MarsApi.retrofitService
    }

    @Provides
    @Singleton
    fun provideMarsPhotosRepository(apiService: MarsApiService): MarsPhotosRepository {
        return MarsPhotosRepository(apiService)
    }
}
