package com.example.music_app.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.music_app.core.BuildConfig
import com.example.music_app.data.remote.ApiService
import com.example.music_app.data.repoImp.TrackRepoImp
import com.example.music_app.db.TrackDatabase
import com.example.music_app.domain.repository.TrackRepo
import com.example.music_app.domain.usecase.GetTrackUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext // Trả về context của ứng dụng
    }



    @Provides
    @Singleton
    fun provideTrackRepository(apiService: ApiService): TrackRepo {
        return TrackRepoImp(apiService) // Thực thể của TrackRepositoryImpl

    }

    @Provides
    @Singleton
    fun provideTrackUseCase(trackRepo: TrackRepo): GetTrackUseCase {
        return GetTrackUseCase(trackRepo)
    }

    //database
    @Provides
    @Singleton
    fun provideTrackDatabase(app: Application): TrackDatabase {
        return Room.databaseBuilder(
            app,
            TrackDatabase::class.java,
            "track_database")
            .build()
    }


//network
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)

    }
}