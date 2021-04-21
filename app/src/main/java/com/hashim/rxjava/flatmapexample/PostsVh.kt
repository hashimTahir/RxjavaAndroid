/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hashim.rxjava.databinding.ItemRecyclerFlatMapBinding
import com.hashim.rxjava.flatmapexample.response.PostResponseItem


class PostsVh(
    val hItemRecyclerFlatMapBinding: ItemRecyclerFlatMapBinding,
) : RecyclerView.ViewHolder(hItemRecyclerFlatMapBinding.root) {

    fun hBindVh(postResponseItem: PostResponseItem) {
        hItemRecyclerFlatMapBinding.hTilteTv.text = postResponseItem.title
        if (postResponseItem.hCommentsResponse == null) {
            hShowProgressBar(true)
            hItemRecyclerFlatMapBinding.hCommentsTv.text = ""
        } else {
            hShowProgressBar(false)
            hItemRecyclerFlatMapBinding.hCommentsTv.text =
                postResponseItem.hCommentsResponse.size.toString()
        }
    }

    private fun hShowProgressBar(showProgressBar: Boolean) {
        if (showProgressBar) {
            hItemRecyclerFlatMapBinding.hPrograssBar.visibility = View.VISIBLE
        } else {
            hItemRecyclerFlatMapBinding.hPrograssBar.visibility = View.GONE
        }
    }

}
