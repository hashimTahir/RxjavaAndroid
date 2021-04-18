/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single


/*
* emits exactly one element
* Subscribe has 2 Methods
* onSuccess->When emisson is successfully recievied
* OnError-> When Exception is throw
*
*
* */
fun hGetSingle(): @NonNull Single<MutableList<TestData>>? {
    var hTestDataSingle = Single.just(DataSource.hGetData())

    return hTestDataSingle

}