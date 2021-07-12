package com.vinyla_android.view.transformation

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * Created By Malibin
 * on 7월 12, 2021
 */

class BlurImage @JvmOverloads constructor(
    private val context: Context,
    private val radius: Double = MAX_RADIUS,
    private val sampling: Int = DEFAULT_DOWN_SAMPLING,
) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("${ID}_${radius}_${sampling}".toByteArray(CHARSET))
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val rs = RenderScript.create(context)
        val inputAllocation = createInputAllocation(rs, toTransform)
        val outputAllocation = Allocation.createTyped(rs, inputAllocation.type)
        val scriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)).apply {
            setInput(inputAllocation)
            setRadius(radius.toFloat())
            forEach(outputAllocation)
        }
        outputAllocation.copyTo(toTransform)

        RenderScript.releaseAllContexts()
        inputAllocation.destroy()
        outputAllocation.destroy()
        scriptIntrinsicBlur.destroy()
        return toTransform
    }

    private fun createInputAllocation(renderScript: RenderScript, toTransform: Bitmap): Allocation {
        return Allocation.createFromBitmap(
            renderScript,
            toTransform,
            Allocation.MipmapControl.MIPMAP_NONE,
            Allocation.USAGE_SCRIPT
        )
    }

    companion object {
        private const val VERSION = 1
        private const val ID = "com.vinyla_android.view.transformation.BlurImage.$VERSION"

        private const val MAX_RADIUS = 25.0
        private const val DEFAULT_DOWN_SAMPLING = 1
    }
}
