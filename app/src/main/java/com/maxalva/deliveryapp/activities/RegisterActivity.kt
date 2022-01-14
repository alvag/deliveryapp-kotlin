package com.maxalva.deliveryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.maxalva.deliveryapp.R

class RegisterActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "RegisterActivity"
    }

    private lateinit var imageViewGoToLogin: ImageView
    private lateinit var editTextName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageViewGoToLogin = findViewById(R.id.imageview_go_to_login)
        imageViewGoToLogin.setOnClickListener { goToLogin() }

        editTextName = findViewById(R.id.edit_text_name)
        editTextLastName = findViewById(R.id.edit_text_lastname)
        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPhone = findViewById(R.id.edit_text_phone)
        editTextPassword = findViewById(R.id.edit_text_password)
        editTextConfirmPassword = findViewById(R.id.edit_text_confirm_password)

        btnRegister = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener { register() }
    }

    private fun register() {
        val name = editTextName.text.toString()
        val lastName = editTextLastName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val password = editTextPassword.text.toString()
        val confirmPassword = editTextConfirmPassword.text.toString()

        Log.d(TAG, "El nombre es $name")
        Log.d(TAG, "El apellido es $lastName")
        Log.d(TAG, "El email es $email")
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}