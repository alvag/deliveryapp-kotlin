package com.maxalva.deliveryapp.routes

import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRoutes {

    @POST(value = "v1/users")
    fun register(@Body user: User): Call<ResponseHttp>

}