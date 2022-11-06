package com.example.reponsimwspraktik.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.data.DataHome
import com.example.reponsimwspraktik.data.DataMember

class AdapterHome(val homeList: ArrayList<DataHome>): RecyclerView.Adapter<AdapterHome.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = homeList[position]

        holder.image.setImageResource(currentItem.image)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imgProduct)
    }
}