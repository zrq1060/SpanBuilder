package com.zrq.spanbuilder

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

/**
 * 描述:可设置自定义Typeface的span
 *
 * @author zhangrq
 * 2016/10/13 18:03
 */
class CustomTypefaceSpan(private val typeface: Typeface) : MetricAffectingSpan() {
    override fun updateDrawState(drawState: TextPaint) {
        apply(drawState)
    }

    override fun updateMeasureState(paint: TextPaint) {
        apply(paint)
    }

    private fun apply(paint: Paint) {
        val oldTypeface = paint.typeface
        val oldStyle = oldTypeface?.style ?: 0
        val fakeStyle = oldStyle and typeface.style.inv()
        if (fakeStyle and Typeface.BOLD != 0) {
            paint.isFakeBoldText = true
        }
        if (fakeStyle and Typeface.ITALIC != 0) {
            paint.textSkewX = -0.25f
        }
        paint.typeface = typeface
    }
}