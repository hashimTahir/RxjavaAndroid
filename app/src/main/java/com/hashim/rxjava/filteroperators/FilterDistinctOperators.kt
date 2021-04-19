/*
 * Copyright (c) 2021/  4/ 19.  Created by Hashim Tahir
 */

package com.hashim.rxjava.filteroperators

import com.hashim.rxjava.DataSource
import com.hashim.rxjava.TestData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/*Whateevet the filter condition is, only that task qualifying the
* condion will make through*/
fun hGetFilterObservable(): @NonNull Observable<TestData>? {
    return Observable
        .fromIterable(DataSource.hGetData())
        .filter { testData ->
            if (testData.hTask.equals("Low")) {
                return@filter true
            }
            return@filter false
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}



/*Returns on unique testdota downstream.
*
*
* other options are takeOperator e.g. take(3)
* will only emit 3 testdata
*
* takeWhile() speify the condition on which stream should terminate.
* */
fun hGetDistinctObservable(): @NonNull Observable<TestData>? {
    return Observable
        .fromIterable(DataSource.hGetData())
        .distinct {
            return@distinct it.hTask
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}