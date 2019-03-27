package com.example.mvvmkaptapplication.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.mvvmkaptapplication.di.ViewKeyMap
import com.example.mvvmkaptapplication.factory.ViewModelFactory
import com.example.mvvmkaptapplication.ui.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewKeyMap(MainViewModel::class)
    abstract fun listViewModel(listViewModel: MainViewModel): ViewModel

}