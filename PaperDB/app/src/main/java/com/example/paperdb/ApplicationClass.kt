package com.example.paperdb

import android.app.Application
import android.util.Log
import io.paperdb.Paper

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("ApplicationClass", "onCreate called")
        Paper.init(applicationContext)


    }

}