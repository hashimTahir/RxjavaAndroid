/*
 * Copyright (c) 2021/  4/ 25.  Created by Hashim Tahir
 */

package com.hashim.rxjava.utilityoperators

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Timed
import java.util.concurrent.TimeUnit


/*
* TimeStamp operator attach a timestamp to each item emitted by an Observable.
* It transforms the items into the Timestamped<T> type, which contains the
* original items, along with a timestamp for when the event was emitted.
*
* */

fun hGetTimeStampObservable(): @NonNull Observable<Timed<Long>>? {
    return Observable.interval(100, TimeUnit.MILLISECONDS)
        .take(3)
        .timestamp()
}