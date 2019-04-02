package com.example.mvvmkaptapplication.ui.activity

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmkaptapplication.R
import com.example.mvvmkaptapplication.data.Resource
import com.example.mvvmkaptapplication.data.local.MovieEntity
import com.example.mvvmkaptapplication.databinding.ItemViewBinding
import com.example.mvvmkaptapplication.databinding.MainActivityBinding
import com.example.mvvmkaptapplication.factory.ViewModelFactory
import com.example.mvvmkaptapplication.ui.viewModel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainActivityBinding: MainActivityBinding
    lateinit var listData:List<MovieEntity>
    private var dataList:MutableList<MovieEntity> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getMovieList().observe(
            this,
            Observer<Resource<List<MovieEntity>>> { resources ->
                if (resources!!.isLoading) {
                    Log.d("resource data", "" + resources.currentState)
                } else if (resources.isSuccess) {
                    dataList.addAll(resources.data!!)

                    Log.d("resource data", "" + dataList)
                }
            })
    }
}
