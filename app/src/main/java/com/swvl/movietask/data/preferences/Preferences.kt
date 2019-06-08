package com.swvl.movietask.data.preferences

interface Preferences {
    fun writeString(key:String,value: String)
    fun readString(key:String): String
    fun writeInt(key:String,value: Int)
    fun readInt(key:String): Int
}