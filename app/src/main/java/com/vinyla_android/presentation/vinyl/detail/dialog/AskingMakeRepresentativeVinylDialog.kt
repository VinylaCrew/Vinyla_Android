package com.vinyla_android.presentation.vinyl.detail.dialog

import com.vinyla_android.R
import com.vinyla_android.view.dialog.TwoButtonDialog

class AskingMakeRepresentativeVinylDialog : TwoButtonDialog() {
    override val contentsTextResId: Int = R.string.asking_make_representative_vinyl
    override val leftButtonTextResId: Int = R.string.cancel
    override val rightButtonTextResId: Int = R.string.register
}
