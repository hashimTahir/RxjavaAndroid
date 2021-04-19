/*
 * Copyright (c) 2021/  4/ 19.  Created by Hashim Tahir
 */

package com.hashim.rxjava.createoperators

import com.hashim.rxjava.DataSource
import com.hashim.rxjava.TestData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable


fun hGetFromArrayObserveable(): @NonNull Observable<MutableList<TestData>>? {

    /*
    * Will emit the whole array at onnce
    *
    * According to Rxjava doc fromArray() only supports reference arrays.
    * But in kotlin arrayOf<Int>() returns Array object. If we put a kotlin
    *  Array object in fromArray() method, it creates array of Array object
    *  (in java, it will be Array[]). So after subscription Observable
    * returns that object of Array. But if we put items in fromArray() method,
    * it will create array of Int. So after subscription it returns Int.
    * If one wants to iterate over any mutable iterable, then should use Observable.fromIterable()
    * used earlier in "Observable.kt" file
    * */
    return Observable
        .fromArray(DataSource.hGetData()) //create Observable from list
        .subscribeOn(Schedulers.io()) // switch tread to backgroud
        .observeOn(AndroidSchedulers.mainThread())

}


fun hGetCallableObserveable(): @NonNull Observable<String>? {

    /*
    * Suitable for databse operations where result should be returned
    * */
    val hCallable = Callable<String> {
        return@Callable "test callable"
    }


    return Observable.fromCallable(hCallable)

}

/*LiveDataReactiveStreams can be used to convert Rx flowalbe to livedata
*
* use dependency
* android.arch.lifecycle:reactivestreams:version
* */

