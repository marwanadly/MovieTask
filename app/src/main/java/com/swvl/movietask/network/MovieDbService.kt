package com.swvl.movietask.network

import com.swvl.movietask.data.remote.MovieDbResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {
    @GET("/3/search/movie")
    fun getMovieImageData(@Query("api_key") apiKey:String, @Query("query") movieTitle:String): Observable<MovieDbResponse>
}