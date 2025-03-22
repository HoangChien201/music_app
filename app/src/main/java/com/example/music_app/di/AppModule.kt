package com.example.music_app.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room
import com.example.music_app.core.BuildConfig
import com.example.music_app.data.remote.ApiService
import com.example.music_app.data.repoImp.AlbumRepoImp
import com.example.music_app.data.repoImp.ArtistRepoImp
import com.example.music_app.data.repoImp.TrackRepoImp
import com.example.music_app.db.AlbumDatabase
import com.example.music_app.db.ArtistDatabase
//import com.example.music_app.db.AlbumDatabase
//import com.example.music_app.db.ArtistDatabase
import com.example.music_app.db.TrackDatabase
import com.example.music_app.domain.repository.AlbumRepo
import com.example.music_app.domain.repository.ArtistRepo
import com.example.music_app.domain.repository.TrackRepo
import com.example.music_app.domain.usecase.CreateAlbumUseCase
import com.example.music_app.domain.usecase.CreateArtistUseCase
import com.example.music_app.domain.usecase.CreateTrackUseCase
import com.example.music_app.domain.usecase.GetAlbumUseCase
import com.example.music_app.domain.usecase.GetAlbumsUseCase
import com.example.music_app.domain.usecase.GetArtistUseCase
import com.example.music_app.domain.usecase.GetArtistsUseCase
import com.example.music_app.domain.usecase.GetTrackUseCase
import com.example.music_app.domain.usecase.GetTracksUseCase
import com.example.music_app.factory.TrackService
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext // Trả về context của ứng dụng
    }

    //repository
    @Provides
    @Singleton
    fun provideTrackRepository(apiService: ApiService,db:TrackDatabase): TrackRepo {
        return TrackRepoImp(apiService,db.trackDao()) // Thực thể của TrackRepositoryImpl

    }

    @Provides
    @Singleton
    fun provideAlbumRepository(apiService: ApiService,db:AlbumDatabase): AlbumRepo {
        return AlbumRepoImp(apiService,db.albumDao()) // Thực thể của TrackRepositoryImpl

    }


    @Provides
    @Singleton
    fun provideArtistRepository(apiService: ApiService,db:ArtistDatabase): ArtistRepo {
        return ArtistRepoImp(apiService,db.artistDao()) // Thực thể của TrackRepositoryImpl

    }


    //usecase
    @Provides
    @Singleton
    fun provideTrackUseCase(trackRepo: TrackRepo): GetTrackUseCase {
        return GetTrackUseCase(trackRepo)
    }

    @Provides
    @Singleton
    fun provideTracksUseCase(trackRepo: TrackRepo): GetTracksUseCase {
        return GetTracksUseCase(trackRepo)
    }

    @Provides
    @Singleton
    fun provideAlbumUseCase(albumRepo: AlbumRepo): GetAlbumUseCase {
        return GetAlbumUseCase(albumRepo)
    }

    @Provides
    @Singleton
    fun provideAlbumsUseCase(albumRepo: AlbumRepo): GetAlbumsUseCase {
        return GetAlbumsUseCase(albumRepo)
    }

    @Provides
    @Singleton
    fun provideArtistUseCase(artistRepo: ArtistRepo): GetArtistUseCase {
        return GetArtistUseCase(artistRepo)
    }

    @Provides
    @Singleton
    fun provideArtistsUseCase(artistRepo: ArtistRepo): GetArtistsUseCase {
        return GetArtistsUseCase(artistRepo)
    }

    @Provides
    @Singleton
    fun provideCreateTrackUseCase(trackRepo: TrackRepo): CreateTrackUseCase {
        return CreateTrackUseCase(trackRepo)
    }

    @Provides
    @Singleton
    fun provideCreateAlbumUseCase(albumRepo: AlbumRepo): CreateAlbumUseCase {
        return CreateAlbumUseCase(albumRepo)
    }

    @Provides
    @Singleton
    fun provideCreateArtistUseCase(artistRepo: ArtistRepo): CreateArtistUseCase {
        return CreateArtistUseCase(artistRepo)
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
    @Provides
    @Singleton
    fun provideAlbumDatabase(app: Application): AlbumDatabase {
        return Room.databaseBuilder(
            app,
            AlbumDatabase::class.java,
            "album_database")
            .build()
    }
    @Provides
    @Singleton
    fun provideArtistDatabase(app: Application): ArtistDatabase {
        return Room.databaseBuilder(
            app,
            ArtistDatabase::class.java,
            "artist_database")
            .build()
    }


    //network
    var gson=Gson()
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)  // Thời gian tối đa để kết nối đến server
        .readTimeout(30, TimeUnit.SECONDS)     // Thời gian tối đa để đọc dữ liệu từ server
        .writeTimeout(30, TimeUnit.SECONDS)    // Thời gian tối đa để gửi dữ liệu đến server
        .build()
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideTrackService(): TrackService {
        return TrackService()

    }
}