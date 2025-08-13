package com.example.littlelemon.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

const val USER_FIRST_KEY = "USER_FIRST_KEY"
const val USER_LAST_NAME_KEY = "USER_LAST_NAME_KEY"
const val USER_EMAIL_KEY = "USER_EMAIL_KEY"

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        sharedPreferences.edit(true) {
            putString(key, value)
        }
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clearPreferences() {
        sharedPreferences.edit(true) { clear() }
    }
}