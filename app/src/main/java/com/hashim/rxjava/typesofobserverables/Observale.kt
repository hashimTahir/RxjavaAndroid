/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun hGetObserveable(): @NonNull Observable<TestData>? {

    /*Declate an observable from datasource
    * Observable is Any Object whose state needs to be observed
    * Observable: emit a stream elements (endlessly)
    *
    *
    * Observable subscribe has three methods
    *
    * OnNext->Called on each emission
    * throwable->Called when some exceptoin is thrown.
    * OnComplete->When emission is completed.
    * */

    return Observable
        .fromIterable(DataSource.hGetData()) //create Observable from list
        .subscribeOn(Schedulers.io()) // switch tread to backgroud
        .observeOn(AndroidSchedulers.mainThread())
}