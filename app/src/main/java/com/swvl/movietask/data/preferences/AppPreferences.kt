package com.swvl.movietask.data.preferences

import android.content.Context
import com.swvl.movietask.util.Constants

class AppPreferences(private val context: Context) : Preferences {

    override fun write(key: String, value: String) {
        context.getSharedPreferences(Constants.preferencesName, Constants.preferencesMode)
            .edit().putString(key,value).apply()
    }

    override fun read(key: String): String {
        return context.getSharedPreferences(Constants.preferencesName, Constants.preferencesMode).getString(key, "")!!
    }
}