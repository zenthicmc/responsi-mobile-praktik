package com.example.reponsimwspraktik.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.SessionManager
import com.example.reponsimwspraktik.data.DataMember
import com.example.reponsimwspraktik.data.DataPromo
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat

class AdapterPromo(val context: Context, val promoList: ArrayList<DataPromo>): RecyclerView.Adapter<AdapterPromo.MyViewHolder>() {
    private lateinit var sessionManager : SessionManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_promo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        sessionManager = SessionManager(context)
        val currentItem = promoList[position]
        val decimalFormat = DecimalFormat("#,###")

        // load image
        Picasso.get()
            .load(currentItem.image)
            .into(holder.image)

        holder.title.text = currentItem.title
        holder.category.text = currentItem.category
        holder.price.text = "Rp " + decimalFormat.format(currentItem.price).toString()
        holder.discount.text = "Rp " + decimalFormat.format(currentItem.discount).toString()
        holder.discount.setPaintFlags(holder.discount.getPaintFlags() or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG)

        holder.btnAktif.setOnClickListener {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("product_id", currentItem.id)
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
                                Toast.makeText(context, "Produk Berhasil Diaktifkan", Toast.LENGTH_SHORT).show()
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
        val btnAktif : Button = itemView.findViewById(R.id.btnAktif)
    }
}