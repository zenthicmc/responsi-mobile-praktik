package com.example.reponsimwspraktik.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.reponsimwspraktik.LoginActivity
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.SessionManager
import com.example.reponsimwspraktik.adapter.AdapterHome
import com.example.reponsimwspraktik.data.DataHome
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataHome : ArrayList<DataHome>
    private lateinit var sessionManager : SessionManager
    private lateinit var shimmerPromo: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerHome)
        dataHome = ArrayList<DataHome>()
        shimmerPromo = view.findViewById(R.id.shimmerHome)

        // start shimmer
        shimmerPromo.visibility = View.VISIBLE
        shimmerPromo.startShimmer()

        getKatalog()

        return view
    }
    
    private fun getKatalog() {
        AndroidNetworking.get("https://grocery-api.tonsu.site/products/katalog")
            .setTag("katalog")
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
                                dataHome.add(
                                    DataHome(
                                        item.getInt("id"),
                                        image
                                    )
                                )

                                // stop shimmer
                                shimmerPromo.stopShimmer()
                                shimmerPromo.visibility = View.GONE

                                recyclerView.layoutManager = LinearLayoutManager(activity)
                                recyclerView.adapter = activity?.let { AdapterHome(it, dataHome) }
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