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
import com.vinyla_android.view.databinding.WidgetVinylViewBinding

/**
 * Created By Malibin
 * on 7월 11, 2021
 */

class VinylView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetVinylViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        context.withStyledAttributes(attrs, R.styleable.VinylView) { applyAttributes(this) }
    }

    private fun applyAttributes(typedArray: TypedArray) {
        typedArray.getString(R.styleable.VinylView_vinylImageUrl).also { setVinylImageUrl(it) }
    }

    fun setVinylImageUrl(imageUrl: String?){
        if (imageUrl == null) {
            binding.imageVinylCover.setImageResource(R.drawable.circle_black)
            return
        }
        Glide.with(this)
            .load(imageUrl)
            .transform(CircleCrop())
            .into(binding.imageVinylCover)
    }

//    companion object {
//        @JvmStatic
//        @BindingAdapter("vinylImageUrl")
//        fun bindingVinylImage(vinylView: VinylView) {
//        }
//    }
}
