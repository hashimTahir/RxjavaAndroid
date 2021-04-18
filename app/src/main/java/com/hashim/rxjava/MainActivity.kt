/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hashim.rxjava.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var hActivityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)

        /*The object inside the subscribe method is observer
        * this is what recieves the values from observables
        * */

        hGetObserveable()?.subscribe({
            Timber.d("Observable Emitted ${it.hName}")
        }, {

            Timber.d("Observable throw ${it.message}")
        }, {
            Timber.d("Observable Completed")

        })

        hGetSingle()?.subscribe({
            Timber.d("Single Whole list emitted as Single $it")
        }, {
            Timber.d("Single exception ${it.message}")
        })

        hGetCompletable()?.subscribe({
            Timber.d("Completable On Success")
        }, {
            Timber.d("Completable Excetion ${it.message}")

        })

        hGetFlowable()?.subscribe({
            Timber.d("Flowable $it")
        }, {
            Timber.d("Flowable exception ${it.message}")
        }, {
            Timber.d("Flowable On Complete")
        })

    }

}