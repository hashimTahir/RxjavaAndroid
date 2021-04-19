/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hashim.rxjava.databinding.ActivityMainBinding
import com.hashim.rxjava.operators.hGetFromArrayObserveable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

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

        hGetFromArrayObserveable()?.subscribe(
            {
                Timber.d("hGetTimerObservable ${it}")
            },
            { Timber.d("Exception ${it.message}") },
            { Timber.d("hGetTimerObservable Completed") }
        )

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