/*
 * Copyright (c) 2021/  4/ 19.  Created by Hashim Tahir
 */

package com.hashim.rxjava.transformoperators

import com.hashim.rxjava.DataSource
import com.hashim.rxjava.TestData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
* Map operator applies a function on each emitted item and transforms it
* into other. it maintains the order in which they were created.
* */

/*Convert TestData into a String*/
fun hGetMapObservable(): @NonNull Observable<String?>? {
    return Observable
        .fromIterable(DataSource.hGetData())
        .map { testData ->
            return@map testData.hName

        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


/*
* Buffer Operator
*
*  Periodically gather items from an Observable into bundles and emit the
* bundles rather than emitting items one at a time.
* Order is maintained
* */

fun hGetBufferObservable(): @NonNull Observable<MutableList<TestData>>? {
    /*buffer here means emit in groups of two.*/
    return Observable
        .fromIterable(DataSource.hGetData())
        .buffer(2)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}



/*
* Debounce Operator
*Say we have a seach view and user is typing, and the typed search event has to call
* an api, its a bad idea to make an api call after each text change.
* What would be ideal is to let the user keep typing. Wait for  say 500 or 1000 ms,
* after that make the api call with the typed text.
* one can use the debounce operator for this purpose.
*
* Example is in MainActivity.
* */