package com.swvl.movietask.util

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

class CommonUtils {
    companion object {
        @Throws(IOException::class)
        fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
            val manager = context.assets
            val inputStream = manager.open(jsonFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, Charset.forName("UTF-8"))
        }
    }
}