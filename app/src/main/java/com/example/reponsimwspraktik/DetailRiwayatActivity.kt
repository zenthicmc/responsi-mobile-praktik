package com.example.reponsimwspraktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.adapter.AdapterDetailRiwayat
import com.example.reponsimwspraktik.data.DataDetail

class DetailRiwayatActivity : AppCompatActivity() {
    private lateinit var btnBack : ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataDetail : ArrayList<DataDetail>

    private lateinit var txtStatus: TextView
    private lateinit var txtInvoice: TextView
    private lateinit var txtDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.recyclerDetail)
        dataDetail = ArrayList<DataDetail>()

        val status = intent.getStringExtra("status")
        val invoice = intent.getStringExtra("invoice")
        val date = intent.getStringExtra("date")

        txtStatus = findViewById(R.id.txtStatus)
        txtInvoice = findViewById(R.id.txtInvoice)
        txtDate = findViewById(R.id.txtDate)

        if (status == "Selesai") {
            txtStatus.text = status
            txtStatus.setBackgroundResource(R.drawable.badge_green)
        } else {
            txtStatus.text = status
            txtStatus.setBackgroundResource(R.drawable.badge_red)
        }

        txtInvoice.text = invoice
        txtDate.text = date

        val image = arrayOf(
            R.drawable.person10,
            R.drawable.person7,
            R.drawable.clothes1,
        )

        val title = arrayOf(
            "White Dress Size M",
            "Red Dress Size M",
            "Blue Hoodie Size L",
        )

        val jumlah = arrayOf(
            "Jumlah: 1",
            "Jumlah: 2",
            "Jumlah: 3",
        )

        val price = arrayOf(
            "Rp. 100.000",
            "Rp. 200.000",
            "Rp. 300.000",
        )

        val total = arrayOf(
            "Total: Rp. 100.000",
            "Total: Rp. 400.000",
            "Total: Rp. 900.000",
        )

        for(i in title.indices) {
            dataDetail.add(
                DataDetail(
                    image[i],
                    title[i],
                    jumlah[i],
                    price[i],
                    total[i],
                )
            )

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AdapterDetailRiwayat(this, dataDetail)
        }
    }
}