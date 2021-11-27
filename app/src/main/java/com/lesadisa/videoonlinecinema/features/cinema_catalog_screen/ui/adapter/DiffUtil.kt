package com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui.adapter

class DiffUtil/*(
    private val oldMoviesList: List<CinemaDomainModel>,
    private val newMoviesList: List<CinemaDomainModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldMoviesList.size

    override fun getNewListSize(): Int = newMoviesList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMoviesList[oldItemPosition] == newMoviesList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMoviesList[oldItemPosition] == newMoviesList[newItemPosition]
}*/
