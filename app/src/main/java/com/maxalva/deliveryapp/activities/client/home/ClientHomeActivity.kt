package com.maxalva.deliveryapp.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.deliveryapp.activities.MainActivity
import com.maxalva.models.User

class ClientHomeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ClientHomeActivity"
    }

    private lateinit var sharedPref: SharedPref

    private lateinit var btnLogout: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        sharedPref = SharedPref(this)

        btnLogout = findViewById(R.id.btn_log_out)

        btnLogout.setOnClickListener { logout() }

        getUserFromSession()
    }

    private fun logout() {
        sharedPref.remove("user")
        goToLogin()
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession() {
        if (!sharedPref.getData("user").isNullOrBlank()) {
            val gson = Gson()
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)

            Log.d(TAG, "User: $user")
        }
    }
}