package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val release_date: String,
    val title: String,
    val poster_path: String
):Parcelable