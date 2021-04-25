/*
 * Copyright (c) 2021/  4/ 24.  Created by Hashim Tahir
 */

package com.hashim.rxjava.utilityoperators

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber


/*
* This operator registers an action to take upon a variety of Observable lifecycle events.
*
*     1->  The doOnNext() operator modifies the Observable source so that it invokes an
*           action when the onNext() is called.
*     2->   The doOnCompleted() operator registers an action so that it invokes an action
*           when the onComplete() is called.
*     3->  The doOnEach() operator modifies the Observable source so that it notifies
*            an Observer for each item and establishes a callback that will be called each
*              time an item is emitted.
*     4-> The doOnSubscribe() operator registers an action which is called whenever an Observer
*          subscribes to the resulting Observable.
*
* */
fun hGetDoOnNextObservable(): @NonNull Observable<String>? {
    return Observable.just("A", "B", "C", "D", "E", "F")
        .doOnNext {
            Timber.d("Emitted String $it")
        }
}

fun hGetDoOnSubscribeUnsubscribeEachObservable(): @NonNull Observable<Int>? {
    return Observable.range(1, 5)
        .doOnEach(object : Observer<Int?> {
            override fun onError(e: Throwable) {
                Timber.d("Exception ${e.message}")
            }

            override fun onComplete() {
                println("Complete is called")
            }

            override fun onSubscribe(d: Disposable?) {
                println("onSubscribe is called")
            }


            override fun onNext(t: Int?) {
                Timber.d("Emitted $t")
            }
        })
        .doOnSubscribe { Timber.d("Subscribed") }
}


/*
* This operator represents both the items emitted and the notification sent as
* emitted items, or vice versa. What .materialize() does is basically wrap the
*  observed object types into an observable Notification object on which we can
*  check whether the onNext(), onError() and/or onComplete() methods are called.
*  dematerialize(), reverses the effect.
*
* */
fun hGetMaterializeObservable(): @NonNull Observable<Notification<String>>? {
    return Observable.just("A", "B", "C", "D", "E", "F")
        .materialize()
}