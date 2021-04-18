/*
 * Copyright (c) 2021/  4/ 18.  Created by Hashim Tahir
 */

package com.hashim.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hashim.rxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var hActivityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)
    }
}