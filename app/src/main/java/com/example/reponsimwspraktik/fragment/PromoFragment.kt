package com.example.reponsimwspraktik.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.adapter.AdapterHome
import com.example.reponsimwspraktik.adapter.AdapterPromo
import com.example.reponsimwspraktik.data.DataHome
import com.example.reponsimwspraktik.data.DataPromo
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class PromoFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataPromo : ArrayList<DataPromo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_promo, container, false)

        recyclerView = view.findViewById(R.id.recyclerPromo)
        dataPromo = ArrayList<DataPromo>()

        getPromo()

        return view
    }

    private fun getPromo() {
        AndroidNetworking.get("https://grocery-api.tonsu.site/products/promo")
            .setTag("promo")
            .setPriority(com.androidnetworking.common.Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("response", response.toString())
                    try {
                        if(response.getString("success").equals("true")) {
                            val getJsonArray: JSONArray = response.getJSONArray("data")
                            for(i in 0 until getJsonArray.length()) {
                                val item = getJsonArray.getJSONObject(i)
                                val image = item.getString("image")

                                Log.d("item", item.getString("image"))
                                dataPromo.add(
                                    DataPromo(
                                        image,
                                        item.getString("product_name"),
                                        item.getString("unit"),
                                        item.getInt("original_price"),
                                        item.getInt("discount_price")
                                    )
                                )

                                recyclerView.layoutManager = LinearLayoutManager(activity)
                                recyclerView.adapter = activity?.let { AdapterPromo(it, dataPromo) }
                            }
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