package com.example.mvvmkaptapplication.data

import com.example.mvvmkaptapplication.data.Status.ERROR
import com.example.mvvmkaptapplication.data.Status.LOADING
import com.example.mvvmkaptapplication.data.Status.SUCCESS

class Resource<T> private constructor(val currentState: Status, val data: T?, val message: String?) {

    val isSuccess: Boolean
        get() = currentState === SUCCESS && data != null

    val isLoading: Boolean
        get() = currentState === LOADING

    val isLoaded: Boolean
        get() = currentState !== LOADING

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}