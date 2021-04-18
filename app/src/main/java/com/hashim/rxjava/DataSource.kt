/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

object DataSource {
    fun hGetData(): MutableList<TestData> {
        val hList = mutableListOf<TestData>()
        hList.add(
            TestData(
                hName = "abc",
                hId = 1,
                hTask = "High"
            )
        )
        hList.add(
            TestData(
                hName = "def",
                hId = 2,
                hTask = "Medium"
            )
        )
        hList.add(
            TestData(
                hName = "ghi",
                hId = 3,
                hTask = "Low"
            )
        )
        hList.add(
            TestData(
                hName = "jkl",
                hId = 4,
                hTask = "High"
            )
        )
        hList.add(
            TestData(
                hName = "mno",
                hId = 5,
                hTask = "Low"
            )
        )
        return hList
    }
}