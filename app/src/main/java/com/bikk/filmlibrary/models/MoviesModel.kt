package com.bikk.filmlibrary.models

data class MoviesModel(
    val page: Int,
    val results: List<MovieItemModel>,
    val total_pages: Int,
    val total_results: Int
)