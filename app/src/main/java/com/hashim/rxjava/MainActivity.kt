/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hashim.rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var hActivityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)


        /*Declate an observable from datasource
        * Observable is Any Object whose state needs to be observed
        * Observer is what wishes to be notified when state of observable is changed
        * */
        val hTestDataObservable = Observable
            .fromIterable(DataSource.hGetData()) //create Observable from list
            .subscribeOn(Schedulers.io()) // switch tread to backgroud
            .observeOn(AndroidSchedulers.mainThread()) //observe the results on main thread

        /*First lambda is Onnext, for every value emitted it is called everytime
        * Second one is exception,
        * third one is onComplete, when all emissions complete, it is called*/
        hTestDataObservable.subscribe({
            Timber.d("Task Emitted ${it.hName}")
        }, {

            Timber.d("Exception throw ${it.message}")
        }, {
            Timber.d("Completed")

        })

    }

}