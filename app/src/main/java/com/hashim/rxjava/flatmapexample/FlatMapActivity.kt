/*
 * Copyright (c) 2021/  4/ 22.  Created by Hashim Tahir
 */

package com.hashim.rxjava.flatmapexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashim.rxjava.databinding.ActivityFlatMapBinding
import com.hashim.rxjava.flatmapexample.response.PostResponseItem
import com.hashim.rxjava.flatmapexample.retrofit.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import kotlin.random.Random


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
            .subscribeOn(Schedulers.io())
            /*Data Retrieved from posts, flat the emission using flatmap in a
            single observable apply function to get comments too. and pass the retrieved post.*/
            .flatMap(Function {
                return@Function hGetCommentsObservable(it) // after this retrieved commets are mapped to the posts.
            })
            /*In case of any exception. Replace that emisstion with empty observable*/
            .onErrorResumeNext { Observable.empty() }
            /*At the end update the view on ui thread.*/
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hUpdateAdaptersData(it)

            }, {
                Timber.d("hMakeApiCall Exception ${it.message}")

            }, {
                Timber.d("hMakeApiCall Completed")
            })
    }

    private fun hUpdateAdaptersData(postResponseItem: PostResponseItem?) {
        Observable.fromIterable(hPostsAdapter.hGetPosts())
            /*For every item in the adapters list, match the id of the item with
            * passed item (THE ITEM WHOSE COMMENTS HAVE BEEN RETRIEVED) */
            .filter {
                postResponseItem?.id == it.id
            }
            .observeOn(AndroidSchedulers.mainThread())
            /*Then display the comments and hide progress bar of that item.*/
            .subscribe({
                hPostsAdapter.hUpdatePosts(it)
            }, {
                Timber.d("Excetion hUpdateAdaptersData ${it.message}")

            }, {
                Timber.d("Update completed")
            })
    }

    private fun hGetCommentsObservable(postResponseItem: PostResponseItem?): @NonNull Observable<PostResponseItem>? {
        return postResponseItem?.id?.let {
            /*This would run on background thread*/
            /*make comments api call*/
            RetrofitService.hApiService.hGetComments(
                it
            )
                /*Retrieved data is comments response, map it back to the
                    * posts after using the commentsSetter in PostResponseItem class, then
                    * return it back to the calling method.
                    *
                    * OBSERVE THE DATA RETRIEVED IS OF CommentsResponse TYPE BUT THE METHOD
                    * RETURN TYPE IS PostResponseItem TYPE.
                    * */
                .map {
                    /*Just randomly delay the thread so that each comments section is loaded
                    * in different time frame. just to make the progress bar a bit more visual */
                    val hDelay = (Random.nextInt(5) + 1) * 1000
                    Thread.sleep(hDelay.toLong())
                    postResponseItem.hCommentsResponse = it
                    return@map postResponseItem
                }
                .subscribeOn(Schedulers.io())
        }


    }

    private fun hMakeApiCall(): @NonNull Observable<PostResponseItem> {

        /*Make the api call*/
        return RetrofitService.hApiService.hGetPosts()
            /*Switch to backgroud thread*/
            .subscribeOn(Schedulers.io())
            /*When data retrieved post the response i.e. setting the data to the view on Ui thred*/
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { Observable.empty() }
            /*Then flaten the whole list and return it after setting the initial data
            * again switch to background thread*/
            .flatMap {
                hPostsAdapter.hSetPosts(it)
                return@flatMap Observable.fromIterable(it)
                    .subscribeOn(Schedulers.io())
            }
    }

    private fun hInitRecyclerView() {
        hPostsAdapter = PostsAdapter()
        hActivityFlatMapBinding.hRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FlatMapActivity)
            adapter = hPostsAdapter
        }

    }
}