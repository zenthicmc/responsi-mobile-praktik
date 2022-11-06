package com.example.reponsimwspraktik.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.reponsimwspraktik.AboutActivity
import com.example.reponsimwspraktik.EditProfileActivity
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.RiwayatActivateActivity

class SettingFragment : Fragment() {
    private lateinit var btnEditProfile: RelativeLayout
    private lateinit var btnAbout: RelativeLayout
    private lateinit var btnRiwayat: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        btnEditProfile = view.findViewById(R.id.btnEditProfile)
        btnAbout = view.findViewById(R.id.btnAbout)
        btnRiwayat = view.findViewById(R.id.btnRiwayat)
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
        return view
    }
}