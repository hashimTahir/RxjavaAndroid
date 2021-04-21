/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hashim.rxjava.databinding.ActivityFlatMapBinding


/*Say we have a list of posts for a user and list of comments for each post of the user
*       Sample api used are
*   https://jsonplaceholder.typicode.com/posts/
*   https://jsonplaceholder.typicode.com/posts/1/comments
*
* */
class FlatMapActivity : AppCompatActivity() {
    lateinit var hActivityFlatMapBinding: ActivityFlatMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityFlatMapBinding = ActivityFlatMapBinding.inflate(layoutInflater)
        setContentView(hActivityFlatMapBinding.root)

        hInitRecyclerView()
    }

    private fun hInitRecyclerView() {


    }
}