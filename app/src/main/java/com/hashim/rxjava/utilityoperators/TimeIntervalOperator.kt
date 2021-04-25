/*
 * Copyright (c) 2021/  4/ 25.  Created by Hashim Tahir
 */

package com.hashim.rxjava.utilityoperators

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Timed
import java.util.concurrent.TimeUnit


/*
* Time Interval operator converts an Observable that emits items into one
* that emits indications of the amount of time elapsed between those emissions.
*  i.e. if we are more interested in how much time has passed since the
* last item, rather than the absolute moment in time when the items were
* emitted,
* */


fun hGetTimeInervalObservable(): @NonNull Observable<Timed<Long>>? {
    return Observable.interval(100, TimeUnit.MILLISECONDS)
        .take(3)
        .timeInterval()
}