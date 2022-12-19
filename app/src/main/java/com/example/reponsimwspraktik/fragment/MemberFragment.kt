package com.example.reponsimwspraktik.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.SessionManager
import com.example.reponsimwspraktik.adapter.AdapterMember
import com.example.reponsimwspraktik.adapter.AdapterPromo
import com.example.reponsimwspraktik.data.DataMember
import com.example.reponsimwspraktik.data.DataPromo
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MemberFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataMember : ArrayList<DataMember>
    private lateinit var sessionManager : SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_member, container, false)

        recyclerView = view.findViewById(R.id.recyclerMember)
        dataMember = ArrayList<DataMember>()

        getMember()

        return view
    }

    private fun getMember() {
        sessionManager = SessionManager(context)
        AndroidNetworking.get("https://grocery-api.tonsu.site/products/cart")
            .setTag("promo")
            .addHeaders("token", "Bearer" + " " + sessionManager.getToken())
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
                                dataMember.add(
                                    DataMember(
                                        item.getInt("product_id"),
                                        image,
                                        item.getString("product_name"),
                                        item.getInt("original_price"),
                                        item.getInt("member_price"),
                                        item.getInt("original_price") - item.getInt("member_price")
                                    )
                                )

                                recyclerView.layoutManager = GridLayoutManager(context, 2)
                                recyclerView.adapter = activity?.let { AdapterMember(it, dataMember) }
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