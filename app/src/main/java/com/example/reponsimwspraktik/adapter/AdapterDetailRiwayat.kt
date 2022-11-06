package com.example.reponsimwspraktik.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.data.DataDetail
import com.example.reponsimwspraktik.data.DataPromo

class AdapterDetailRiwayat(val context: Context, val riwayatList: ArrayList<DataDetail>): RecyclerView.Adapter<AdapterDetailRiwayat.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.card_detail_riwayat, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = riwayatList[position]

        holder.image.setImageResource(currentItem.image)
        holder.title.text = currentItem.title
        holder.jumlah.text = currentItem.jumlah
        holder.price.text = currentItem.price
        holder.total.text = currentItem.total
    }

    override fun getItemCount(): Int {
        return riwayatList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imgProduct)
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val jumlah: TextView = itemView.findViewById(R.id.txtJumlah)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
        val total: TextView = itemView.findViewById(R.id.txtTotal)
    }
}