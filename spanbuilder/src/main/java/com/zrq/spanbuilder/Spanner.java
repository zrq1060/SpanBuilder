package com.zrq.spanbuilder;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.style.ClickableSpan;
import android.widget.TextView;

/**
 * @author zhangrq and llitfkitfk on 11/21/16.
 */

public final class Spanner {

    private Spanner() {
    }

    /**
     * 创建Spans的另一种使用方式
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * 样式构建器
     */
    public static class Builder {

        private SpanBuilder spanBuilder = new SpanBuilder();
        private Spans spans = new Spans();

        /**
         * 设置字体大小
         *
         * @param textSize 设置字体大小（单位sp）
         */
        public Builder size(int textSize) {
            this.spanBuilder.setTextSize(textSize);
            return this;
        }

        /**
         * 设置字体颜色
         *
         * @param textColor 字体颜色
         */
        public Builder color(int textColor) {
            this.spanBuilder.setTextColor(textColor);
            return this;
        }

        /**
         * 设置背景颜色
         *
         * @param color 背景颜色
         */
        public Builder backgroundColor(int color) {
            this.spanBuilder.setBackgroundColor(color);
            return this;
        }

        /**
         * 设置字体类型
         *
         * @param style 字体类型    粗斜体：BOLD_ITALIC，粗体：BOLD，斜体：ITALIC，正常：NORMAL
         */
        public Builder style(TextStyle style) {
            this.spanBuilder.setTextStyle(style.ordinal());
            return this;
        }

        /**
         * 设置自定义的字体类型
         *
         * @param typeface 设置字体类型
         */
        public Builder typeface(Typeface typeface) {
            this.spanBuilder.setTypeface(typeface);
            return this;
        }

        /**
         * 设置字体style样式的外观
         *
         * @param styleId 字体style样式的id
         */
        public Builder appearance(Context context, int styleId) {
            this.spanBuilder.setTextAppearance(context, styleId);
            return this;
        }

        /**
         * 设置点击
         * （默认是有下划线和字体颜色的，如需更改，则覆盖clickableSpan对象的updateDrawState方法 ）设置如下：<br/>
         * public void updateDrawState(TextPaint ds) {<br/>
         * ds.setColor(ds.linkColor); <br/>
         * ds.setUnderlineText(false); <br/>
         * }
         *
         * @param textView      设置可点击的textView
         * @param clickableSpan 点击的span
         */
        public Builder click(TextView textView, ClickableSpan clickableSpan) {
            this.spanBuilder.setClick(textView, clickableSpan);
            return this;
        }

        /**
         * 设置删除线的样式
         */
        public Builder deleteLine() {
            this.spanBuilder.setDeleteLine();
            return this;
        }

        /**
         * 设置下划线的样式
         */
        public Builder underLine() {
            this.spanBuilder.setUnderLine();
            return this;
        }

        /**
         * 设置图片
         *
         * @param drawable 设置drawable
         */
        public Builder image(Drawable drawable) {
            this.spanBuilder.setImage(drawable);
            return this;
        }

        /**
         * 设置系统自带字体类型
         *
         * @param family 设置字体 "monospace", "serif", and "sans-serif"等
         */
        public Builder fontFamily(String family) {
            this.spanBuilder.setFontFamily(family);
            return this;
        }

        /**
         * 设置垂直的引用线
         *
         * @param color 设置垂直的引用线的颜色
         */
        public Builder quote(int color) {
            this.spanBuilder.setQuote(color);
            return this;
        }

        /**
         * 设置字体内容的对其方式
         *
         * @param align 设置对其方式    ALIGN_NORMAL,    ALIGN_OPPOSITE,    ALIGN_CENTER,
         */
        public Builder alignment(Layout.Alignment align) {
            this.spanBuilder.setAlignment(align);
            return this;
        }

        /**
         * 设置字体的相对大小
         *
         * @param proportion 设置相对textSize的比例大小
         */
        public Builder relativeSize(float proportion) {
            this.spanBuilder.setRelativeSize(proportion);
            return this;
        }

        /**
         * 设置字体为上标的样式
         */
        public Builder upLabel() {
            this.spanBuilder.setUpLabel();
            return this;
        }

        /**
         * 设置字体为下标的样式
         */
        public Builder underLabel() {
            this.spanBuilder.setUnderLabel();
            return this;
        }

        /**
         * 设置字体X轴缩放
         *
         * @param proportion 缩放的倍数
         */
        public Builder scaleX(float proportion) {
            this.spanBuilder.setScaleX(proportion);
            return this;
        }

        /**
         * 设置需要改变样式的内容
         *
         * @param text 内容
         */
        public Builder text(CharSequence text) {
            appendOld();// 拼接上一个
            this.spanBuilder = new SpanBuilder(text);
            return this;
        }

        /**
         * 生成拼接好的Spans
         */
        public Spans build() {
            appendOld();// 拼接上一个
            return this.spans;
        }

        /**
         * 拼接上一个spanBuilder
         */
        private void appendOld() {
            if (this.spanBuilder.length() != 0) {
                // 无内容即无样式，不增加；有内容，增加，增加的是前一个的样式
                this.spans.append(this.spanBuilder);
            }
        }
    }
}
