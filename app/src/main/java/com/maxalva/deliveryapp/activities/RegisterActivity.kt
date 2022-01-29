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
import com.maxalva.deliveryapp.providers.UserProvider
import com.maxalva.deliveryapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private var userProvider = UserProvider()

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

        if (!isValidForm(name, lastName, email, phone, password, confirmPassword)) {
            Toast.makeText(this, "El formulario no es válido", Toast.LENGTH_LONG).show()
            return
        }

        val user = User(
            name = name,
            lastName = lastName,
            email = email,
            phone = phone,
            password = password,
        )

        userProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
            override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                Log.d(TAG, "onResponse: $response")
                Log.d(TAG, "onResponse: ${response.body()}")

                if (response.body()?.isSuccess == true) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "${response.body()?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    saveUserSession(response.body()?.data.toString())
                    goToSaveImage()
                }

            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun goToSaveImage() {
        val i = Intent(this, SaveImageActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun saveUserSession(data: String) {
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)

        sharedPref.save("user", user)
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun isValidForm(
        name: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isBlank()) {
            return false
        }

        if (lastName.isBlank()) {
            return false
        }

        if (email.isBlank()) {
            return false
        }

        if (!email.isEmailValid()) {
            return false
        }

        if (phone.isBlank()) {
            return false
        }

        if (password.isBlank()) {
            return false
        }

        if (password != confirmPassword) {
            return false
        }

        return true
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }
}