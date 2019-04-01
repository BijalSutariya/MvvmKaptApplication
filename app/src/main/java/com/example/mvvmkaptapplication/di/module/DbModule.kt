package com.example.mvvmkaptapplication.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.mvvmkaptapplication.data.local.AppDatabase
import com.example.mvvmkaptapplication.data.local.MovieDao
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.Nullable
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    internal fun provideDatabase(@Nullable application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "dataList.db"
        )
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }
}