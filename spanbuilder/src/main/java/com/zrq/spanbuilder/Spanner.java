package com.zrq.spanbuilder;

import android.text.style.UnderlineSpan;

/**
 * Created by llitfkitfk on 11/21/16.
 */

public final class Spanner {

    private Spanner() {
    }
    
    /**
     * 创建简单样式
     *
     * @return
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * 样式构建器
     */
    public static class Builder {

        private SpanBuilder spanBuilder;
        private Spans spans;

        public Builder() {
            spans = new Spans();
        }

        public Builder(Spans spans) {
            this.spans = spans;
        }

        /**
         * 下划线样式
         *
         * @return
         */
        public Builder deleteLine() {
            this.spanBuilder.setDeleteLine();
            return this;
        }

        /**
         * 设置一种风格
         *
         * @param style 风格
         * @return
         */
        public Builder style(SpanStyle style) {
            switch (style) {
                case NORMAL:
                case BOLD:
                case BOLD_ITALIC:
                case ITALIC:
                    this.spanBuilder.setTypeface(style.ordinal());
                    break;
                case DELETELINE:
                    this.spanBuilder.setDeleteLine();
                    break;
                case UNDERLINE:
                    this.spanBuilder.setSpanAll(new UnderlineSpan());
                    break;
                default:
            }
            return this;
        }

        /**
         * 设置多种风格
         *
         * @param styles 风格
         * @return
         */
        public Builder styles(SpanStyle[] styles) {
            if (styles.length == 0) {
                return this;
            } else {
                for (SpanStyle style : styles) {
                    switch (style) {
                        case NORMAL:
                        case BOLD:
                        case BOLD_ITALIC:
                        case ITALIC:
                            this.spanBuilder.setTypeface(style.ordinal());
                            break;
                        case DELETELINE:
                            this.spanBuilder.setDeleteLine();
                            break;
                        case UNDERLINE:
                            this.spanBuilder.setSpanAll(new UnderlineSpan());
                            break;
                        default:
                    }
                }
            }
            return this;
        }

        public Builder size(int textSize) {
            this.spanBuilder.setTextSize(textSize);
            return this;
        }

        /**
         * 设置背景颜色
         *
         * @param color 背景颜色
         * @return
         */
        public Builder backgroundColor(int color) {
            this.spanBuilder.setBackgroundColor(color);
            return this;
        }

        /**
         * 设置字体颜色
         *
         * @param textColor 字体颜色
         * @return
         */
        public Builder color(int textColor) {
            this.spanBuilder.setTextColor(textColor);
            return this;
        }

        /**
         * 设置需要改变样式的内容
         *
         * @param text 内容
         * @return
         */
        public Builder text(CharSequence text) {
            if (this.spanBuilder != null) {
                this.spans.append(this.spanBuilder);
            }
            this.spanBuilder = new SpanBuilder(text);
            return this;
        }

        /**
         * 生成Span
         *
         * @return
         */
        public Spans build() {
            this.spans.append(this.spanBuilder);
            return this.spans;
        }


    }
}
