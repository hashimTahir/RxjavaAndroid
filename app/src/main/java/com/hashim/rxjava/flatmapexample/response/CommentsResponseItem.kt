/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample.response


import com.google.gson.annotations.SerializedName

data class CommentsResponseItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
)