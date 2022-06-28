package com.bikk.filmlibrary.di

import com.bikk.filmlibrary.util.Const
import com.bikk.filmlibrary.data.retrofit.ApiService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private val gson = Gson()
    private val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
            val builder = request.method(original.method, original.body)
                .build()
            chain.proceed(builder)
        }.addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()


    fun getService(): ApiService = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
        .create(ApiService::class.java)

}