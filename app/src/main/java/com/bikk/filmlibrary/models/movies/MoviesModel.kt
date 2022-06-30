package com.bikk.filmlibrary.models.movies

data class MoviesModel(
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
)