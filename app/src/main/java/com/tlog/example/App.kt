package com.tlog.example

import android.app.Application
import com.tony.tlog.TLog

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        TLog.config.openLog = BuildConfig.DEBUG
        TLog.initTLog(this)
    }
}