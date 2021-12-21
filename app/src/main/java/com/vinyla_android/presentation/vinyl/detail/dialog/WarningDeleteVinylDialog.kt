package com.vinyla_android.presentation.vinyl.detail.dialog

import com.vinyla_android.R
import com.vinyla_android.view.dialog.TwoButtonDialog

class WarningDeleteVinylDialog : TwoButtonDialog() {
    override val contentsTextResId: Int = R.string.warning_delete_vinyl
    override val leftButtonTextResId: Int = R.string.cancel
    override val rightButtonTextResId: Int = R.string.delete

    override val rightButtonTextColorResId: Int = R.color.purple
}
