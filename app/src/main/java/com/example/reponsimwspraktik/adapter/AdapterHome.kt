package com.example.reponsimwspraktik.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.DetailKatalogActivity
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.data.DataHome
import com.example.reponsimwspraktik.data.DataMember
import com.squareup.picasso.Picasso

class AdapterHome(val context: Context, val homeList: ArrayList<DataHome>): RecyclerView.Adapter<AdapterHome.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = homeList[position]
        val getId = currentItem.id
        val getImage = currentItem.image

        // load image
        Picasso.get()
            .load(getImage)
            .into(holder.image)

        // on click listener
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailKatalogActivity::class.java)
            intent.putExtra("id", getId)
            intent.putExtra("image", getImage)
            holder.itemView.context.startActivity(intent)
        }

        // Hapus
        holder.btnHapus.setOnClickListener {
            homeList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imgProduct)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
    }
}