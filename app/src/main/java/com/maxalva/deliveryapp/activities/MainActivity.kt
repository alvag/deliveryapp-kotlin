package com.maxalva.deliveryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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

        if (!isValidForm(email, password)) {
            Toast.makeText(this, "El formulario no es válido", Toast.LENGTH_LONG).show()
            return
        }

        Log.d(TAG, "El email es $email")
        Log.d(TAG, "El email es $password")
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun isValidForm(email: String, password: String): Boolean {
        return !(email.isBlank() || password.isBlank() || !email.isEmailValid())
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }


}