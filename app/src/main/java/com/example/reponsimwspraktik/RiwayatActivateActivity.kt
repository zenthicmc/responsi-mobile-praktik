package com.example.reponsimwspraktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.adapter.AdapterMember
import com.example.reponsimwspraktik.adapter.AdapterRiwayatActivate
import com.example.reponsimwspraktik.data.DataActivate
import com.example.reponsimwspraktik.data.DataMember
import com.example.reponsimwspraktik.RecyclerRiwayatClickListener

class RiwayatActivateActivity : AppCompatActivity() {
    private lateinit var btnBack : ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataActivate : ArrayList<DataActivate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_activate)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.recyclerActivate)
        dataActivate = ArrayList<DataActivate>()

        val status = arrayOf(
            "Selesai",
            "Dibatalkan",
            "Selesai",
            "Dibatalkan",
            "Selesai",
            "Dibatalkan",
            "Selesai",
        )

        val invoice = arrayOf(
            "INVOICE: 861156058",
            "INVOICE: 440166852",
            "INVOICE: 329600401",
            "INVOICE: 869892612",
            "INVOICE: 522966370",
            "INVOICE: 269941991",
            "INVOICE: 604590644",
        )

        val total = arrayOf(
            "Total Bayar: Rp. 100.000",
            "Total Bayar: Rp. 200.000",
            "Total Bayar: Rp. 300.000",
            "Total Bayar: Rp. 400.000",
            "Total Bayar: Rp. 500.000",
            "Total Bayar: Rp. 600.000",
            "Total Bayar: Rp. 700.000",
        )

        val date = arrayOf(
            "06 November 2022",
            "07 November 2022",
            "08 November 2022",
            "09 November 2022",
            "10 November 2022",
            "11 November 2022",
            "12 November 2022",
        )

        for(i in invoice.indices) {
            dataActivate.add(
                DataActivate(
                    status[i],
                    invoice[i],
                    total[i],
                    date[i],
                )
            )

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AdapterRiwayatActivate(this, dataActivate)
        }
    }
}