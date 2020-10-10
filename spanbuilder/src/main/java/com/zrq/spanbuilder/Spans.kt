package com.zrq.spanbuilder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Layout
import android.text.SpannableString.*
import android.text.SpannableStringBuilder
import android.text.style.*
import android.widget.TextView

/**
 * 描述:
 *
 * @author zhangrq
 * 2016/11/5 21:14
 */
class Spans : SpannableStringBuilder() {

    /**
     * 构建者模式拼接Spans
     */
    class Builder {
        private var span = Span("")
        private var spans = Spans()

        /**
         * 设置文本内容
         * [text] 文本
         */
        fun text(text: CharSequence?): Builder {
            // 新文本起始设置，拼接上一个，创建新的Span
            appendOld() // 拼接上一个
            span = Span(text)
            return this
        }

        /**
         * 生成拼接好的Spans
         */
        fun build(): Spans {
            // 文本结束设置，拼接上一个，并返回结果
            appendOld() // 拼接上一个
            return spans
        }

        /**
         * 拼接上一个span
         */
        private fun appendOld() {
            if (span.length != 0) {
                // 无内容即无样式，不增加；有内容，增加，增加的是前一个的样式
                spans.append(span)
            }
        }

        /**
         * 设置字体大小
         *
         * [textSize] 设置字体大小（单位sp）
         */
        fun size(textSize: Int): Builder {
            span.setTextSize(textSize)
            return this
        }

        /**
         * 设置字体大小
         *
         * [textSize] 设置字体大小（单位Px）
         */
        fun sizePx(textSize: Int): Builder {
            span.setTextSizePx(textSize)
            return this
        }

        /**
         * 设置字体颜色
         *
         * [textColor] 字体颜色
         */
        fun color(textColor: Int): Builder {
            span.setTextColor(textColor)
            return this
        }

        /**
         * 设置背景颜色
         *
         * [color] 背景颜色
         */
        fun backgroundColor(color: Int): Builder {
            span.setBackgroundColor(color)
            return this
        }

        /**
         * 设置字体类型
         *
         * [style] 字体类型 [android.graphics.Typeface] NORMAL：正常，BOLD：粗体，ITALIC：斜体，BOLD_ITALIC：粗斜体
         */
        fun style(style: Int): Builder {
            span.setTextStyle(style)
            return this
        }

        /**
         * 设置自定义的字体类型
         *
         * [typeface] 设置字体类型
         */
        fun typeface(typeface: Typeface): Builder {
            span.setTypeface(typeface)
            return this
        }

        /**
         * 设置字体style样式的外观
         *
         * [styleId] 字体style样式的id
         */
        fun appearance(context: Context, styleId: Int): Builder {
            span.setTextAppearance(context, styleId)
            return this
        }

