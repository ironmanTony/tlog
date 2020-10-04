package com.tony.tlog

import android.content.Context
import android.util.Log
import com.tony.tlog.bean.TLogData
import com.tony.tlog.db.ObjectBox
import io.objectbox.Box
import java.lang.RuntimeException

object TLog {

    val config = TConfig()
    var logBox: Box<TLogData>? = null
    var mAppContext: Context? = null

    fun initTLog(context: Context) {
        val appContext = context.applicationContext
        mAppContext = appContext
        ObjectBox.init(appContext)
        logBox = ObjectBox.boxStore.boxFor(TLogData::class.java)
    }

    fun log(tag: String, msg: String?) {
        if (!config.openLog) {
            return
        }
        checkBoxStore()
        logBox!!.put(
            TLogData(
                time = System.currentTimeMillis(),
                tag = tag,
                log = msg
            )
        )
        Log.d(tag, msg ?: "null")
    }

    fun clear() {
        checkBoxStore()
        logBox!!.removeAll()
    }

    fun getBox(): Box<TLogData> {
        checkBoxStore()
        return logBox!!
    }

    private fun checkBoxStore() {
        if (logBox == null) {
            throw RuntimeException("please init log first")
        }
    }

}