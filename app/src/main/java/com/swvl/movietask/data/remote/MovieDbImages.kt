package com.swvl.movietask.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDbImages (
    @SerializedName("poster_path")
    var imagePath:String
)