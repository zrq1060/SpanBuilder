package com.zrq.spanbuilder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Layout
import android.text.SpannableString
import android.text.Spanned.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.widget.TextView

/**
 * 描述: Span构建者，内部封装了常用的样式
 *
 *
 * 邮箱:zrq1060@163.com
 *
 * @author zhangrq
 * 2016/9/30 11:50
 */
class Span(source: CharSequence?) : SpannableString(source ?: "") {
    /**
     * 设置字体大小
     *
     * [size] 设置字体大小（单位sp）
     */
    fun setTextSize(size: Int): Span {
        setOneSpanAll(AbsoluteSizeSpan(size, true))
        return this
    }

    /**
     * 设置字体大小
     *
     * [size] 设置字体大小（单位px）
     */
    fun setTextSizePx(size: Int): Span {
        setOneSpanAll(AbsoluteSizeSpan(size, false))
        return this
    }

    /**
     * 设置字体颜色
     *
     * [color] 字体颜色
     */
    fun setTextColor(color: Int): Span {
        setOneSpanAll(ForegroundColorSpan(color))
        return this
    }

    /**
     * 设置背景颜色
     *
     * [color] 背景颜色
     */
    fun setBackgroundColor(color: Int): Span {
        setOneSpanAll(BackgroundColorSpan(color))
        return this
    }

    /**
     * 设置字体类型
     *
     * [style] 字体类型 [android.graphics.Typeface] NORMAL：正常，BOLD：粗体，ITALIC：斜体，BOLD_ITALIC：粗斜体
     */
    fun setTextStyle(style: Int): Span {
        setOneSpanAll(StyleSpan(style))
        return this
    }

    /**
     * 设置自定义的字体类型
     *
     * [typeface] 设置字体类型
     */
    fun setTypeface(typeface: Typeface): Span {
        setOneSpanAll(CustomTypefaceSpan(typeface))
        return this
    }

    /**
     * 设置字体style样式的外观
     *
     * [styleId] 字体style样式的id
     */
    fun setTextAppearance(context: Context, styleId: Int): Span {
        setOneSpanAll(TextAppearanceSpan(context, styleId))
        return this
    }

    /**
     * 设置系统自带字体类型
     *
     * [family] 设置字体 "monospace", "serif", and "sans-serif"等
     */
    fun setFontFamily(family: String?): Span {
        setOneSpanAll(TypefaceSpan(family))
        return this
    }

    /**
     * 设置点击
     * （默认是有下划线和字体颜色的，如需更改，则覆盖clickableSpan对象的updateDrawState方法 ）设置如下：<br></br>
     * public void updateDrawState(TextPaint ds) {<br></br>
     * ds.setColor(ds.linkColor); <br></br>
     * ds.setUnderlineText(false); <br></br>
     * }
     *
     * [textView]      设置可点击的textView
     * [clickableSpan] 点击的span
     */
    fun setClick(textView: TextView?, clickableSpan: ClickableSpan?): Span {
        if (textView != null) {
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.highlightColor = Color.TRANSPARENT
        }
        setOneSpanAll(clickableSpan)
        return this
    }

    /**
     * 设置删除线的样式
     */
    fun setStrikeThrough(): Span {
        setOneSpanAll(StrikethroughSpan())
        return this
    }

    /**
     * 设置下划线的样式
     */
    fun setUnderLine(): Span {
        setOneSpanAll(UnderlineSpan())
        return this
    }

    /**
     * 设置垂直的引用线
     *
     * [color] 设置垂直的引用线的颜色
     */
    fun setQuote(color: Int): Span {
        setOneSpanAll(QuoteSpan(color))
        return this
    }

    /**
     * 设置字体内容的对其方式
     *
     * [align] 设置对其方式    ALIGN_NORMAL,    ALIGN_OPPOSITE,    ALIGN_CENTER,
     */
    fun setAlignment(align: Layout.Alignment): Span {
        setOneSpanAll(AlignmentSpan.Standard(align))
        return this
    }

    /**
     * 设置字体的相对大小
     *
     * [proportion] 设置相对textSize的比例大小
     */
    fun setRelativeSize(proportion: Float): Span {
        setOneSpanAll(RelativeSizeSpan(proportion))
        return this
    }

    /**
     * 设置字体为上标的样式
     */
    fun setSuperscript(): Span {
        setOneSpanAll(SuperscriptSpan())
        return this
    }

    /**
     * 设置字体为下标的样式
     */
    fun setSubscript(): Span {
        setOneSpanAll(SubscriptSpan())
        return this
    }

    /**
     * 设置字体X轴缩放
     *
     * [proportion] 缩放的倍数
     */
    fun setScaleX(proportion: Float): Span {
        setOneSpanAll(ScaleXSpan(proportion))
        return this
    }

    /**
     * 设置URL，点击会打开浏览器展示
     *
     * [urlSpan] 如需修改字体颜色等，请参考[setClick]方法
     */
    fun setURL(textView: TextView?, urlSpan: URLSpan): Span {
        if (textView != null) {
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.highlightColor = Color.TRANSPARENT
        }
        setOneSpanAll(urlSpan)
        return this
    }

    /**
     * 设置图片-Drawable，不需要设置setBounds，不会替换内容
     *
     * [drawable] 图片
     * [pad] 距离
     */
    @JvmOverloads
    fun setDrawableMargin(drawable: Drawable, pad: Int = 0): Span {
        setOneSpanAll(DrawableMarginSpan(drawable, pad))
        return this
    }

