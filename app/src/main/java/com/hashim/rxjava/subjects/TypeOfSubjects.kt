/*
 * Copyright (c) 2021/  4/ 25.  Created by Hashim Tahir
 */

package com.hashim.rxjava.subjects

import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import timber.log.Timber


/*
* PublishSubject emits all the items at the point of subscription. This is
* the most basic form of Subject.
* */

fun hPublishSubjectRunner() {
    val hPublishSubject = PublishSubject.create<Int>()
    hPublishSubject.onNext(0)


    /*
    * 1,2,3,4 are called for Observer 1
    * */
    hPublishSubject.subscribe(
        {
            Timber.d("Observer 1 onNext: $it")
        },
        { error: Throwable? -> },
        { Timber.d("Observer 1 onSubscribe") })

    hPublishSubject.onNext(1)
    hPublishSubject.onNext(2)


    /*
*  only 3,4 are called for Observer 2
* */
    hPublishSubject.subscribe(
        {
            Timber.d("Observer 2 onNext: $it")
        },
        { error: Throwable? -> },
        { Timber.d("Observer 2 onSubscribe") }
    )

    hPublishSubject.onNext(3)
    hPublishSubject.onNext(4)
}


/*
* BehaviorSubject emits the most recent item at the time of their subscription
*  and all items after that. 
*
*
* Difference between PublishSubject and BehaviorSubject is that PublishSubject
*  prints all values after subscription and BehaviorSubject prints the last
*  emitted value before subscription and all the values after subscription.
* */

fun hBehaviourSubjectRunner() {
    val hBehaviourSubject = BehaviorSubject.create<Int>()
    hBehaviourSubject.onNext(0)


    hBehaviourSubject.subscribe(
        {
            Timber.d("Observer 1 onNext: $it")
        },
        { error: Throwable? -> },
        { Timber.d("Observer 1 onSubscribe") })

    hBehaviourSubject.onNext(1)
    hBehaviourSubject.onNext(2)


    hBehaviourSubject.subscribe(
        {
            Timber.d("Observer 2 onNext: $it")
        },
        { error: Throwable? -> },
        { Timber.d("Observer 2 onSubscribe") })

    hBehaviourSubject.onNext(3)
    hBehaviourSubject.onNext(4)
}

/*
* ReplaySubject emits all the items of the Observable, regardless of when
*  the subscriber subscribes.
*
* */


fun hReplaySubjectRunner() {
    val hReplaySubject = ReplaySubject.create<Int>()
    hReplaySubject.onNext(0)


    hReplaySubject.subscribe(
        { Timber.d("Observer 1 onNext: $it") },
        { error: Throwable? -> },
        { Timber.d("Observer 1 onSubscribe") })

    hReplaySubject.onNext(1)
    hReplaySubject.onNext(2)


    hReplaySubject.subscribe(
        { Timber.d("Observer 2 onNext: $it") },
        { error: Throwable? -> },
        { Timber.d("Observer 2 onSubscribe") })

    hReplaySubject.onNext(3)
    hReplaySubject.onNext(4)
}


/*
* AsyncSubject emits only the last value of the Observable and this only happens
* after the Observable completes.
*
* */

fun hAsyncSubjectRunner() {
    val hAsyncSubject = AsyncSubject.create<Int>()
    hAsyncSubject.onNext(0)


    hAsyncSubject.subscribe(
        { Timber.d("Observer 1 onNext: $it") },
        { error: Throwable? -> },
        { Timber.d("Observer 1 onComplete") },
    )

    hAsyncSubject.onNext(1)
    hAsyncSubject.onNext(2)


    hAsyncSubject.subscribe(
        { Timber.d("Observer 2 onNext: $it") },
        { error: Throwable? -> },
        { Timber.d("Observer 2 onComplete") },
    )

    hAsyncSubject.onNext(3)
    hAsyncSubject.onNext(4)


    /* This is very important in AsyncSubject  */
    hAsyncSubject.onComplete()
}
