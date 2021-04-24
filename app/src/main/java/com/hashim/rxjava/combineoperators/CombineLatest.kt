/*
 * Copyright (c) 2021/  4/ 24.  Created by Hashim Tahir
 */

package com.hashim.rxjava.combineoperators

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

/*
* This operator is used when an item is emitted by either of two Observables,
*  and the latest item emitted by each Observable is combined via a
* specified function and the resulting items are emitted based on the
*  results of this function.

Sample Implementation: The below code demonstrates the use of combineLatest() operator.
* Let’s say there are 2 Observables each emitting values after an interval of
* 100 ms and 150 ms respectively. The combineLatest() operator combines both the
*  observables and emits the result at each particular intervals.
* */


fun hGetCombineLatestObservable(): @NonNull Observable<String>? {
    val hList = listOf(1, 2, 3, 4)
    val hList1 = listOf(5, 6, 7, 8)
    val hObservable1: Observable<Int> = Observable.fromIterable(hList)
    val hObservable2: Observable<Int> = Observable.fromIterable(hList1)

    return Observable.combineLatest(
        hObservable1,
        hObservable2,
    ) { observable1Times, observable2Times ->
        "Refreshed Observable1: $observable1Times refreshed Observable2: $observable2Times"
    }
}