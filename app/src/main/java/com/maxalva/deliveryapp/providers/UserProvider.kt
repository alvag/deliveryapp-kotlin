package com.maxalva.deliveryapp.providers

import com.maxalva.deliveryapp.api.ApiRoutes
import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.routes.UserRoutes
import com.maxalva.deliveryapp.models.User
import retrofit2.Call

class UserProvider {

    private var userRoutes: UserRoutes? = null

    init {
        val api = ApiRoutes()
        userRoutes = api.getUserRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return userRoutes?.register(user)
    }

}