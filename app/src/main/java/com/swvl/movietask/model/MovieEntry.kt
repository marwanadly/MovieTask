package com.swvl.movietask.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class MovieEntry(
    var title:String = "",
    var year:Int = 0,
    var cast:RealmList<String>? = null,
    var genres:RealmList<String>? = null,
    var rating:Int = 0
):RealmObject()