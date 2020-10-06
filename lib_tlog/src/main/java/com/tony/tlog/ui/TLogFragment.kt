package com.tony.tlog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tony.tlog.TLog
import com.tony.tlog.databinding.FragmentTlogBinding
import com.tony.tlog.utils.SoftKeyBoardListener
import com.tony.tlog.utils.SpUtils

class TLogFragment : Fragment() {

    lateinit var mBinding: FragmentTlogBinding
    lateinit var mViewModel: TlogViewModel
    val mAdapter = TLogAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTlogBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this).get(TlogViewModel::class.java)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initKeyBoard()
    }

    private fun initKeyBoard() {
        SoftKeyBoardListener.setListener(
            requireActivity(),
            object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
                override fun keyBoardShow(height: Int) {
                    mAdapter.notifyDataSetChanged()
                }

                override fun keyBoardHide(height: Int) {
                    mAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun initView() {
        mBinding.btnClear.setOnClickListener {
            TLog.clear()
            mViewModel.findLog(null)
        }
        mBinding.etFilter.addTextChangedListener {
            mViewModel.findLog(it?.toString())
        }
        mBinding.recycler.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = mAdapter
        }
        mViewModel.liveData.observe(viewLifecycleOwner, Observer {
            mAdapter.refreshData(it)
        })
        val preFilter = SpUtils.getString("log_filter", "")
        if (!preFilter?.trim().isNullOrEmpty()) {
            mBinding.etFilter.setText(preFilter)
        } else {
            mBinding.btnClear.requestFocus()
        }
        mViewModel.findLog(preFilter)
    }

    override fun onResume() {
        super.onResume()
        SpUtils.putString("log_filter", mBinding.etFilter.text.toString())
    }
}