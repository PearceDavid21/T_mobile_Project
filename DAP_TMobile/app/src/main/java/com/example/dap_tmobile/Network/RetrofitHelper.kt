package com.example.dap_tmobile.Network

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

const val cacheStorageSize : Long = 5 * 1024 * 1024
object RetrofitHelper {

    private fun getCustomOkHttpClient(cacheFile : File) : OkHttpClient {
        val apiCache = Cache(cacheFile, cacheStorageSize)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(apiCache)
            .build()
    }

    fun getRetrofitInstance(baseUrl : String, cacheFile: File) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getCustomOkHttpClient(cacheFile))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}