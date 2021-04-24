/*
 * Copyright (c) 2021/  4/ 24.  Created by Hashim Tahir
 */

package com.hashim.rxjava.combineoperators

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


/*
* This operator combines the output of two or more Observables into a single
* Observable, without interleaving them i.e. the first Observables completes its
* emission before the second starts and so forth if there are more observables.
*
*
*  The difference between merge() and concat() is that merge() interweaves
* output while concat() waits for earlier emissions to complete before
* processing new emissions.
* */

fun hGetConcatObservable(): @NonNull Observable<String>? {
    val hObservable1: Observable<String> = Observable
        .interval(1, TimeUnit.SECONDS).map { id -> "A$id" }

    val hObservable2: Observable<String> = Observable
        .interval(1, TimeUnit.SECONDS).map { id -> "B$id" }

    return Observable.concat(hObservable1, hObservable2)

}