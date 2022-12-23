package com.example.reponsimwspraktik.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.reponsimwspraktik.*
import com.example.reponsimwspraktik.adapter.AdapterHome
import com.example.reponsimwspraktik.data.DataHome
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SettingFragment : Fragment() {
    private lateinit var photoProfile: ImageView
    private lateinit var btnEditProfile: RelativeLayout
    private lateinit var btnAbout: RelativeLayout
    private lateinit var btnDetail: RelativeLayout
    private lateinit var btnRiwayat: RelativeLayout
    private lateinit var btnLogout: RelativeLayout
    private lateinit var txtName: TextView
    private lateinit var sessionManager: SessionManager
    private lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // Set Name To Current User
        sessionManager = SessionManager(context)

        btnEditProfile = view.findViewById(R.id.btnEditProfile)
        btnAbout = view.findViewById(R.id.btnAbout)
        btnRiwayat = view.findViewById(R.id.btnRiwayat)
        btnDetail = view.findViewById(R.id.btnDetail)
        btnLogout = view.findViewById(R.id.btnLogout)
        val activity = getActivity()

        getProfile(view)

        btnEditProfile.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }

        btnAbout.setOnClickListener {
            val intent = Intent(activity, AboutActivity::class.java)
            startActivity(intent)
        }

        btnRiwayat.setOnClickListener {
            val intent = Intent(activity, RiwayatActivateActivity::class.java)
            startActivity(intent)
        }

        btnDetail.setOnClickListener {
            val intent = Intent(activity, DetailRiwayatActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            sessionManager.removeData()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return view
    }

    private fun getProfile(view: View) {
        sessionManager = SessionManager(context)
        photoProfile = view.findViewById(R.id.photoProfile)
        txtName = view.findViewById(R.id.txtName)

        AndroidNetworking.get("https://grocery-api.tonsu.site/members")
            .setTag("members")
            .addHeaders("token", "Bearer" + " " + sessionManager.getToken())
            .setPriority(com.androidnetworking.common.Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("response", response.toString())
                    try {
                        if(response.getString("success").equals("true")) {
                            val getJsonObject: JSONObject = response.getJSONObject("data")

                            Picasso.get()
                                .load(getJsonObject.getString("photo_profile"))
                                .into(photoProfile)

                            txtName.text = getJsonObject.getString("name")
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