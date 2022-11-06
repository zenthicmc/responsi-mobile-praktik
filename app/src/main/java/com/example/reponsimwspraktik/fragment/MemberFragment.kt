package com.example.reponsimwspraktik.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.adapter.AdapterMember
import com.example.reponsimwspraktik.data.DataMember

class MemberFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataMember : ArrayList<DataMember>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_member, container, false)

        recyclerView = view.findViewById(R.id.recyclerMember)
        dataMember = ArrayList<DataMember>()

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

        val total = arrayOf(
            "Rp. 50.000",
            "Rp. 100.000",
            "Rp. 150.000",
            "Rp. 200.000",
            "Rp. 250.000",
        )

        for(i in title.indices){
            dataMember.add(
                DataMember(
                    image[i],
                    title[i],
                    price[i],
                    discount[i],
                    total[i]
                )
            )

            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = AdapterMember(dataMember)
        }
        return view
    }
}