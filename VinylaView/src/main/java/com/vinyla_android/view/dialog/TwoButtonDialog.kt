package com.vinyla_android.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.vinyla_android.view.R
import com.vinyla_android.view.databinding.DialogTwoButtonBinding

abstract class TwoButtonDialog : DialogFragment() {

    abstract val contentsTextResId: Int
    abstract val leftButtonTextResId: Int
    abstract val rightButtonTextResId: Int

    open val leftButtonTextColorResId: Int = R.color.grayDD
    open val rightButtonTextColorResId: Int = R.color.mainColor

    private var binding: DialogTwoButtonBinding? = null
    private var onButtonClickListener: OnButtonClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogTwoButtonBinding.inflate(layoutInflater).also { binding = it }
        initView(binding)
        return binding.root
    }

    private fun initView(binding: DialogTwoButtonBinding) {
        setBackgroundTransparent()
        binding.textContents.setText(contentsTextResId)

        binding.buttonLeft.setOnClickListener { onButtonClickListener?.onLeftButtonClick(this) }
        binding.buttonLeft.setText(leftButtonTextResId)
        binding.buttonLeft.setTextColor(
            ContextCompat.getColor(requireContext(), leftButtonTextColorResId)
        )

        binding.buttonRight.setOnClickListener { onButtonClickListener?.onRightButtonClick(this) }
        binding.buttonRight.setText(rightButtonTextResId)
        binding.buttonRight.setTextColor(
            ContextCompat.getColor(requireContext(), rightButtonTextColorResId)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setBackgroundTransparent() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setOnButtonClickListener(onButtonClickListener: OnButtonClickListener?): TwoButtonDialog {
        this.onButtonClickListener = onButtonClickListener
        return this
    }

    protected fun requireBinding(): DialogTwoButtonBinding {
        return binding ?: error("binding is not inflated yet")
    }

    interface OnButtonClickListener {
        fun onLeftButtonClick(dialog: TwoButtonDialog)

        fun onRightButtonClick(dialog: TwoButtonDialog)
    }
}
