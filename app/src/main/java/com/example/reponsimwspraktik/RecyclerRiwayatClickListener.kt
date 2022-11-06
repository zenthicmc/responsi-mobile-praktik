package com.example.reponsimwspraktik

import android.view.View
import com.example.reponsimwspraktik.data.DataActivate

interface RecyclerRiwayatClickListener {
    fun onItemClicked(view: View, activate: DataActivate)
}