package com.example.mvvmkaptapplication.ui.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.mvvmkaptapplication.data.Resource
import com.example.mvvmkaptapplication.data.local.MovieEntity
import com.example.mvvmkaptapplication.data.repository.MovieRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {

    private var moviesLiveData: LiveData<Resource<List<MovieEntity>>>
    init {
        moviesLiveData = repository.LoadMovieList()
    }

    fun getMovieList():LiveData<Resource<List<MovieEntity>>>{
        return moviesLiveData
    }
}