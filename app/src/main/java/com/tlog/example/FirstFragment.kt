package com.tlog.example

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tlog.example.databinding.FragmentFirstBinding
import com.tony.tlog.TLog
import com.tony.tlog.ui.TLogActivity

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var mBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        initView()

    }

    private fun initView() {
        mBinding.btnGoTlogPage.setOnClickListener { goTLogActivity() }
        mBinding.btnAddLog.setOnClickListener { addLogs() }
    }

    private fun addLogs() {
        repeat(1000) {
            TLog.log("test", "add log:$it")
        }
    }

    private fun goTLogActivity() {
        startActivity(Intent(requireContext(), TLogActivity::class.java))
    }
}