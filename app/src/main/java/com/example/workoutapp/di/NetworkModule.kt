package com.example.workoutapp.di

import com.example.workoutapp.data.local.WorkoutDatabase
import com.example.workoutapp.data.remote.WorkoutApi
import com.example.workoutapp.data.repository.RemoteDataSourceImpl
import com.example.workoutapp.domain.repository.RemoteDataSource
import com.example.workoutapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType = contentType))
            .build()
    }


    @Provides
    @Singleton
    fun provideWorkoutApi(retrofit: Retrofit): WorkoutApi {
        return retrofit.create(WorkoutApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(
        WorkoutApi: WorkoutApi,
        WorkoutDatabase: WorkoutDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl (
            workoutApi = WorkoutApi,
            workoutDatabase = WorkoutDatabase
        )
    }
}