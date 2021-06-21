package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shows(
    val poster_path: String,
    val id: Int,
    val original_language: String,
    val name: String,
    val overview: String,
): Parcelable