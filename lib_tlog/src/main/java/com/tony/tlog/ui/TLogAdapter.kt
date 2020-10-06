package com.tony.tlog.ui


import android.content.Context
import android.text.ClipboardManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tony.tlog.bean.TLogData
import com.tony.tlog.databinding.LayoutTlogItemBinding

class TLogAdapter(val mData: MutableList<TLogData>) : RecyclerView.Adapter<TLogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TLogHolder {
        val inflater = LayoutInflater.from(parent.context)
        val mBinding = LayoutTlogItemBinding.inflate(inflater, parent, false)
        return TLogHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: TLogHolder, position: Int) {
        holder.onBind(mData[position])
    }

    fun refreshData(data: MutableList<TLogData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}

class TLogHolder(val mBinding: LayoutTlogItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

    init {
        mBinding.content.setOnLongClickListener {
            val clipboardManager =
                mBinding.root.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.text = mBinding.data?.log
            Toast.makeText(mBinding.root.context, "log已复制到剪切板", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }

    fun onBind(item: TLogData) {
        mBinding.data = item
    }

}