package com.example.reponsimwspraktik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin : Button
    private lateinit var btnDaftar : Button
    private lateinit var sessionManager : SessionManager
    private lateinit var editEmail : EditText
    private lateinit var editName : EditText
    private lateinit var email: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        checkLogin()

        btnDaftar = findViewById(R.id.btnDaftar)
        btnLogin = findViewById(R.id.btnLogin)

        btnDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            clickLogin()
        }
    }

    private fun init(){
        sessionManager = SessionManager(this)
        editEmail = findViewById(R.id.editEmail)
        editName = findViewById(R.id.editNama)
    }

    private fun checkLogin(){
        if (sessionManager.isLogin()!!){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun clickLogin() {
        email = editEmail.text.toString().trim()
        name = editName.text.toString().trim()

        if (email.isEmpty() || email == ""){
            editEmail.error = "Email wajib diisi"
            editEmail.requestFocus()
        } else if (name.isEmpty() || name == ""){
            editName.error = "Nama wajib diisi"
            editName.requestFocus()
        } else {
            sessionManager.setLoggin(true)
            sessionManager.setName(name)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}