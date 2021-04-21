/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample.retrofit

import com.hashim.rxjava.flatmapexample.response.CommentsResponse
import com.hashim.rxjava.flatmapexample.response.PostResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("posts")
    fun hGetPosts(): Observable<PostResponse>?

    @GET("posts/{id}/comments")
    fun hGetComments(
        @Path("id") id: Int
    ): Observable<CommentsResponse>?
}