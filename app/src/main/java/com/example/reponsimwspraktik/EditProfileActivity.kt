package com.example.reponsimwspraktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class EditProfileActivity : AppCompatActivity() {
    private lateinit var btnBack : ImageView
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        btnSimpan = findViewById(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Profile Berhasil Disimpan", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}