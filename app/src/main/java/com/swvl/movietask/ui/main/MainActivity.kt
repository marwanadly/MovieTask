package com.swvl.movietask.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.swvl.movietask.MoviesTaskApplication
import com.swvl.movietask.R
import com.swvl.movietask.di.components.ApplicationComponent
import com.swvl.movietask.di.components.DaggerMainComponent
import com.swvl.movietask.model.MovieEntry
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private fun getApplicationComponent(): ApplicationComponent = (application as MoviesTaskApplication).component

    @Inject lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.builder().applicationComponent(getApplicationComponent())
            .build().inject(this)
        mainViewModel.retrieveAllMovies()
        val moviesList = ArrayList<MovieEntry>()
        mainViewModel.moviesList.observeForever { list-> moviesList.addAll(list!!.toList());setupRecyclerView(moviesList) }
    }

    private fun setupRecyclerView(movies:ArrayList<MovieEntry>){
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = MoviesListAdapter(movies, this)
    }
}