        /**
         * 设置系统自带字体类型
         *
         * [family] 设置字体 "monospace", "serif", and "sans-serif"等
         */
        fun fontFamily(family: String?): Builder {
            span.setFontFamily(family)
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
        fun click(textView: TextView?, clickableSpan: ClickableSpan?): Builder {
            span.setClick(textView, clickableSpan)
            return this
        }

        /**
         * 设置删除线的样式
         */
        fun strikeThrough(): Builder {
            span.setStrikeThrough()
            return this
        }

        /**
         * 设置下划线的样式
         */
        fun underLine(): Builder {
            span.setUnderLine()
            return this
        }

        /**
         * 设置垂直的引用线
         *
         * [color] 设置垂直的引用线的颜色
         */
        fun quote(color: Int): Builder {
            span.setQuote(color)
            return this
        }

        /**
         * 设置字体内容的对其方式
         *
         * [align] 设置对其方式    ALIGN_NORMAL,    ALIGN_OPPOSITE,    ALIGN_CENTER,
         */
        fun alignment(align: Layout.Alignment): Builder {
            span.setAlignment(align)
            return this
        }

        /**
         * 设置字体的相对大小
         *
         * [proportion] 设置相对textSize的比例大小
         */
        fun relativeSize(proportion: Float): Builder {
            span.setRelativeSize(proportion)
            return this
        }

        /**
         * 设置字体为上标的样式
         */
        fun superscript(): Builder {
            span.setSuperscript()
            return this
        }

        /**
         * 设置字体为下标的样式
         */
        fun subscript(): Builder {
            span.setSubscript()
            return this
        }

        /**
         * 设置字体X轴缩放
         *
         * [proportion] 缩放的倍数
         */
        fun scaleX(proportion: Float): Builder {
            span.setScaleX(proportion)
            return this
        }

        /**
         * 设置URL，点击会打开浏览器展示
         *
         * [urlSpan] 如需修改字体颜色等，请参考[click]方法
         */
        fun url(textView: TextView?, urlSpan: URLSpan): Builder {
            span.setURL(textView,urlSpan)
            return this
        }

        /**
         * 设置图片-Drawable，不需要设置setBounds，不会替换内容
         *
         * [drawable] 图片
         * [pad] 距离
         */
        @JvmOverloads
        fun drawableMargin(drawable: Drawable, pad: Int = 0): Builder {
            span.setDrawableMargin(drawable, pad)
            return this
        }

        /**
         * 设置图片-Bitmap，不会替换内容
         *
         * [bitmap] 图片
         * [pad] 距离
         */
        @JvmOverloads
        fun iconMargin(bitmap: Bitmap, pad: Int = 0): Builder {
            span.setIconMargin(bitmap, pad)
            return this
        }

        /**
         * 设置图片-Drawable，需要设置setBounds，会替换内容
         *
         * [dynamicDrawableSpan] 提供Drawable的span
         */
        fun dynamicDrawable(dynamicDrawableSpan: DynamicDrawableSpan): Builder {
            span.setDynamicDrawable(dynamicDrawableSpan)
            return this
        }

        /**
         * 设置图片-Bitmap，会替换内容
         *
         * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
         */
        @JvmOverloads
        fun image(context: Context, bitmap: Bitmap, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Builder {
            span.setImage(context, bitmap, verticalAlignment)
            return this
        }

        /**
         * 设置图片-Drawable，会替换内容。Drawable需设置setBounds。
         *
         * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
         */
        @JvmOverloads
        fun image(drawable: Drawable, source: String? = null, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Builder {
            span.setImage(drawable, source, verticalAlignment)
            return this
        }

        /**
         * 解决上述方法JvmOverloads后无此参数情况
         */
        fun image(drawable: Drawable, verticalAlignment: Int) = image(drawable, null, verticalAlignment)

        /**
         * 设置图片-Uri，会替换内容。
         *
         * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
         */
        @JvmOverloads
        fun image(context: Context, uri: Uri, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Builder {
            span.setImage(context, uri, verticalAlignment)
            return this
        }

        /**
         * 设置图片-resourceId，会替换内容。
         *
         * [verticalAlignment] 垂直对齐方式：ALIGN_BOTTOM、ALIGN_BASELINE
         */
        @JvmOverloads
        fun image(context: Context, resourceId: Int, verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM): Builder {
            span.setImage(context, resourceId, verticalAlignment)
            return this
        }

        /**
         * 设置子弹
         *
         */
        @JvmOverloads
        fun bullet(gapWidth: Int = BulletSpan.STANDARD_GAP_WIDTH, color: Int? = null): Builder {
            span.setBullet(gapWidth, color)
            return this
        }

        /**
         * 设置行缩进
         * [first] 段落第一行的缩进距离
         * [rest] 段落其余行缩进距离
         */
        @JvmOverloads
        fun leadingMargin(first: Int, rest: Int = first): Builder {
            span.setLeadingMargin(first, rest)
            return this
        }

        /**
         * 设置一行上制表符位置
         * [where] 制表符与行前距离的偏移量
         */
        fun tabStop(where: Int): Builder {
            span.setTabStop(where)
            return this
        }

        /**
         * 设置模糊
         * [radius] 模糊的半径
         * [style] 模糊的样式
         */
        fun blurMask(radius: Float, style: BlurMaskFilter.Blur): Builder {
            span.setBlurMask(radius, style)
            return this
        }

        /**
         * 设置样式-作用于text全部
         * [flags] 默认：[SPAN_EXCLUSIVE_EXCLUSIVE]。常用：[SPAN_INCLUSIVE_INCLUSIVE]、[SPAN_EXCLUSIVE_EXCLUSIVE]、[SPAN_INCLUSIVE_EXCLUSIVE]、[SPAN_EXCLUSIVE_INCLUSIVE]，详情请看：https://www.jianshu.com/p/1956e15c9a27
         * [spans] 多个span样式
         */
        @JvmOverloads
        fun setSpanAll(flags: Int = SPAN_EXCLUSIVE_EXCLUSIVE, vararg spans: Any?): Builder {
            span.setSpanAll(flags, *spans)
            return this
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
        fun setSpanPart(start: Int, end: Int, flags: Int = SPAN_EXCLUSIVE_EXCLUSIVE, vararg spans: Any?): Builder {
            span.setSpanPart(start, end, flags, *spans)
            return this
        }
    }

    companion object {
        /**
         * 构建者模式拼接Spans
         */
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }
}