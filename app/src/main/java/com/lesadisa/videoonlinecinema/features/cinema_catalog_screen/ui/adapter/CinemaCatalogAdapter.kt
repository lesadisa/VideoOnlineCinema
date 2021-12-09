package com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesadisa.videoonlinecinema.base.loadImage
import com.lesadisa.videoonlinecinema.base.setThrottledClickListener
import com.lesadisa.videoonlinecinema.databinding.CinemaItemCatalogBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class CinemaCatalogAdapter(
    private var movies: List<CinemaDomainModel>,
    private val onItemClick: (movie: CinemaDomainModel) -> Unit, //
) :
    RecyclerView.Adapter<CinemaCatalogAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = CinemaItemCatalogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MoviesViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie = movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class MoviesViewHolder(private val binding: CinemaItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: CinemaDomainModel) {

            binding.tvVoteAverage.text = movie.voteAverage.toString()
            binding.tvOriginalTitle.text = movie.originalTitle
            binding.tvOverview.text = movie.overview
            binding.moviePoster.apply {
                loadImage(movie.posterPath)
                          }
            binding.cardItemLayout.setThrottledClickListener {
                onItemClick(movie)
            }

        }
    }

    fun updateList(newMovies: List<CinemaDomainModel>) {

        movies = newMovies
        notifyDataSetChanged()
    }
}