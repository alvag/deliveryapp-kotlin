package com.maxalva.deliveryapp.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.models.User

class ClientHomeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ClientHomeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        getUserFromSession()
    }

    private fun getUserFromSession() {
        val sharedPref = SharedPref(this)

        if (!sharedPref.getData("user").isNullOrBlank()) {
            val gson = Gson()
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)

            Log.d(TAG, "User: $user")
        }
    }
}