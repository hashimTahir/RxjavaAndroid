/*
 * Copyright (c) 2021/  4/ 24.  Created by Hashim Tahir
 */

package com.hashim.rxjava.combineoperators

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


/*
* This operator combines multiple Observables into one by merging their emissions
* i.e. merges multiple Observables into a single Observable but it won’t maintain the
*  sequential execution. merge() operator doesn’t wait for data from observable 1 to complete.
*  It emits data from both the observable simultaneously as soon as the data becomes available to emit.
*
* */
fun hGetMergeObservable(): @NonNull Observable<String>? {
    val hObservable1: Observable<String> = Observable
        .interval(1, TimeUnit.SECONDS).map { id -> "A$id" }

    val hObservable2: Observable<String> = Observable
        .interval(4, TimeUnit.SECONDS).map { id -> "B$id" }

    return Observable.merge(hObservable1, hObservable2)


}