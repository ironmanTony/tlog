package com.tony.tlog.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tony.tlog.TLog
import com.tony.tlog.bean.TLogData
import com.tony.tlog.bean.TLogData_
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class TlogViewModel : ViewModel() {

    val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH)

    val query = TLog.getBox().query()
        .contains(TLogData_.tag, "")
        .order(TLogData_.time)
        .build()

    val liveData = MutableLiveData<MutableList<TLogData>>()

    fun findLog(filter: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (filter.isNullOrEmpty()) {
                    liveData.postValue(
                        formatTime(
                            TLog.getBox().query().order(TLogData_.time).build().find()
                        )
                    )
                } else {
                    liveData.postValue(formatTime(query.setParameter(TLogData_.tag, filter).find()))
                }
            }
        }
    }

    fun formatTime(list: MutableList<TLogData>): MutableList<TLogData> {
        for (item in list) {
            item.time?.let {
                item.displayTime = formater.format(Date(it))
            }
        }
        return list
    }
}