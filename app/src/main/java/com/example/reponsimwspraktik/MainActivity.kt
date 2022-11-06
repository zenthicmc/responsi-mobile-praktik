package com.example.reponsimwspraktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.reponsimwspraktik.fragment.HomeFragment
import com.example.reponsimwspraktik.fragment.MemberFragment
import com.example.reponsimwspraktik.fragment.PromoFragment
import com.example.reponsimwspraktik.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fragment: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)
        fragment = findViewById(R.id.fragment_container)

        replaceFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.nav_promo -> {
                    replaceFragment(PromoFragment())
                }
                R.id.nav_member -> {
                    replaceFragment(MemberFragment())
                }
                R.id.nav_setting -> {
                    replaceFragment(SettingFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}