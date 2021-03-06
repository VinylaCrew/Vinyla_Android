package com.vinyla_android.presentation.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView)
        .load(imageUrl ?: return)
        .into(imageView)
}

@BindingAdapter("app:imageResId")
fun bindImageResourceId(imageView: ImageView, @DrawableRes imageResId: Int?) {
    imageView.setImageResource(imageResId ?: return)
}
