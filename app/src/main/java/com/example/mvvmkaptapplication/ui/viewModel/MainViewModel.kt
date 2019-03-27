package com.example.mvvmkaptapplication.ui.viewModel

import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {
    fun changeText(s: String) : String{
       return "hello world"
    }


}