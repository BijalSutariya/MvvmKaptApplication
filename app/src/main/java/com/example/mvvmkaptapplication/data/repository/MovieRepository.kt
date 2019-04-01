package com.example.mvvmkaptapplication.data.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import com.example.mvvmkaptapplication.data.NetworkBoundResource
import com.example.mvvmkaptapplication.data.Resource
import com.example.mvvmkaptapplication.data.local.MovieDao
import com.example.mvvmkaptapplication.data.local.MovieEntity
import com.example.mvvmkaptapplication.data.remote.MovieApiService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(var movieDao: MovieDao, var movieApiService: MovieApiService) {

    fun LoadMovieList(): LiveData<Resource<List<MovieEntity>>> {

        return object : NetworkBoundResource<List<MovieEntity>, List<MovieEntity>>() {
            override fun saveCallResult(item: List<MovieEntity>) {
                movieDao.insertMovies(item)
            }

            @SuppressLint("CheckResult")
            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                return movieDao.getMoviesByPage()
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): Call<List<MovieEntity>> {
                return movieApiService.fetchMovies()
            }

        }.getAsLiveData()
    }
}