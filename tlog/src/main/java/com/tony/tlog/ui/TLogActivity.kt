package com.tony.tlog.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tony.tlog.R
import com.tony.tlog.databinding.ActivityTlogBinding

class TLogActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityTlogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tlog)
        initView()
    }

    private fun initView() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.title = "TLog"


    }
}