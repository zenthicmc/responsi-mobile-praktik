package com.example.reponsimwspraktik.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.data.DataPromo

class AdapterPromo(val context: Context, val promoList: ArrayList<DataPromo>): RecyclerView.Adapter<AdapterPromo.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_promo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = promoList[position]

        holder.image.setImageResource(currentItem.image)
        holder.title.text = currentItem.title
        holder.category.text = currentItem.category
        holder.price.text = currentItem.price
        holder.discount.text = currentItem.discount
        holder.discount.setPaintFlags(holder.discount.getPaintFlags() or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG)
    }

    override fun getItemCount(): Int {
        return promoList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imgProduct)
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val category: TextView = itemView.findViewById(R.id.txtCategory)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
        val discount: TextView = itemView.findViewById(R.id.txtDiscount)
    }
}