    /**
     * 设置图片-Bitmap，不会替换内容
     *
     * [bitmap] 图片
     * [pad] 距离
     */
    @JvmOverloads
    fun setIconMargin(bitmap: Bitmap, pad: Int = 0): Span {
        setOneSpanAll(IconMarginSpan(bitmap, pad))
        return this
    }

    /**
     * 设置图片-Drawable，需要设置setBounds，会替换内容
     *
     * [dynamicDrawableSpan] 提供Drawable的span
     */
    fun setDynamicDrawable(dynamicDrawableSpan: DynamicDrawableSpan): Span {
        setOneSpanAll(dynamicDrawableSpan)
        return this
    }

    /**
     * 设置图片-Bitmap，会替换内容
     *
     * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
     */
    @JvmOverloads
    fun setImage(context: Context, bitmap: Bitmap, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Span {
        setOneSpanAll(ImageSpan(context, bitmap, verticalAlignment))
        return this
    }

    /**
     * 设置图片-Drawable，会替换内容。Drawable需设置setBounds。
     *
     * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
     */
    @JvmOverloads
    fun setImage(drawable: Drawable, source: String? = null, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Span {
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        setOneSpanAll(ImageSpan(drawable, source, verticalAlignment))
        return this
    }

    /**
     * 解决上述方法JvmOverloads后无此参数情况
     */
    fun setImage(drawable: Drawable, verticalAlignment: Int) = setImage(drawable, null, verticalAlignment)

    /**
     * 设置图片-Uri，会替换内容。
     *
     * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
     */
    @JvmOverloads
    fun setImage(context: Context, uri: Uri, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Span {
        setOneSpanAll(ImageSpan(context, uri, verticalAlignment))
        return this
    }

    /**
     * 设置图片-resourceId，会替换内容。
     *
     * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
     */
    @JvmOverloads
    fun setImage(context: Context, resourceId: Int, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Span {
        setOneSpanAll(ImageSpan(context, resourceId, verticalAlignment))
        return this
    }

    /**
     * 设置子弹
     */
    @JvmOverloads
    fun setBullet(gapWidth: Int = BulletSpan.STANDARD_GAP_WIDTH, color: Int? = null): Span {
        setOneSpanAll(if (color == null) BulletSpan(gapWidth) else BulletSpan(gapWidth, color))
        return this
    }

    /**
     * 设置行缩进
     * [first] 段落第一行的缩进距离
     * [rest] 段落其余行缩进距离
     */
    @JvmOverloads
    fun setLeadingMargin(first: Int, rest: Int = first): Span {
        setOneSpanAll(LeadingMarginSpan.Standard(first, rest))
        return this
    }

    /**
     * 设置一行上制表符位置
     * [where] 制表符与行前距离的偏移量
     */
    fun setTabStop(where: Int): Span {
        setOneSpanAll(TabStopSpan.Standard(where))
        return this
    }

    /**
     * 设置模糊
     * [radius] 模糊的半径
     * [style] 模糊的样式
     */
    fun setBlurMask(radius: Float, style: BlurMaskFilter.Blur): Span {
        setOneSpanAll(MaskFilterSpan(BlurMaskFilter(radius, style)))
        return this
    }

    /**
     * 设置一个样式-作用于text全部
     * [span] 一个span样式
     */
    private fun setOneSpanAll(span: Any?): Span {
        return setSpanPart(0, length, SPAN_EXCLUSIVE_EXCLUSIVE, span)
    }

    /**
     * 设置样式-作用于text全部
     * [flags] 默认：[SPAN_EXCLUSIVE_EXCLUSIVE]。常用：[SPAN_INCLUSIVE_INCLUSIVE]、[SPAN_EXCLUSIVE_EXCLUSIVE]、[SPAN_INCLUSIVE_EXCLUSIVE]、[SPAN_EXCLUSIVE_INCLUSIVE]，详情请看：https://www.jianshu.com/p/1956e15c9a27
     * [spans] 多个span样式
     */
    @JvmOverloads
    fun setSpanAll(flags: Int = SPAN_EXCLUSIVE_EXCLUSIVE, vararg spans: Any?): Span {
        return setSpanPart(0, length, flags, *spans)
    }

    /**
     * 设置样式-作用于text文本start到end的位置
     *
     * [start] 从此位置开始设置样式
     * [end] 从此位置结束设置样式
     * [flags] 默认：[SPAN_EXCLUSIVE_EXCLUSIVE]。常用：[SPAN_INCLUSIVE_INCLUSIVE]、[SPAN_EXCLUSIVE_EXCLUSIVE]、[SPAN_INCLUSIVE_EXCLUSIVE]、[SPAN_EXCLUSIVE_INCLUSIVE]，详情请看：https://www.jianshu.com/p/1956e15c9a27
     * [spans] 多个span样式
     */
    @JvmOverloads
    fun setSpanPart(start: Int, end: Int, flags: Int = SPAN_EXCLUSIVE_EXCLUSIVE, vararg spans: Any?): Span {
        if (start > end || spans.isEmpty()) return this
        for (span in spans) {
            if (span == null) continue
            super.setSpan(span, start, end, flags)
        }
        return this
    }
}