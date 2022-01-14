package com.maxalva.deliveryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.maxalva.deliveryapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var imageViewGoToRegister: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.imageview_go_to_register)

        imageViewGoToRegister.setOnClickListener { goToRegister() }
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }
}