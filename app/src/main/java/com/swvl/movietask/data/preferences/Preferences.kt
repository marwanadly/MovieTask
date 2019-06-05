package com.swvl.movietask.data.preferences

interface Preferences {
    fun write(key:String,value: String)
    fun read(key:String): String
}