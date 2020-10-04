package com.tony.tlog.bean

import androidx.annotation.Keep
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Transient

@Keep
@Entity
data class TLogData(
    @Id
    var id: Long = 0,
    var time: Long? = null,
    var tag: String? = null,
    var log: String? = null,
    var verbose: Long = 0,
    @Transient
    var displayTime: String? = null
)