/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample.response


import com.google.gson.annotations.SerializedName

data class PostResponseItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int,
    var hCommentsResponse: CommentsResponse? = null


)