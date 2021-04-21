/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashim.rxjava.databinding.ActivityFlatMapBinding


/*Say we have a list of posts for a user and list of comments for each post of the user
*       Sample api used are
*   https://jsonplaceholder.typicode.com/posts/
*   https://jsonplaceholder.typicode.com/posts/1/comments
*
* */
class FlatMapActivity : AppCompatActivity() {
    lateinit var hActivityFlatMapBinding: ActivityFlatMapBinding
    lateinit var hPostsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityFlatMapBinding = ActivityFlatMapBinding.inflate(layoutInflater)
        setContentView(hActivityFlatMapBinding.root)

        hInitRecyclerView()

        /*Get the data from api.*/
        hMakeApiCall()
    }

    private fun hMakeApiCall() {
        TODO("Not yet implemented")
    }

    private fun hInitRecyclerView() {
        hPostsAdapter = PostsAdapter()
        hActivityFlatMapBinding.hRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FlatMapActivity)
            adapter = hPostsAdapter
        }

    }
}