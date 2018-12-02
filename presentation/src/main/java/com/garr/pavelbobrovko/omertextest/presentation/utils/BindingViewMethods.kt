package com.garr.pavelbobrovko.omertextest.presentation.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("src")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view.context).setDefaultRequestOptions(RequestOptions().fitCenter().circleCrop())
            .load(url).into(view)
}