package com.lesadisa.videoonlinecinema.features.films_screen.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class DiffUtil(
    private val oldMoviesList: List<CinemaDomainModel>,
    private val newMoviesList: List<CinemaDomainModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldMoviesList.size

    override fun getNewListSize(): Int = newMoviesList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMoviesList[oldItemPosition] == newMoviesList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMoviesList[oldItemPosition] == newMoviesList[newItemPosition]
}
