package com.vinyla_android.presentation.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.vinyla_android.R
import com.vinyla_android.domain.entity.member.nickname.NicknameState

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


@BindingAdapter("app:bindNicknameState")
fun TextView.bindNicknameState(nicknameState: NicknameState?) = when (nicknameState) {
    NicknameState.UNCHECKED, null -> {
        setTextColor(resources.getColor(R.color.gray88, context.theme))
        setText(R.string.nickname_cannot_modify)
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }
    NicknameState.AVAILABLE -> {
        setTextColor(resources.getColor(R.color.mainColor, context.theme))
        setText(R.string.nickname_available)
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.icn_login_complete, 0, 0, 0)
    }
    NicknameState.DUPLICATED -> {
        setTextColor(resources.getColor(R.color.purple, context.theme))
        setText(R.string.nickname_duplicated)
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.icn_login_fail, 0, 0, 0)
    }
    NicknameState.INVALID -> {
        setTextColor(resources.getColor(R.color.purple, context.theme))
        setText(R.string.nickname_invalid)
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.icn_login_fail, 0, 0, 0)
    }
}
