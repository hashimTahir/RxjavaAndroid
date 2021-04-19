/*
 * Copyright (c) 2021/  4/ 19.  Created by Hashim Tahir
 */

package com.hashim.rxjava.operators

import com.hashim.rxjava.DataSource
import com.hashim.rxjava.TestData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber


/* For Creating Observables
*
*    Create — create an Observable from scratch by calling observer methods programmatically
*    Defer — do not create the Observable until the observer subscribes, and create a fresh Observable for each observer
*    From — convert some other object or data structure into an Observable
*    Interval — create an Observable that emits a sequence of integers spaced by a particular time interval
*    Just — convert an object or a set of objects into an Observable that emits that or those objects
*    Range — create an Observable that emits a range of sequential integers
*    Repeat — create an Observable that emits a particular item or sequence of items repeatedly
*    Start — create an Observable that emits the return value of a function
*    Timer — create an Observable that emits a single item after a given delay
*
*
*
* Transforming Observables
* Operators that transform items that are emitted by an Observable.
*
* Buffer — periodically gather items from an Observable into bundles and emit these bundles rather than emitting the items one at a time
* FlatMap — transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
* Map — transform the items emitted by an Observable by applying a function to each item

* */

/*For Manually creating the observable from */
fun hCreateSingleTaskUsingCreateOperator(): @NonNull Observable<TestData>? {
    val hTask = DataSource.hGetData()[0]

    return Observable.create { emitter: ObservableEmitter<TestData>? ->
        if (!emitter?.isDisposed!!) {
            emitter.onNext(hTask)
            emitter.onComplete()
        }
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun hCreateListofTasksUsingCreateOperator(): @NonNull Observable<TestData>? {
    return Observable.create { emitter: ObservableEmitter<TestData>? ->
        for (task in DataSource.hGetData()) {
            if (!emitter?.isDisposed!!) {
                emitter.onNext(task)
            }
        }
        if (!emitter?.isDisposed!!) emitter.onComplete()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/*With JUST ONE CAN ONLY USE TEN ITEMS.*/
fun hGetObservableByJust(): @NonNull Observable<TestData>? {
    return Observable.just(
        DataSource.hGetData()[0],
        DataSource.hGetData()[1],
        DataSource.hGetData()[2],
        DataSource.hGetData()[3],
        DataSource.hGetData()[4],
        DataSource.hGetData()[0],
        DataSource.hGetData()[1],
        DataSource.hGetData()[2],
        DataSource.hGetData()[3],
        DataSource.hGetData()[4],
    )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/*Say when looping a range a doing expensive operations on each iteration
Map here is used to convert map the integers to tasks
* */
fun hGetObservablebyRangeUsingMap(): @NonNull Observable<TestData>? {
    return Observable.range(0, 9)
        .subscribeOn(Schedulers.io())
        .map {
            Timber.d("Thread is ${Thread.currentThread().name}")
            return@map TestData("Test task", hId = it, null)
        }
        .observeOn(AndroidSchedulers.mainThread())

}