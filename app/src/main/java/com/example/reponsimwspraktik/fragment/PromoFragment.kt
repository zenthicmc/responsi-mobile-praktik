package com.example.reponsimwspraktik.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.adapter.AdapterPromo
import com.example.reponsimwspraktik.data.DataPromo

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

        val image = arrayOf(
            R.drawable.person10,
            R.drawable.person11,
            R.drawable.clothes1,
            R.drawable.clothes2,
            R.drawable.clothes3,
        )

        val title = arrayOf(
            "White Dress Size M",
            "Red Dress Size M",
            "Blue Hoodie Size L",
            "Black Tuxedo Size L",
            "Red Shirt Size L",
        )

        val category = arrayOf(
            "pieces",
            "pieces",
            "pieces",
            "pieces",
            "pieces",
        )

        val price = arrayOf(
            "Rp. 100.000",
            "Rp. 200.000",
            "Rp. 300.000",
            "Rp. 400.000",
            "Rp. 500.000",
        )

        val discount = arrayOf(
            "Rp. 50.000",
            "Rp. 100.000",
            "Rp. 150.000",
            "Rp. 200.000",
            "Rp. 250.000",
        )


        for(i in title.indices){
            dataPromo.add(
                DataPromo(
                    image[i],
                    title[i],
                    category[i],
                    price[i],
                    discount[i]
                )
            )

            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = AdapterPromo(dataPromo)
        }
        return view
    }
}