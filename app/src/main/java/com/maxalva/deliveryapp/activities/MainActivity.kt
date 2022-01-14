package com.maxalva.deliveryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.maxalva.deliveryapp.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var imageViewGoToRegister: ImageView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.imageview_go_to_register)
        imageViewGoToRegister.setOnClickListener { goToRegister() }

        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPassword = findViewById(R.id.edit_text_password)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener { login() }
    }

    private fun login() {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        Log.d(TAG, "El email es $email")
        Log.d(TAG, "El email es $password")
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }


}