package com.example.reponsimwspraktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class DetailKatalogActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var button: Button
    private lateinit var btnBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_katalog)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        image = findViewById(R.id.image)
        button = findViewById(R.id.button)
        val getImage = intent.getStringExtra("image")

        Picasso.get()
            .load(getImage)
            .into(image)

        button.setOnClickListener { Toast.makeText(this@DetailKatalogActivity,"berhasil", Toast.LENGTH_SHORT).show() }
    }
}