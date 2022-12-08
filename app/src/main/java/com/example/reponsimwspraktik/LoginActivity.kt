package com.example.reponsimwspraktik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin : Button
    private lateinit var btnDaftar : Button
    private lateinit var sessionManager : SessionManager
    private lateinit var editEmail : EditText
    private lateinit var editPassword : EditText
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
//        checkLogin()

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
        editPassword = findViewById(R.id.editPassword)
    }

//    private fun checkLogin(){
//        if (sessionManager.isLogin()!!){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }

    fun clickLogin() {
        email = editEmail.text.toString().trim()
        password = editPassword.text.toString().trim()

        if (email.isEmpty() || email == ""){
            editEmail.error = "Email wajib diisi"
            editEmail.requestFocus()
        } else if (password.isEmpty() || password == ""){
            editPassword.error = "Password wajib diisi"
            editPassword.requestFocus()
        } else {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("email", email)
                jsonObject.put("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post("https://grocery-api.tonsu.site/auth/login")
                .addJSONObjectBody(jsonObject) // posting json
                .addHeaders("Content-Type", "application/json")
                .setPriority(com.androidnetworking.common.Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("response", response.toString())
                        try {
                            if(response.getString("success").equals("true")){
                                Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                                sessionManager.setLogin(true)
                                sessionManager.setEmail(email)
                            }
                        } catch(e: JSONException) {
                            Log.d("onError", e.toString())
                        }
                    }

                    override fun onError(error: ANError) {
                        Log.d("onError", error.toString())
                    }
                })
        }
    }
}