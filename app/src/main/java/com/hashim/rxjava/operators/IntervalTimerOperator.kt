/*
 * Copyright (c) 2021/  4/ 19.  Created by Hashim Tahir
 */

package com.hashim.rxjava.operators

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/*Say to keep track of time a common scenario in android, one use runnable in conjunction
* with handler
*
* In Rxjava the way to do this is by interval operator
* */

fun hGetIntervalObservable(): @NonNull Observable<Long>? {
    /*Define the period and timeUnit*/
    return Observable.interval(1, TimeUnit.SECONDS)
        /*Switch thread*/
        .subscribeOn(Schedulers.io())
        /*Terminate with take while
        * say we watn to termiate it after 5 seconds*/
        .takeWhile {
            return@takeWhile it <= 5
        }
        .observeOn(AndroidSchedulers.mainThread())
}

/*Timer observable emits a single observable after specified time period*/

fun hGetTimerObservable(): @NonNull Observable<Long>? {
    /*Define the period and timeUnit*/
    return Observable.timer(5, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
