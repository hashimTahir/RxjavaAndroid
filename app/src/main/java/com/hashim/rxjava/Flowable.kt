/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers


/*
* emits a stream of elements
*
* The property that distinguishes one from the other is backpressure.
*
*   Observables are not backpressure-aware
*   Flowables are backpressure-aware
*
*   Back pressure
*
*   Backpressure is when in a Flowable processing pipeline can't process the
*   values fast enough and need a way to tell the upstream producer to slow down.
*
*   if there is too much data emitting to the Observers and the Observers can't
*   handle it. In situations like that one might see an Out of Memory exception.
*    The device literally cannot handle the incoming data fast enough.
*
*   Different Backpressure Strategies
*
*   BackpressureStrategy.BUFFER
*
*   BackpressureStrategy.DROP
*
*   BackpressureStrategy.ERROR
*
*   BackpressureStrategy.LATEST
*
* BackpressureStrategy.MISSING
*
*
* Flowables can be converted to Observables and Observables can be converted to Flowables.
* with toFlowable an toObservable
* */
fun hGetFlowable() {

    /*Even with one million emissions, this wont cause out of memory exception*/
    Flowable.range(0, 1000000)
        .onBackpressureBuffer()
        .observeOn(Schedulers.computation())

}