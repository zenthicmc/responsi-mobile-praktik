package com.example.reponsimwspraktik.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.reponsimwspraktik.*

class SettingFragment : Fragment() {
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
        name = sessionManager.getName().toString()

        txtName = view?.findViewById(R.id.txtName)!!
        txtName.text = name

        btnEditProfile = view.findViewById(R.id.btnEditProfile)
        btnAbout = view.findViewById(R.id.btnAbout)
        btnRiwayat = view.findViewById(R.id.btnRiwayat)
        btnDetail = view.findViewById(R.id.btnDetail)
        btnLogout = view.findViewById(R.id.btnLogout)
        val activity = getActivity()

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
}