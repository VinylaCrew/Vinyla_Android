package com.vinyla_android.view.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.vinyla_android.view.R
import com.vinyla_android.view.databinding.WidgetVinylDisplayViewBinding
import com.vinyla_android.view.transformation.BlurImage

/**
 * Created By Malibin
 * on 7월 13, 2021
 */

class VinylDisplayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetVinylDisplayViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        context.withStyledAttributes(attrs, R.styleable.VinylDisplayView) { applyAttributes(this) }
    }

    private fun applyAttributes(typedArray: TypedArray) {
        typedArray.getString(R.styleable.VinylDisplayView_vinylImageUrl)
            .also { setVinylImageUrl(it) }
    }

    fun setVinylImageUrl(imageUrl: String?) {
        if (imageUrl == null) return
        putCircleImage(imageUrl)
        blurBackgroundImage(imageUrl)
    }

    private fun putCircleImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .transform(CircleCrop())
            .into(binding.imageMainVinyl)
    }

    private fun blurBackgroundImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .transform(BlurImage(context))
            .into(binding.imageBlurBackground)
    }
}
