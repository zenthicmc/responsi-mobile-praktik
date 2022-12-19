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
import com.squareup.picasso.Picasso

class AdapterPromo(val context: Context, val promoList: ArrayList<DataPromo>): RecyclerView.Adapter<AdapterPromo.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_promo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = promoList[position]

        // load image
        Picasso.get()
            .load(currentItem.image)
            .into(holder.image)

        holder.title.text = currentItem.title
        holder.category.text = currentItem.category
        holder.price.text = "Rp " + currentItem.price.toString()
        holder.discount.text = "Rp " + currentItem.discount.toString()
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