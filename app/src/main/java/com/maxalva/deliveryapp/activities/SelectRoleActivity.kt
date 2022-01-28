package com.maxalva.deliveryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.deliveryapp.adapters.RolesAdapter
import com.maxalva.deliveryapp.models.User

class SelectRoleActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPref
    private lateinit var recyclerviewRoles: RecyclerView
    private lateinit var user: User
    private lateinit var adapter: RolesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)

        sharedPref = SharedPref(this)
        getUserFromSession()

        recyclerviewRoles = findViewById(R.id.recyclerview_roles)
        recyclerviewRoles.layoutManager = LinearLayoutManager(this)

        adapter = RolesAdapter(this, user.roles!!)
        recyclerviewRoles.adapter = adapter
    }

    private fun getUserFromSession() {
        val userStr = sharedPref.getData("user")
        val gson = Gson()
        user = gson.fromJson(userStr, User::class.java)
    }
}