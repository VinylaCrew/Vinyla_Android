package com.vinyla_android.view.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.vinyla_android.view.R
import com.vinyla_android.view.databinding.WidgetTopBarBinding

class TopBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetTopBarBinding.inflate(LayoutInflater.from(context), this)

    var title: String
        get() = binding.textTitle.text.toString()
        set(value) {
            binding.textTitle.text = value
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.TopBar) { applyAttributes(this) }
    }

    private fun applyAttributes(typedArray: TypedArray) {
        title = typedArray.getString(R.styleable.TopBar_title).orEmpty()
    }

    fun setOnBackButtonClickListener(listener: ((View) -> Unit)?) {
        binding.buttonBack.setOnClickListener(listener)
    }
}
