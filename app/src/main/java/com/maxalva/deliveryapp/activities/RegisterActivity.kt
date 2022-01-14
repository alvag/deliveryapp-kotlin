package com.maxalva.deliveryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.maxalva.deliveryapp.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var imageViewGoToLogin: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageViewGoToLogin = findViewById(R.id.imageview_go_to_login)

        imageViewGoToLogin.setOnClickListener { goToLogin() }
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}