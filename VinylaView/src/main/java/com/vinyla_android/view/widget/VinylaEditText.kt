package com.vinyla_android.view.widget

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.updatePadding
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.vinyla_android.view.R
import com.vinyla_android.view.databinding.WidgetVinylaEditTextBinding

/**
 * Created By Malibin
 * on 5월 08, 2021
 */

class VinylaEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetVinylaEditTextBinding.inflate(LayoutInflater.from(context), this)

    var text: String
        get() = binding.textInput.text.toString()
        set(value) {
            binding.textInput.setText(value)
        }

    var isRequired: Boolean = true
        set(value) {
            binding.viewRequire.visibility = if (value) View.VISIBLE else View.GONE
            field = value
        }

    var isOptional: Boolean = false
        set(value) {
            binding.textOptional.visibility = if (value) View.VISIBLE else View.GONE
            field = value
        }

    var title: String = ""
        set(value) {
            binding.textTitle.text = value.also { field = it }
        }

    var hint: String = ""
        set(value) {
            binding.textInput.hint = value.also { field = it }
        }

    var inputVisibility: Int = View.VISIBLE
        set(value) {
            binding.textInput.visibility = value.also { field = it }
        }

    var minInputHeight: Float = 0f
        set(value) {
            binding.textInput.minHeight = value.toInt()
            field = value
        }

    var inputTextSize: Float = 0f
        set(value) {
            binding.textInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
            field = value
        }

    var inputTextPaddingTop: Float = 0f
        set(value) {
            binding.textInput.updatePadding(top = value.toInt())
            field = value
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.VinylaEditText) { applyAttributes(this) }
    }

    private fun applyAttributes(typedArray: TypedArray) {
        isRequired = typedArray.getBoolean(R.styleable.VinylaEditText_isRequired, true)
        isOptional = typedArray.getBoolean(R.styleable.VinylaEditText_isOptional, false)
        title = typedArray.getString(R.styleable.VinylaEditText_title).orEmpty()
        hint = typedArray.getString(R.styleable.VinylaEditText_hint).orEmpty()
        inputVisibility =
            typedArray.getInt(R.styleable.VinylaEditText_inputVisibility, View.VISIBLE)
        minInputHeight = typedArray.getDimension(R.styleable.VinylaEditText_minInputHeight, 0f)
        inputTextSize = typedArray.getDimensionPixelSize(
            R.styleable.VinylaEditText_inputTextSize,
            resources.getDimensionPixelSize(R.dimen.default_vinyla_text_size)
        ).toFloat()
        inputTextPaddingTop = typedArray.getDimension(
            R.styleable.VinylaEditText_inputTextPaddingTop,
            resources.getDimension(R.dimen.default_vinyla_text_padding_top)
        )
    }

    fun addTextChangedListener(listener: (text: Editable?) -> Unit) {
        binding.textInput.addTextChangedListener { listener(it) }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("inputText")
        fun bindingVinylaEditText(view: VinylaEditText, text: String?) {
            if (view.text != text) {
                view.text = text.orEmpty()
            }
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "inputText", event = "inputTextAttrChanged")
        fun getInputText(view: VinylaEditText): String {
            return view.text
        }

        @JvmStatic
        @BindingAdapter("inputTextAttrChanged")
        fun setInputTextInverseListener(
            view: VinylaEditText,
            listener: InverseBindingListener
        ) {
            view.addTextChangedListener { listener.onChange() }
        }
    }
}
