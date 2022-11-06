package com.example.reponsimwspraktik.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.DetailRiwayatActivity
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.data.DataActivate

class AdapterRiwayatActivate(val context: Context, val activateList: ArrayList<DataActivate>): RecyclerView.Adapter<AdapterRiwayatActivate.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.card_riwayat_activate, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = activateList[position]

        holder.status.text = currentItem.status
        if (currentItem.status == "Selesai") {
            holder.status.setBackgroundResource(R.drawable.badge_green)
        } else {
            holder.status.setBackgroundResource(R.drawable.badge_red)
        }
        holder.invoice.text = currentItem.invoice
        holder.total.text = currentItem.total
        holder.date.text = currentItem.date

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailRiwayatActivity::class.java)
            intent.putExtra("status", currentItem.status)
            intent.putExtra("invoice", currentItem.invoice)
            intent.putExtra("date", currentItem.date)
            startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return activateList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val status: TextView = itemView.findViewById(R.id.txtStatus)
        val invoice: TextView = itemView.findViewById(R.id.txtInvoice)
        val total: TextView = itemView.findViewById(R.id.txtTotal)
        val date: TextView = itemView.findViewById(R.id.txtDate)
    }
}