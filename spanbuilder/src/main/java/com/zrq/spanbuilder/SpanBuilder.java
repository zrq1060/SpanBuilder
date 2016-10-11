package com.zrq.spanbuilder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
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
 *
 * 邮箱:zrq1060@163.com
 *
 * @author zhangrq
 *         2016/9/30 11:50
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SpanBuilder extends SpannableStringBuilder {
    /**
     * 根据TextSetting的配置，设置内容
     */
    public SpanBuilder append(TextSetting textSetting) {

        String text = textSetting.getText();
        if (text != null) {// 文本为空不设置，不为空后再进行设置
            int textColor = textSetting.getTextColor();
            int textSize = textSetting.getTextSize();
            SpannableString msp = new SpannableString(text);
            // 字体大小
            if (textSize != -1) {// 字体大小有设置，设置字体大小
                msp.setSpan(new AbsoluteSizeSpan(textSize, true),
                        0, text.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            // 字体颜色
            if (textColor != -1) {// 字体颜色有设置，设置字体颜色
                msp.setSpan(new ForegroundColorSpan(textColor), 0,
                        text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            this.append(msp);
        }
        return this;
    }

    @Override
    public SpanBuilder append(CharSequence text) {
        super.append(text);
        return this;
    }

    @Override
    public SpanBuilder append(char text) {
        super.append(text);
        return this;
    }

    @Override
    public SpanBuilder append(CharSequence text, int start, int end) {
        super.append(text, start, end);
        return this;
    }

    @Override
    public SpanBuilder append(CharSequence text, Object what, int flags) {
        super.append(text, what, flags);
        return this;
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 下划线 </font>元素的Span
     */
    public static SpannableString getUnderLineSpan(String content) {
        return getSpan(content, new UnderlineSpan());
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 删除线 </font>元素的Span
     */
    public static SpannableString getDeleteLineSpan(String content) {
        return getSpan(content, new StrikethroughSpan());
    }


    /**
     * 获取带有<font color="#ff0000" size="4"> 下标 </font>元素的Span
     *
     * @param underLabelContent  下标的内容
     * @param underLabelTextSize 下标内容的大小（单位，sp）
     */
    public static SpannableString getUnderLabelSpan(String underLabelContent,
                                                    int underLabelTextSize) {
        if (underLabelTextSize < 1)
            underLabelTextSize = 1;
        return getSpan(underLabelContent, new SubscriptSpan(),
                new AbsoluteSizeSpan(underLabelTextSize, true));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 上标 </font>的元素Span
     *
     * @param underLabelContent  下标的内容
     * @param underLabelTextSize 下标内容的大小（单位，sp）
     */
    public static SpannableString getUpLabelSpan(String underLabelContent,
                                                 int underLabelTextSize) {
        if (underLabelTextSize < 1)
            underLabelTextSize = 1;
        return getSpan(underLabelContent, new SuperscriptSpan(),
                new AbsoluteSizeSpan(underLabelTextSize, true));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 点击 </font>元素的Span（默认是有下划线和字体颜色的，如需更改，<br/>
     * 则覆盖clickableSpan对象的updateDrawState方法 ）设置如下：<br/>
     * public void updateDrawState(TextPaint ds) {<br/>
     * ds.setColor(ds.linkColor); <br/>
     * ds.setUnderlineText(false);} <br/>
     *
     * @param clickContent  显示的点击内容
     * @param textView      设置可点击的textView
     * @param clickableSpan 点击的span
     */

    public static SpannableString getClickSpan(String clickContent,
                                               TextView textView, ClickableSpan clickableSpan) {
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setHighlightColor(Color.GREEN);
        }
        return getSpan(clickContent, clickableSpan);
    }


    /**
     * 获取带有<font color="#ff0000" size="4"> X轴缩放 </font>元素的Span<br/>
     *
     * @param scaleXContent 缩放的内容
     * @param proportion    缩放的倍数
     */
    public static SpannableString getScaleXSpan(String scaleXContent, float proportion) {
        return getSpan(scaleXContent, new ScaleXSpan(proportion));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 对其方式 </font>元素的Span<br/>
     *
     * @param content 设置对其方式的内容
     * @param align   设置对其方式    ALIGN_NORMAL,    ALIGN_OPPOSITE,    ALIGN_CENTER,
     */
    public static SpannableString getAlignmentSpan(String content, Layout.Alignment align) {
        return getSpan(content, new AlignmentSpan.Standard(align));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 字体类型 </font>元素的Span<br/>
     *
     * @param content 设置字体类型的内容
     * @param style   设置字体类型    Typeface.BOLD_ITALIC,Typeface.BOLD,Typeface.ITALIC,Typeface.NORMAL
     */
    public static SpannableString getStyleSpan(String content, int style) {
        return getSpan(content, new StyleSpan(style));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 相对大小 </font>元素的Span<br/>
     *
     * @param content    设置的内容
     * @param proportion 设置比例大小
     */
    public static SpannableString getRelativeSizeSpan(String content, float proportion) {
        return getSpan(content, new RelativeSizeSpan(proportion));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 绝对大小 </font>元素的Span<br/>
     *
     * @param content 设置的内容
     * @param size    设置字体大小（单位sp）
     */
    public static SpannableString getAbsoluteSizeSpan(String content, int size) {
        return getSpan(content, new AbsoluteSizeSpan(size, true));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 字体类型 </font>元素的Span<br/>
     *
     * @param content 设置的内容
     * @param family  设置字体类型 "monospace", "serif", and "sans-serif".
     */
    public static SpannableString getTypefaceSpan(String content, String family) {
        return getSpan(content, new TypefaceSpan(family));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 垂直的引用线 </font>元素的Span<br/>
     *
     * @param content 设置的内容
     * @param color   设置垂直的引用线的颜色
     */
    public static SpannableString getQuoteSpan(String content, int color) {
        return getSpan(content, new QuoteSpan(color));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 字体外观 </font>元素的Span<br/>
     *
     * @param content 设置的内容
     * @param styleId 设置style样式的id
     */
    public static SpannableString getTextAppearanceSpan(String content, Context context, int styleId) {
        return getSpan(content, new TextAppearanceSpan(context, styleId));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 字体颜色 </font>元素的Span<br/>
     *
     * @param content 设置的内容
     * @param color   设置style样式的id
     */
    public static SpannableString getTextColorSpan(String content, int color) {
        return getSpan(content, new ForegroundColorSpan(color));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 图片 </font>元素的Span<br/>
     *
     * @param content  设置的内容
     * @param drawable 设置drawable
     */
    public static SpannableString getImageSpan(String content, Drawable drawable) {
        return getSpan(content, new ImageSpan(drawable));
    }

    /**
     * 获取带有<font color="#ff0000" size="4"> 背景颜色 </font>元素的Span<br/>
     *
     * @param content 设置的内容
     * @param color   设置背景颜色
     */
    public static SpannableString getBackgroundColorSpan(String content, int color) {
        return getSpan(content, new BackgroundColorSpan(color));
    }

    /**
     * 给本字符串内容设置样式
     *
     * @param content 显示的点击内容
     * @param spans   样式如下：（如此参数为空或无，则此内容不设置样式）<br/>
     *                本类封装的Span <br/>
     *                AlignmentSpan.Standard 对齐方式 <br/>
     *                StyleSpan 字体样式：粗体、斜体等 <br/>
     *                UnderlineSpan 下划线 <br/>
     *                RelativeSizeSpan 相对大小（相对于之前字体的多少倍） <br/>
     *                AbsoluteSizeSpan 绝对大小（文本字体） <br/>
     *                ScaleXSpan 基于x轴缩放 <br/>
     *                TypefaceSpan 文本字体类型（ "monospace", "serif", and "sans-serif".） <br/>
     *                QuoteSpan 影响段落层次的文本格式。它可以给一个段落加上垂直的引用线。 <br/>
     *                StrikethroughSpan 删除线（中划线） <br/>
     *                SuperscriptSpan 上标（数学公式会用到） <br/>
     *                SubscriptSpan 下标（数学公式会用到） <br/>
     *                TextAppearanceSpan 文本外貌，设置为styleId样式（包括字体、大小、样式和颜色） <br/>
     *                ForegroundColorSpan 字体颜色（前景色） <br/>
     *                ClickableSpan （子类：URLSpan） 文本可点击，有点击事件，但是会调用TextView的点击事件 <br/>
     *                ImageSpan 放置一张图片 <br/>
     *                <p>
     *                本类未封装的Span <br/>
     *                NoCopySpan <br/>
     *                ParcelableSpan <br/>
     *                SuggestionSpan <br/>
     *                TtsSpan <br/>
     *                URLSpan <br/>
     *                BulletSpan <br/>
     *                BackgroundColorSpan <br/>
     *                LeadingMarginSpan.Standard <br/>
     *                EasyEditSpan <br/>
     *                LocaleSpan <br/>
     *                Annotation <br/>
     *                SpellCheckSpan <br/>
     *                SuggestionRangeSpan <br/>
     *                LineBackgroundSpan <br/>
     *                MaskFilterSpan <br/>
     *                RasterizerSpan <br/>
     *                TabStopSpan <br/>
     *                WrapTogetherSpan <br/>
     *                MetricAffectingSpan <br/>
     *                ReplacementSpan <br/>
     *                DynamicDrawableSpan <br/>
     *                ReplacementDrawableSpan   <br/>
     *                LineHeightSpan <br/>
     *                IconMarginSpan <br/>
     */
    public static SpannableString getSpan(CharSequence content,
                                          Object... spans) {
        if (content == null)
            content = "";
        SpannableString spannableString = new SpannableString(content);
        if (spans == null || spans.length == 0)
            return spannableString;
        for (Object span : spans) {
            if (span == null)
                continue;
            spannableString.setSpan(span, 0, spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 给SpannableString添加新的样式，应用于所有内容
     *
     * @param spannableString 原有样式的SpannableString
     * @param spans           详情请看setContentStyle方法中spans的解释{@link #getSpan}.
     */
    public static SpannableString addSpanStyleAll(
            SpannableString spannableString, Object... spans) {
        if (spannableString == null || spans == null || spans.length == 0)
            return spannableString;
        for (Object span : spans) {
            if (span == null)
                continue;
            spannableString.setSpan(span, 0, spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 给SpannableString添加新的样式，应用于start到end的位置
     *
     * @param spannableString 原有样式的SpannableString
     * @param start           从此位置开始设置样式
     * @param end             从此位置结束设置样式
     * @param spans           详情请看setContentStyle方法中spans的解释{@link #getSpan}.
     */
    public static SpannableString addSpanStylePart(
            SpannableString spannableString, int start, int end,
            Object... spans) {
        if (spannableString == null || start > end || spans == null
                || spans.length == 0)
            return spannableString;
        for (Object span : spans) {
            if (span == null)
                continue;
            spannableString.setSpan(span, start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

}
