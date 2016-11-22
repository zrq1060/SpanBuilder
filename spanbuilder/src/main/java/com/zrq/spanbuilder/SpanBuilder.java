package com.zrq.spanbuilder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;


/**
 * 描述: Span构建者，内部封装了常用的样式
 * <p>
 * 邮箱:zrq1060@163.com
 *
 * @author zhangrq
 *         2016/9/30 11:50
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SpanBuilder extends SpannableString {

    /**
     * @param source 设置的内容，作用于所有
     */
    public SpanBuilder(CharSequence source) {
        super(source);
    }

    /**
     * @param source    设置的内容，作用于所有
     * @param textSize  设置字体大小，单位sp
     * @param textColor 设置字体颜色
     */
    public SpanBuilder(CharSequence source, int textSize, int textColor) {
        super(source);
        setTextSize(textSize);
        setTextColor(textColor);
    }

    public SpanBuilder() {
        super("");
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 绝对大小 </font>的元素
     *
     * @param size 设置字体大小（单位sp）
     */
    public SpanBuilder setTextSize(int size) {
        setSpanAll(new AbsoluteSizeSpan(size, true));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 字体颜色 </font>的元素
     *
     * @param color 字体颜色
     */
    public SpanBuilder setTextColor(int color) {
        setSpanAll(new ForegroundColorSpan(color));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 背景颜色 </font>的元素
     *
     * @param color 设置背景颜色
     */
    public SpanBuilder setBackgroundColor(int color) {
        setSpanAll(new BackgroundColorSpan(color));
        return this;
    }

    /**
     * 已过时
     * @see SpanBuilder#setTextStyle(int)
     */
    @Deprecated
    public SpanBuilder setTypeface(int style) {
        return setTextStyle(style);
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 字体类型 </font>的元素
     *
     * @param style 设置字体类型    粗斜体：BOLD_ITALIC，粗体：BOLD，斜体：ITALIC，正常：NORMAL
     */
    public SpanBuilder setTextStyle(int style) {
        setSpanAll(new StyleSpan(style));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 自定义的字体类型 </font>的元素
     *
     * @param typeface 设置字体类型
     */
    public SpanBuilder setTypeface(Typeface typeface) {
        setSpanAll(new CustomTypefaceSpan(typeface));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 字体外观 </font>的元素
     *
     * @param styleId 设置style样式的id
     */
    public SpanBuilder setTextAppearance(Context context, int styleId) {
        setSpanAll(new TextAppearanceSpan(context, styleId));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 点击 </font>的元素（默认是有下划线和字体颜色的，如需更改，<br/>
     * 则覆盖clickableSpan对象的updateDrawState方法 ）设置如下：<br/>
     * public void updateDrawState(TextPaint ds) {<br/>
     * ds.setColor(ds.linkColor); <br/>
     * ds.setUnderlineText(false);} <br/>
     *
     * @param textView      设置可点击的textView
     * @param clickableSpan 点击的span
     */
    public SpanBuilder setClick(TextView textView, ClickableSpan clickableSpan) {
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setHighlightColor(Color.TRANSPARENT);
        }
        setSpanAll(clickableSpan);
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 删除线 </font>的元素
     */
    public SpanBuilder setDeleteLine() {
        setSpanAll(new StrikethroughSpan());
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 下划线 </font>的元素
     */
    public SpanBuilder setUnderLine() {
        setSpanAll(new UnderlineSpan());
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 图片 </font>的元素
     *
     * @param drawable 设置drawable
     */
    public SpanBuilder setImage(Drawable drawable) {
        setSpanAll(new ImageSpan(drawable));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 字体类型 </font>的元素
     *
     * @param family 设置字体类型 "monospace", "serif", and "sans-serif"等
     */
    public SpanBuilder setFontFamily(String family) {
        setSpanAll(new TypefaceSpan(family));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 垂直的引用线 </font>的元素
     *
     * @param color 设置垂直的引用线的颜色
     */
    public SpanBuilder setQuote(int color) {
        setSpanAll(new QuoteSpan(color));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 对其方式 </font>的元素
     *
     * @param align 设置对其方式    ALIGN_NORMAL,    ALIGN_OPPOSITE,    ALIGN_CENTER,
     */
    public SpanBuilder setAlignment(Layout.Alignment align) {
        setSpanAll(new AlignmentSpan.Standard(align));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 相对大小 </font>的元素
     *
     * @param proportion 设置相对textSize的比例大小
     */
    public SpanBuilder setRelativeSize(float proportion) {
        setSpanAll(new RelativeSizeSpan(proportion));
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 上标 </font>的元素
     */
    public SpanBuilder setUpLabel() {
        setSpanAll(new SuperscriptSpan());
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> 下标 </font>的元素
     */
    public SpanBuilder setUnderLabel() {
        setSpanAll(new SubscriptSpan());
        return this;
    }

    /**
     * 设置带有<font color="#ff0000" size="4"> X轴缩放 </font>的元素
     *
     * @param proportion 缩放的倍数
     */
    public SpanBuilder setScaleX(float proportion) {
        setSpanAll(new ScaleXSpan(proportion));
        return this;
    }


    /**
     * 设置样式-作用于text全部 详情看setSpanPart方法
     */
    public SpanBuilder setSpanAll(Object... spans) {
        return setSpanPart(0, length(), spans);
    }

    /**
     * 设置样式-作用于text文本start到end的位置
     *
     * @param start 从此位置开始设置样式
     * @param end   从此位置结束设置样式
     * @param spans 样式如下：（如此参数为空或无，则此内容不设置样式）<br/>
     *              ----------------------------------本类封装的Span------------------------- <br/>
     *              AlignmentSpan.Standard 对齐方式 <br/>
     *              StyleSpan 字体样式：粗体、斜体等 <br/>
     *              UnderlineSpan 下划线 <br/>
     *              RelativeSizeSpan 相对大小（相对于之前字体的多少倍） <br/>
     *              AbsoluteSizeSpan 绝对大小（文本字体） <br/>
     *              ScaleXSpan 基于x轴缩放 <br/>
     *              TypefaceSpan 文本字体类型（ "monospace", "serif", and "sans-serif".） <br/>
     *              QuoteSpan 影响段落层次的文本格式。它可以给一个段落加上垂直的引用线。 <br/>
     *              StrikethroughSpan 删除线（中划线） <br/>
     *              SuperscriptSpan 上标（数学公式会用到） <br/>
     *              SubscriptSpan 下标（数学公式会用到） <br/>
     *              TextAppearanceSpan 文本外貌，设置为styleId样式（包括字体、大小、样式和颜色） <br/>
     *              ForegroundColorSpan 字体颜色（前景色） <br/>
     *              ClickableSpan （子类：URLSpan） 文本可点击，有点击事件，但是会调用TextView的点击事件 <br/>
     *              ImageSpan 放置一张图片 <br/>
     *              <p>
     *              ---------------------------------本类未封装的Span------------------------- <br/>
     *              NoCopySpan <br/>
     *              ParcelableSpan <br/>
     *              SuggestionSpan <br/>
     *              TtsSpan <br/>
     *              URLSpan <br/>
     *              BulletSpan <br/>
     *              BackgroundColorSpan <br/>
     *              LeadingMarginSpan.Standard <br/>
     *              EasyEditSpan <br/>
     *              LocaleSpan <br/>
     *              Annotation <br/>
     *              SpellCheckSpan <br/>
     *              SuggestionRangeSpan <br/>
     *              LineBackgroundSpan <br/>
     *              MaskFilterSpan <br/>
     *              RasterizerSpan <br/>
     *              TabStopSpan <br/>
     *              WrapTogetherSpan <br/>
     *              MetricAffectingSpan <br/>
     *              ReplacementSpan <br/>
     *              DynamicDrawableSpan <br/>
     *              ReplacementDrawableSpan   <br/>
     *              LineHeightSpan <br/>
     *              IconMarginSpan <br/>
     */
    public SpanBuilder setSpanPart(int start, int end, Object... spans) {
        if (start > end || spans == null
                || spans.length == 0)
            return this;
        for (Object span : spans) {
            if (span == null)
                continue;
            super.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return this;
    }

    /**
     * 给原有样式增加（替换）新样式,7.0覆盖字体上发现问题，慎用
     *
     * @param start        作用于text文本的开始位置
     * @param end          作用于text文本的结束位置
     * @param newSpanStyle 新文本样式的集合体用SpanBuilder承载
     */
    public void addNewSpanStyle(int start, int end, SpanBuilder newSpanStyle) {
        if (start > end || newSpanStyle == null)
            return;
        Object[] spans = newSpanStyle.getSpans(0, newSpanStyle.length(), Object.class);
        if (spans == null || spans.length == 0)
            return;

        for (Object span : spans) {
            if (span == null)
                continue;
            super.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 移除某一类型的样式
     *
     * @param kind 移除哪一类型的span
     */
    private void removeOldSpan(Class kind) {
        Object[] spans = this.getSpans(0, length(), kind);
        if (spans == null || spans.length == 0)
            return;
        for (Object span : spans) {
            if (span == null)
                continue;
            super.removeSpan(span);
        }
    }
}
