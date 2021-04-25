/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.hashim.rxjava.databinding.ActivityMainBinding
import com.hashim.rxjava.flatmapexample.FlatMapActivity
import com.hashim.rxjava.utilityoperators.hGetDoOnNextObservable
import com.hashim.rxjava.utilityoperators.hGetDoOnSubscribeUnsubscribeEachObservable
import com.hashim.rxjava.utilityoperators.hGetMaterializeObservable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var hActivityMainBinding: ActivityMainBinding
    private val hCompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)

        /*The object inside the subscribe method is observer
        * this is what recieves the values from observables
        *
        * When Observers are no longer required, they should be disposed of
        * For this, CompositeDisposable is used
        * */


        /*Example of debounce operator*/
        Observable.create<String> { string ->
            hActivityMainBinding.hSeachV.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!string.isDisposed) {
                        string.onNext(newText)
                    }
                    return false
                }

            })

        }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                Timber.d("Make an api call $it")
            }

        hGetDoOnNextObservable()?.subscribe(
            {
                Timber.d("hGetDoOnNextObservable ${it}")
            },
            { Timber.d("hGetDoOnNextObservable ${it.message}") },
            { Timber.d("hGetDoOnNextObservable Completed") }
        )

        hGetDoOnSubscribeUnsubscribeEachObservable()?.subscribe(
            {
                Timber.d("hGetDoOnSubscribeUnsubscribeEachObservable ${it}")
            },
            { Timber.d("hGetDoOnSubscribeUnsubscribeEachObservable ${it.message}") },
            { Timber.d("hGetDoOnSubscribeUnsubscribeEachObservable Completed") }
        )


        hGetMaterializeObservable()?.subscribe(
            /*
                         * From the notification object, we can check if the
                         * emitted item is:
                         * isOnNext() or isOnError() or isOnComplete()
                         *
                         * Here we can basically fetch items that are successful
                         * & omit items that resulted in error.
                         *
                         *  */
            {
                Timber.d("Materialize Notification value ${it.value}")
            }, {
                Timber.d("Materialize exception ${it.message}")

            }, {
                Timber.d("Materialize Completed")

            }
        )



        hActivityMainBinding.hFlatMapExampleB.setOnClickListener {
            startActivity(Intent(this, FlatMapActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /*Clear cleans all the Subscribers of the observers without disabling the observalbe
        * they can be subscibed to again if one wants to
        *
        * Dispose will do a hard clean. and observables can no longer be subscribed to.
        * the CompositeDisposible container will no longer be available.
        * */
        hCompositeDisposable.clear()
        hCompositeDisposable.dispose()

    }
}