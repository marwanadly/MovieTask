package com.swvl.movietask.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.swvl.movietask.MoviesTaskApplication
import com.swvl.movietask.R
import com.swvl.movietask.di.components.ApplicationComponent
import com.swvl.movietask.di.components.DaggerMainComponent
import com.swvl.movietask.model.MovieEntry
import com.swvl.movietask.ui.details.MovieDetailsActivity
import com.swvl.movietask.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private fun getApplicationComponent(): ApplicationComponent = (application as MoviesTaskApplication).component

    @Inject lateinit var mainViewModel: MainViewModel
    private val moviesList = ArrayList<MovieEntry>()
    private val searchResult = ArrayList<MovieEntry>()
    lateinit var movieListAdapter: MoviesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.builder().applicationComponent(getApplicationComponent())
            .build().inject(this)
        mainViewModel.retrieveAllMovies()
        mainViewModel.moviesList.observeForever { list-> moviesList.addAll(list!!.toList());setupRecyclerView(moviesList) }
        search_et.addTextChangedListener(getTextWatcher())
    }

    @SuppressLint("CheckResult")
    private fun setupRecyclerView(movies:ArrayList<MovieEntry>){
        movie_list.layoutManager = LinearLayoutManager(this)
        movieListAdapter = MoviesListAdapter(movies, this,false)
        movie_list.adapter = movieListAdapter
        movieListAdapter.movieClickListener.subscribe { movie->
            val detailsIntent = Intent(this,MovieDetailsActivity::class.java)
            detailsIntent.putExtra(Constants.DETAILS_EXTRA,movie)
            startActivity(detailsIntent)
        }
    }

    private fun getTextWatcher():TextWatcher{
       return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.isEmpty()){
                    movieListAdapter.searchResult(moviesList,false)
                }else{
                    searchResult.clear()
                    searchResult.addAll(mainViewModel.searchForMovies(s.toString())); movieListAdapter.searchResult(searchResult,true)
                }
            }
        }
    }
}
