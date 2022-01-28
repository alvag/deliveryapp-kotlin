package com.maxalva.deliveryapp.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("is_available") val isAvailable: Boolean? = null,
    @SerializedName("session_token") val sessionToken: String? = null,
    @SerializedName("password") val password: String,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("roles") val roles: ArrayList<Role>? = null,
) {

    override fun toString(): String {
        return "User(id=$id, name='$name', lastName='$lastName', phone='$phone', email='$email', image=$image, isAvailable=$isAvailable, sessionToken=$sessionToken, password='$password', createdAt=$createdAt, updatedAt=$updatedAt, roles=$roles)"
    }
}