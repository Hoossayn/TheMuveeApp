package com.example.data.model.shows


data class ShowsResult (
    val page: Int,
    val results: List<Shows>,
    val total_pages: Int,
    val total_results: Int
)


data class Shows(
    val poster_path: String,
    val id: Int,
    val original_language: String,
    val name: String,
    val overview: String,
)
