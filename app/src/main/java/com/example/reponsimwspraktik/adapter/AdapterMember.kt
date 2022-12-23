package com.example.reponsimwspraktik.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.SessionManager
import com.example.reponsimwspraktik.data.DataMember
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat

@SuppressLint("RecyclerView")

class AdapterMember(val context: Context, val memberList: ArrayList<DataMember>): RecyclerView.Adapter<AdapterMember.MyViewHolder>() {
    private lateinit var sessionManager : SessionManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_member, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        sessionManager = SessionManager(context)
        val currentItem = memberList[position]
        val decimalFormat = DecimalFormat("#,###")

        // load image
        Picasso.get()
            .load(currentItem.image)
            .into(holder.image)

        holder.title.text = currentItem.title
        // use decimal format
        holder.price.text = "Rp " + decimalFormat.format(currentItem.price).toString()
        holder.discount.text = "Rp " + decimalFormat.format(currentItem.discount).toString()
        holder.discount.setPaintFlags(holder.discount.getPaintFlags() or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG)
        holder.total.text = "Rp " + decimalFormat.format(currentItem.total).toString()

        holder.btnDelete.setOnClickListener {
            deleteItem(position)
        }

        holder.btnMinus.setOnClickListener {
            if(holder.txtCount.text.toString().toInt() > 1) {
                val currentCount = (holder.txtCount.text.toString().toInt() - 1).toString()
                val total = currentItem.total * currentCount.toInt()

                holder.total.text = "Rp " + decimalFormat.format(total).toString()
                holder.txtCount.text = currentCount

                updateItem(position, currentCount.toInt())
            } else {
                deleteItem(position)
            }
        }

        holder.btnPlus.setOnClickListener {
            val currentCount = (holder.txtCount.text.toString().toInt() + 1).toString()
            val total = currentItem.total * currentCount.toInt()

            holder.total.text = "Rp " + decimalFormat.format(total).toString()
            holder.txtCount.text = currentCount

            updateItem(position, currentCount.toInt())
        }
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    fun deleteItem(position: Int) {
        sessionManager = SessionManager(context)
        val currentItem = memberList[position]

        val jsonObject = JSONObject()
        try {
            jsonObject.put("product_id", currentItem.id)
            jsonObject.put("quantity", 0)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
            .setTag("activate")
            .addJSONObjectBody(jsonObject) // posting json
            .addHeaders("token", "Bearer" + " " + sessionManager.getToken())
            .setPriority(com.androidnetworking.common.Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("response", response.toString())
                    try {
                        if(response.getString("success").equals("true")) {
                            memberList.removeAt(position)
                            notifyDataSetChanged()
                            Toast.makeText(context, "Produk Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                        }
                    } catch(e: JSONException) {
                        Log.d("onError", e.toString())
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("onError", error.toString())
                }
            })
    }

    fun updateItem(position: Int, count: Int) {
        sessionManager = SessionManager(context)
        val currentItem = memberList[position]

        val jsonObject = JSONObject()
        try {
            jsonObject.put("product_id", currentItem.id)
            jsonObject.put("quantity", count)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
            .setTag("activate")
            .addJSONObjectBody(jsonObject) // posting json
            .addHeaders("token", "Bearer" + " " + sessionManager.getToken())
            .setPriority(com.androidnetworking.common.Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("response", response.toString())
                    try {
                        if(response.getString("success").equals("true")) {
                            //Toast.makeText(context, "Produk Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                        }
                    } catch(e: JSONException) {
                        Log.d("onError", e.toString())
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("onError", error.toString())
                }
            })
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imgProduct)
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
        val discount: TextView = itemView.findViewById(R.id.txtDiscount)
        val total: TextView = itemView.findViewById(R.id.txtTotal)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val btnMinus : Button = itemView.findViewById(R.id.btnMinus)
        val btnPlus : Button = itemView.findViewById(R.id.btnPlus)
        val txtCount : TextView = itemView.findViewById(R.id.txtCount)
    }
}