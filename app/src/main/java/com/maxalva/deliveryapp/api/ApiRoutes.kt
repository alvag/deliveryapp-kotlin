package com.maxalva.deliveryapp.api

import com.maxalva.deliveryapp.routes.AuthRoutes
import com.maxalva.deliveryapp.routes.UserRoutes
import retrofit2.create

class ApiRoutes {

    companion object {
        private const val API_URL = "http://192.168.1.2:3000/api/"
    }

    private val retrofit = RetrofitClient()

    fun getUserRoutes(): UserRoutes {
        return retrofit.getClient(API_URL).create(UserRoutes::class.java)
    }

    fun getAuthRoutes(): AuthRoutes {
        return retrofit.getClient(API_URL).create(AuthRoutes::class.java)
    }

}