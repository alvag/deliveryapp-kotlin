package com.maxalva.deliveryapp.routes

import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UserRoutes {

    @POST(value = "v1/users")
    fun register(@Body user: User): Call<ResponseHttp>

    @Multipart
    @PUT("v1/users")
    fun update(
        @Part image: MultipartBody.Part,
        @Part("user") user: RequestBody
    ): Call<ResponseHttp>
}