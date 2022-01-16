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
import com.google.gson.Gson
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.deliveryapp.activities.client.home.ClientHomeActivity
import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.providers.AuthProvider
import com.maxalva.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var imageViewGoToRegister: ImageView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button

    private var authProvider = AuthProvider()
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.imageview_go_to_register)
        imageViewGoToRegister.setOnClickListener { goToRegister() }

        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPassword = findViewById(R.id.edit_text_password)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener { login() }

        sharedPref = SharedPref(this)

        if (!sharedPref.getData("user").isNullOrBlank()) {
            goToClientHome()
        }
    }

    private fun login() {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if (!isValidForm(email, password)) {
            Toast.makeText(this, "El formulario no es válido", Toast.LENGTH_LONG).show()
            return
        }

        authProvider.login(email, password)?.enqueue(object: Callback<ResponseHttp> {
            override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                Log.d(TAG, "onResponse: $response")
                Log.d(TAG, "onResponse: ${response.body()}")

                if (response.body()?.isSuccess == true) {
                    Toast.makeText(this@MainActivity, "onResponse: ${response.body()?.message}", Toast.LENGTH_LONG).show()
                    saveUserSession(response.body()?.data.toString())
                    goToClientHome()
                }
            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@MainActivity, "onFailure: ${t.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun goToClientHome() {
        val i = Intent(this, ClientHomeActivity::class.java)
        startActivity(i)
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

    private fun saveUserSession(data: String) {
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)

        sharedPref.save("user", user)
    }

}