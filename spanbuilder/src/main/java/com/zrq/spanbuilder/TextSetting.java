package com.zrq.spanbuilder;

/**
 * 描述:
 *
 * @author zhangrq
 *         2016/9/30 13:34
 */
public class TextSetting {
    /**
     * 字体内容
     */
    private String text;
    /**
     * 字体大小
     */
    private int textSize = -1;
    /**
     * 字体颜色
     */
    private int textColor = -1;

    /**
     * 字体设置
     *
     * @param text      字体内容
     * @param textSize  字体大小 单位sp
     * @param textColor 字体颜色
     */
    public TextSetting(String text, int textSize, int textColor) {
        super();
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
