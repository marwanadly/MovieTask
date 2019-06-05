package com.swvl.movietask.model

import io.realm.RealmModel

open class MovieEntry : RealmModel{
    var title:String = ""
    var year:Int = 0
    var cast:ArrayList<String>? = null
    var genres:ArrayList<String>? = null
    var rating:Int = 0
}