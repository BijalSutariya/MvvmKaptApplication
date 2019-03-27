package com.example.mvvmkaptapplication.di.component

import android.app.Application
import com.example.mvvmkaptapplication.MyApplication
import com.example.mvvmkaptapplication.di.module.ActivityModule
import com.example.mvvmkaptapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        ViewModelModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class
    )
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
}