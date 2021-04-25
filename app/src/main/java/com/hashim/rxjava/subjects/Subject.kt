/*
 * Copyright (c) 2021/  4/ 25.  Created by Hashim Tahir
 */

package com.hashim.rxjava.subjects

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import timber.log.Timber


/*
* A Subject extends an Observable and implements Observer at the same time.
*  It acts as an Observable to clients and registers to multiple events taking place
*  in the app. It acts as an Observer by broadcasting the event to multiple subscribers.
*
*
* Subjects are considered hot observables which means they emit items only once
* regardless of number of subscribers and its subscribers receive items only from
* the point of their subscription. Subjects convert cold observable into hot observable.
* */


fun hRunSimpleSubjectExample() {
    val hObservable: Observable<Int> = Observable.range(1, 5)
        .subscribeOn(Schedulers.io())
    /*
    * Here subject is acting as an observer
    * */
    val hSubject = ReplaySubject.create<Int>()
    hObservable.subscribe(hSubject)

    /*
     *
      * Here subject acts an an Observable,
      * since it emits each item from the original Observable.
      *
      * Multipe observers can subscribe to a subject.
      */
    hSubject.subscribe({
        Timber.d("Subject1 $it")

    }, {}, {})
    hSubject.subscribe({
        Timber.d("Subject2 $it")

    }, {}, {})
}

/*
* Subjects can multicast items to multiple child subscribers. Multicasting makes it
* possible to run expensive operations once and emit the results to multiple subscribers.
*  This prevents doing duplicate operations for multiple subscribers.
*
* */

fun hRunMultiCastObservable() {
    /*
    * Subscribing to it multiple times would run all operations for each of the observable.
    * */

    val hObservable = Observable.range(1, 5)
        .subscribeOn(Schedulers.io())
        /*Assuming this is the heavy operation*/
        .map { integer ->
            Timber.d("Squaring itself $integer")
            integer * integer
        }
    hObservable.subscribe {
        Timber.d("Observalbe1 $it")

    }
    hObservable.subscribe {
        Timber.d("Observalbe2 $it")
    }

}


/*Lets run the above example with subject*/
fun hRunMulticastSubject() {
    val hObservable = Observable.range(1, 5)
        .subscribeOn(Schedulers.io())
        .map { integer: Int ->
            Timber.d("Squaring itself $integer")
            integer * integer
        }
    /*
    * Create subjects and make it subscrbe to the observable.
    * */
    val hSubject = ReplaySubject.create<Int>()
    hObservable.subscribe(hSubject)


    /*Now do multiple subscriptions to the the subject
    *
    * Now only the emitted values are recieved by both subscribers
    * and the heavy operation only running once for both of the subscibers.
    * */
    hSubject.subscribe {
        Timber.d("Subscriber1 $it")
    }
    hSubject.subscribe {
        Timber.d("Subscriber2 $it")
    }
}