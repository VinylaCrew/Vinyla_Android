package com.vinyla_android.view.widget

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.vinyla_android.view.R
import com.vinyla_android.view.databinding.WidgetInputTextViewBinding

/**
 * Created By Malibin
 * on 5월 08, 2021
 */

class InputTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetInputTextViewBinding.inflate(LayoutInflater.from(context), this)

    var text: String
        get() = binding.textInput.text.toString()
        set(value) {
            binding.textInput.setText(value)
        }

    var isRequired: Boolean = true
        set(value) {
            binding.viewRequire.visibility = if (value) View.VISIBLE else View.INVISIBLE
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

    var minInputHeight: Int = 0
        set(value) {
            binding.textInput.minHeight = value.also { field = it }
        }

    var inputVisibility: Int = View.VISIBLE
        set(value) {
            binding.textInput.visibility = value.also { field = it }
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.InputTextView) { applyAttributes(this) }
    }

    private fun applyAttributes(typedArray: TypedArray) {
        isRequired = typedArray.getBoolean(R.styleable.InputTextView_isRequired, true)
        title = typedArray.getString(R.styleable.InputTextView_title).orEmpty()
        hint = typedArray.getString(R.styleable.InputTextView_hint).orEmpty()
        minInputHeight =
            typedArray.getDimension(R.styleable.InputTextView_minInputHeight, 0f).toInt()
        inputVisibility = typedArray.getInt(R.styleable.InputTextView_inputVisibility, View.VISIBLE)
    }

    fun addTextChangedListener(listener: (text: Editable?) -> Unit) {
        binding.textInput.addTextChangedListener { listener(it) }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("inputText")
        fun bindingInputTextView(view: InputTextView, text: String?) {
            if (view.text != text) {
                view.text = text.orEmpty()
            }
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "inputText", event = "inputTextAttrChanged")
        fun getInputText(view: InputTextView): String {
            return view.text
        }

        @JvmStatic
        @BindingAdapter("inputTextAttrChanged")
        fun setInputTextInverseListener(
            view: InputTextView,
            listener: InverseBindingListener
        ) {
            view.addTextChangedListener { listener.onChange() }
        }
    }
}
