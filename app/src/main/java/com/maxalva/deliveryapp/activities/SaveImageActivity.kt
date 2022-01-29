package com.maxalva.deliveryapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.deliveryapp.activities.client.home.ClientHomeActivity
import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.models.User
import com.maxalva.deliveryapp.providers.UserProvider
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SaveImageActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SaveImageActivity"
    }

    private lateinit var circleImageUser: CircleImageView
    private lateinit var btnSkip: Button
    private lateinit var btnConfirm: Button
    private var imageFile: File? = null
    private var userProvider = UserProvider()
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image)

        sharedPref = SharedPref(this)

        circleImageUser = findViewById(R.id.circle_image_user)
        btnSkip = findViewById(R.id.btn_skip)
        btnConfirm = findViewById(R.id.btn_confirm)

        circleImageUser.setOnClickListener { selectImage() }

        btnSkip.setOnClickListener { goToClientHome() }

        btnConfirm.setOnClickListener {
            updateImage()
        }
    }

    private fun updateImage() {
        val user = Gson().fromJson(sharedPref.getData("user"), User::class.java)
        imageFile?.let {
            userProvider.update(it, user)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Log.d(TAG, "onResponse: $response")
                    Log.d(TAG, "onResponse: ${response.body()}")

                    if (response.body()?.isSuccess == true) {
                        saveUserSession(response.body()?.data.toString())
                        Toast.makeText(
                            this@SaveImageActivity,
                            "${response.body()?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(this@SaveImageActivity, "Error: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }

            })
        }
    }

    private fun saveUserSession(data: String) {
        val sharedPref = SharedPref(this)
        val user = Gson().fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    private fun goToClientHome() {
        val i = Intent(this, ClientHomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private val startImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data
                    imageFile = File(fileUri?.path!!)
                    circleImageUser.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "La tarea se canceló", Toast.LENGTH_LONG).show()
                }
            }
        }


    private fun selectImage() {
        ImagePicker
            .with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startImageForResult.launch(intent)
            }
    }
}