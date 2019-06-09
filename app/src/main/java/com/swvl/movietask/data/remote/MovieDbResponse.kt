package com.swvl.movietask.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDbResponse (
    @SerializedName("results")
    var results: List<MovieDbImages>
)