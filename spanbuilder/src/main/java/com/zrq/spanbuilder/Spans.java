package com.zrq.spanbuilder;

import android.text.SpannableStringBuilder;

/**
 * 描述:
 *
 * @author zhangrq
 *         2016/11/5 21:14
 */
public class Spans extends SpannableStringBuilder {
    /**
     * 拼接文本、样式依赖于控件
     * @param text 设置的内容
     */
    @Override
    public Spans append(CharSequence text) {
        super.append(text);
        return this;
    }
    /**
     * 拼接文本、样式依赖于TextView
     * @param text 设置的内容
     */
    @Override
    public Spans append(char text) {
        super.append(text);
        return this;
    }
    /**
     * @param text    设置的内容，作用于text所有
     * @param textSize  设置字体大小，单位sp
     * @param textColor 设置字体颜色
     */
    @Override
    public Spans append(CharSequence text, int textSize, int textColor) {
        super.append(new SpanBuilder(text, textSize, textColor));
        return this;
    }
    /**
     * 拼接文本、样式依赖于TextView
     * @param text 设置的内容
     */
    @Override
    public Spans append(CharSequence text, Object what, int flags) {
        super.append(text, what, flags);
        return this;
    }

}
