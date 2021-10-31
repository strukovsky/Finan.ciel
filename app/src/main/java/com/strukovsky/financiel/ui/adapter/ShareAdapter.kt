package com.strukovsky.financiel.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.strukovsky.financiel.R
import com.strukovsky.financiel.db.entity.Share

class ShareAdapter(private var data: List<Share>): RecyclerView.Adapter<ShareAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shareTicker: TextView = view.findViewById(R.id.share_ticker)
        val shareName: TextView = view.findViewById(R.id.share_name)
    }

    fun updateData(shares: Any)
    {
        data = shares as List<Share>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.share, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentShare = data[position]
        holder.shareName.text = currentShare.name
        holder.shareTicker.text = currentShare.ticker
        holder.itemView.setOnClickListener {
            Log.i("AAAAAA", holder.shareTicker.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}