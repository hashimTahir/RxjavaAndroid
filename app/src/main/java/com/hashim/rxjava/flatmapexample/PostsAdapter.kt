/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.hashim.rxjava.databinding.ItemRecyclerFlatMapBinding
import com.hashim.rxjava.flatmapexample.response.PostResponse
import com.hashim.rxjava.flatmapexample.response.PostResponseItem


class PostsAdapter : RecyclerView.Adapter<PostsVh>() {
    private lateinit var hPosts: PostResponse

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): PostsVh {
        return PostsVh(
            ItemRecyclerFlatMapBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(@NonNull holder: PostsVh, position: Int) {
        holder.hBindVh(hPosts[position])
    }

    override fun getItemCount(): Int {
        return hPosts.size
    }

    fun hSetPosts(posts: PostResponse) {
        hPosts = posts
        notifyDataSetChanged()
    }

    fun hUpdatePosts(postResponseItem: PostResponseItem) {
        hPosts.set(hPosts.indexOf(postResponseItem), postResponseItem)
        notifyItemChanged(hPosts.indexOf(postResponseItem))
    }

    fun hGetPosts(): PostResponse {
        return hPosts
    }
}