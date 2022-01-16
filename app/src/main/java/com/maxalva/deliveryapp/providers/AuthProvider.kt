package com.maxalva.deliveryapp.providers

import com.maxalva.deliveryapp.api.ApiRoutes
import com.maxalva.deliveryapp.models.ResponseHttp
import com.maxalva.deliveryapp.routes.AuthRoutes
import retrofit2.Call

class AuthProvider {

    private var authRoutes: AuthRoutes? = null

    init {
        val api = ApiRoutes()
        authRoutes = api.getAuthRoutes()
    }

    fun login(email: String, password: String): Call<ResponseHttp>? {
        return authRoutes?.login(email, password)
    }

}