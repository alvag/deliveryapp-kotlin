package com.maxalva.deliveryapp.providers

import com.maxalva.deliveryapp.api.ApiRoutes
import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.routes.UserRoutes
import com.maxalva.deliveryapp.models.User
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class UserProvider {

    private var userRoutes: UserRoutes? = null

    init {
        val api = ApiRoutes()
        userRoutes = api.getUserRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return userRoutes?.register(user)
    }

    fun update(file: File, user: User): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val image = MultipartBody.Part.createFormData("image", file.name, reqFile)
        val reqBody = RequestBody.create(MediaType.parse("text/plane"), user.toJson())
        return userRoutes?.update(image, reqBody)
    }

}