package com.swvl.movietask.data.preferences

import android.content.Context
import com.swvl.movietask.util.Constants

class AppPreferences(private val context: Context) : Preferences {

    override fun writeString(key: String, value: String) {
        context.getSharedPreferences(Constants.PREFERENCES_NAME, Constants.PREFERENCES_MODE)
            .edit().putString(key,value).apply()
    }

    override fun readString(key: String): String {
        return context.getSharedPreferences(Constants.PREFERENCES_NAME, Constants.PREFERENCES_MODE).getString(key, "")!!
    }

    override fun writeInt(key: String, value: Int) {
        context.getSharedPreferences(Constants.PREFERENCES_NAME, Constants.PREFERENCES_MODE)
            .edit().putInt(key,value).apply()
    }

    override fun readInt(key: String): Int {
        return context.getSharedPreferences(Constants.PREFERENCES_NAME, Constants.PREFERENCES_MODE).getInt(key, -1)
    }
}