package com.example.shows.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.load(imagePath: String?) {
    if (!imagePath.isNullOrEmpty()) {
        val requestOptions = RequestOptions()
      //  requestOptions.placeholder(R.drawable.ic_launcher_background)
        Glide.with(context)
                //.setDefaultRequestOptions(R.drawable.avatar_1_raster)
                .load("https://image.tmdb.org/t/p/w500$imagePath")
                .into(this)
    }
}