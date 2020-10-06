package com.tony.tlog.utils

import android.content.Context
import android.content.SharedPreferences
import com.tony.tlog.TLog

object SpUtils {

    fun getString(key: String, def: String?): String? {
        return getSp().getString(key, def)
    }

    fun getSp(): SharedPreferences {
        return TLog.mAppContext!!.getSharedPreferences("my_pub", Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String?) {
        getSp().edit().putString(key, value).apply()
    }
}