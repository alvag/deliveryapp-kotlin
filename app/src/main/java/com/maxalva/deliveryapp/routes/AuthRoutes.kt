package com.maxalva.deliveryapp.routes

import com.maxalva.deliveryapp.models.ResponseHttp
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthRoutes {

    @FormUrlEncoded
    @POST(value = "v1/login")
    fun login(@Field("email") email: String, @Field(value = "password") password: String): Call<ResponseHttp>

}