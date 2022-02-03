package com.maxalva.deliveryapp.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.deliveryapp.activities.MainActivity
import com.maxalva.deliveryapp.fragments.client.ClientCategoriesFragment
import com.maxalva.deliveryapp.fragments.client.ClientOrdersFragment
import com.maxalva.deliveryapp.fragments.client.ClientProfileFragment
import com.maxalva.deliveryapp.models.User

class ClientHomeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ClientHomeActivity"
    }

    private lateinit var sharedPref: SharedPref

    //    private lateinit var btnLogout: Button;
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        openFragment(ClientCategoriesFragment())

        sharedPref = SharedPref(this)
        getUserFromSession()

//        btnLogout = findViewById(R.id.btn_log_out)
//        btnLogout.setOnClickListener { logout() }

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    openFragment(ClientCategoriesFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_orders -> {
                    openFragment(ClientOrdersFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_profile -> {
                    openFragment(ClientProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }

            return@setOnNavigationItemSelectedListener false
        }

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun logout() {
        sharedPref.remove("user")
        sharedPref.remove("role")
        goToLogin()
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
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