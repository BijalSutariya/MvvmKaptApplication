package com.example.mvvmkaptapplication.data

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.annotation.NonNull
import android.support.annotation.WorkerThread
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class NetworkBoundResource<T, V> {
    private val result = MediatorLiveData<Resource<T>>()

    private fun fetchFromNetwork(dbSource: LiveData<T>) {

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData!!)
        }
        createCall().enqueue(object : Callback<V> {
            override fun onResponse(@NonNull call: Call<V>, @NonNull response: Response<V>) {
                result.removeSource(dbSource)
                saveResultAndReInit(response.body()!!)
            }

            override fun onFailure(@NonNull call: Call<V>, @NonNull t: Throwable) {
                result.removeSource(dbSource)
                result.addSource(dbSource) { newData -> result.setValue(Resource.error(t.message!!, newData)) }
            }
        })
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveResultAndReInit(response: V) {
        object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                saveCallResult(response)
            }

            override fun onPostExecute(avoid: Unit?) {
                result.addSource(loadFromDb()) { newData ->

                    if (null != newData) {
                        result.setValue(Resource.success(newData))
                        Log.d("logdata from network ", "" + newData)
                    }

                }
            }
        }.execute()
    }


    init {
        result.setValue(Resource.loading(null))
        val dbSource = loadFromDb()
        result.addSource(dbSource) { movieData ->
            result.removeSource(dbSource)
            if (shouldFetch(movieData!!)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                        result.setValue(Resource.success(newData!!))
                }
            }
        }
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: V)

    protected abstract fun shouldFetch(data: T): Boolean

    @NonNull
    @MainThread
    protected abstract fun loadFromDb(): LiveData<T>

    @NonNull
    @MainThread
    protected abstract fun createCall(): Call<V>

    fun getAsLiveData(): LiveData<Resource<T>> {
        return result
    }
}