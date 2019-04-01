package com.example.mvvmkaptapplication.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.mvvmkaptapplication.R
import com.example.mvvmkaptapplication.data.Resource
import com.example.mvvmkaptapplication.data.local.MovieEntity
import com.example.mvvmkaptapplication.factory.ViewModelFactory
import com.example.mvvmkaptapplication.ui.viewModel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getMovieList()!!.observe(
            this,
            Observer<Resource<List<MovieEntity>>> { resources ->
                if (resources!!.isLoading){
                    Log.d("resource data",""+resources.data)
                }
                else if(resources.isSuccess){
                    Log.d("resource data",""+resources.data)
                }
            })
    }
}
