package com.swvl.movietask.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.swvl.movietask.R
import com.swvl.movietask.model.MovieEntry
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesListAdapter(var movies : ArrayList<MovieEntry>, val context: Context, var grouped:Boolean) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    val movieClickListener = PublishSubject.create<MovieEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title + " (${movies[position].year})"
        holder.yearLabel.text = movies[position].year.toString()
        if(movies[position].genres!!.size > 0){
            var genres = ""
            for (genre in movies[position].genres!!){
                genres += "$genre  "
            }
            holder.genres.text = genres
        }else{ holder.genres.visibility = View.GONE }

        if(movies[position].cast!!.size > 0){
            var movieCast = ""
            for (cast in movies[position].cast!!){
                movieCast += "$cast\n"
            }
            holder.cast.text = movieCast
        }else{ holder.cast.visibility = View.GONE }

        if(grouped){
            holder.yearLabel.visibility = View.VISIBLE
            if(position >= 1){
                if(movies[position].year == movies[position-1].year){
                    holder.yearLabel.visibility = View.GONE
                }
            }
        }else{ holder.yearLabel.visibility = View.GONE }

        holder.movieCard.setOnClickListener { movieClickListener.onNext(movies[position]) }
    }

    fun searchResult(movies:ArrayList<MovieEntry>,grouped: Boolean){
        this.movies = movies
        this.grouped = grouped
        notifyDataSetChanged()
    }

    inner class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle = view.movie_title!!
        val genres = view.genres!!
        val cast = view.cast!!
        val yearLabel = view.year_label!!
        val movieCard = view.movie_card!!
    }
}