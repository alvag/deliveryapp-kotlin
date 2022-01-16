package com.maxalva.deliveryapp.Utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.lang.Exception

class SharedPref(activity: Activity) {

    private var prefs: SharedPreferences? = null

    init {
        prefs = activity.getSharedPreferences("com.maxalva.deliveryapp", Context.MODE_PRIVATE)
    }

    fun save(key: String, value: Any) {
        try {
            val gson = Gson()
            val json = gson.toJson(value)

            with(prefs?.edit()) {
                this?.putString(key, json)
                this?.commit()
            }
        } catch (e: Exception) {
            Log.d("ERROR", "Error: ${e.message}")
        }
    }

    fun getData(key: String): String? {
        return prefs?.getString(key, "")
    }

    fun remove(key: String) {
        prefs?.edit()?.remove(key)?.apply()
    }
}