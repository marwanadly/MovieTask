package com.swvl.movietask.model

import com.google.gson.annotations.SerializedName

data class MovieArray(
    @SerializedName("movies")
    var movies:List<MovieEntry>
)