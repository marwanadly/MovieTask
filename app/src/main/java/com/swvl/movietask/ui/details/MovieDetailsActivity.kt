package com.swvl.movietask.ui.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import com.swvl.movietask.MoviesTaskApplication
import com.swvl.movietask.R
import com.swvl.movietask.di.components.ApplicationComponent
import com.swvl.movietask.di.components.DaggerMainComponent
import com.swvl.movietask.model.MovieEntry
import com.swvl.movietask.util.Constants
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject


class MovieDetailsActivity : AppCompatActivity() {

    private fun getApplicationComponent(): ApplicationComponent = (application as MoviesTaskApplication).component
    @Inject lateinit var movieDetailsViewModel:MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        DaggerMainComponent.builder().applicationComponent(getApplicationComponent())
            .build().inject(this)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val movie = intent.extras!!.getParcelable<MovieEntry>(Constants.DETAILS_EXTRA)
        movieDetailsViewModel.getMovieImage(movie!!.title)
        setupView(movie)
    }

    fun setupView(movie:MovieEntry){
       movieDetailsViewModel.imageURL.observeForever {

         Picasso.get().load(it).error(R.drawable.placeholder).into(movie_image)

       }
        details_title.text = movie.title
        details_rating.rating = movie.rating.toFloat()
        details_year.text = movie.year.toString()
        var genres = ""
        for (genre in movie.genres!!){ genres += "$genre  " }
        details_genres.text = genres
        val castAdapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,movie.cast!!.toArray())
        cast_list.adapter = castAdapter
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
