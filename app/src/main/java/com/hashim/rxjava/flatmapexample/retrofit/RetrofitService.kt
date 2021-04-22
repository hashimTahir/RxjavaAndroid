/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    const val H_BASE_URL = "https://jsonplaceholder.typicode.com"


    private val hRetrofitBuilder = Retrofit.Builder()
        .baseUrl(H_BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
    private val hRetrofit = hRetrofitBuilder.build()

    val hApiService: ApiService = hRetrofit.create(ApiService::class.java)
}