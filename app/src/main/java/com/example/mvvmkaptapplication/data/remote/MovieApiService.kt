package com.example.mvvmkaptapplication.data.remote

import com.example.mvvmkaptapplication.data.local.MovieEntity
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("/todos")
    fun fetchMovies(): Call<List<MovieEntity>>

    @GET("/todos/{id}")
    fun fetchDetails(@Path("id") userId: Int): Observable<MovieEntity>
}