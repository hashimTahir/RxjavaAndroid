/*
 * Copyright (c) 2021/  4/ 19.  Created by Hashim Tahir
 */

package com.hashim.rxjava.transformoperators

import com.hashim.rxjava.DataSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
*  The ThrottleFirst() operator filters out items emitted by the source Observable
* that are within a timespan.
* say the time span is 500, and an event is emitted before that. then throttlefirst
* wont let it pass downstream.
*
* Same Example from Searchview in mainActivty can used. jst replace
* debounce with throttleFirst
*
* The main difference between throttling and debouncing is that throttling
* executes the function at a regular interval, while debouncing executes the function
*  only after some cooling period.
* */





/*
*  Flat map Transform the item(s) emitted by an Observable into Observables,
* then flatten the emissions from those into a single Observable.
Order is not maintained
* */
fun hGetFlatMapObservable(): @NonNull Observable<Int?>? {
    /*From Iterable gets data from api and for each item of the list
    * another api is to be made*/

    /*Mapping and changing the data type here simulates the different data types returned by
    * the api*/
    return Observable
        .fromArray(DataSource.hGetData())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap {
            /*Retrieved the data set it to a view*/
            /*flat the whole list*/
            return@flatMap Observable.fromIterable(it.toList())
                .subscribeOn(Schedulers.io())

        }
        .flatMap { testData ->
            /*For each item make a new api call which returns strings.*/
            return@flatMap Observable.just(testData.hTask)
        }
        .map {
            return@map it?.length
        }
        .observeOn(AndroidSchedulers.mainThread())

}


/*
* ConcatMap
* Its Same as Flat map but order is maintained
* */


/*
*
* SwitchMap() will transform items emitted by an Observable into an Observable just
* like ConcatMap() and FlatMap(). The difference being that it will unsubscribe
* previous observers once a new Observer has subscribed.
* Essentially this solves a limitation that both ConcatMap() and FlatMap() have.
*
* */