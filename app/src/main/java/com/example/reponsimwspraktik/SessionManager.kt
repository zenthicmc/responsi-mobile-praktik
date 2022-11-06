package com.example.reponsimwspraktik

import android.content.Context
import android.content.SharedPreferences

class SessionManager(var context: Context?) {
    val PRIVATE_MODE = 0
    private val PREF_NAME = "SharedPreferences"
    private val IS_LOGIN = "is_login"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setLoggin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setName(name: String?) {
        editor?.putString("name", name)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun getName(): String? {
        return pref?.getString("name", "")
    }

    fun removeData() {
        editor?.clear()
        editor?.commit()
    }
}