/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    const val H_BASE_URL = "https://jsonplaceholder.typicode.com"

    private val hRetrofitBuilder = Retrofit.Builder()
        .baseUrl(H_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private val hRetrofit = hRetrofitBuilder.build()

    val hRetquestApi: ApiService = hRetrofit.create(ApiService::class.java)
}