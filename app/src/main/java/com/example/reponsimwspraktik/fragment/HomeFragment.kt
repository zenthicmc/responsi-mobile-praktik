package com.example.reponsimwspraktik.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reponsimwspraktik.R
import com.example.reponsimwspraktik.adapter.AdapterHome
import com.example.reponsimwspraktik.data.DataHome

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataHome : ArrayList<DataHome>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerHome)
        dataHome = ArrayList<DataHome>()

        val image = arrayOf(
            R.drawable.boba1,
            R.drawable.boba2,
            R.drawable.boba3,
            R.drawable.boba4,
        )

        for(i in image.indices){
            dataHome.add(
                DataHome(
                    image[i],
                )
            )

            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = AdapterHome(dataHome)
        }
        return view
    }
}