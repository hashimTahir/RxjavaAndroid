/*
 * Copyright (c) 2021/  4/ 23.  Created by Hashim Tahir
 */

package com.hashim.rxjava.zipoperator

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

/*
* Zip operator is used to combine emission from multiple observable into a single observable.
*
* */



fun hGetSequentialZipObservable(): @NonNull Observable<String>? {


    /*Say we have a result comming from two or more souces
    * and we want to combine the results, perform some calculations on it.*/


    /*
    * zip() operator is misunderstood when it comes to parallelism.
    * when we have multiple http request and combine the result in one,
    * it actually happens Sequentially instead of Parallel.
    * */


    /*Thread name would always be the same and are running sequentially*/

    return Observable.zip(
        Observable.fromCallable {
            for (i in 1..3) {
                Thread.sleep(200)
                Timber.d("Observer 1 Thread ${Thread.currentThread().name}")
            }
            return@fromCallable "Observalbe 1 complete"

        },
        Observable.fromCallable {
            for (i in 1..3) {
                Thread.sleep(200)
                Timber.d("Observer 2 Thread ${Thread.currentThread().name}")
            }
            return@fromCallable "Observalbe 2 complete"
        }
            .subscribeOn(Schedulers.io()),

        ) { a, b ->
        "$a , $b"
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}