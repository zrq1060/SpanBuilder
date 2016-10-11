# SpanBuilder
##一个TextView可设置如下的效果
# 使用说明：
##用法 1：拼接TextSetting对象,可设置某某内容字体大小、颜色
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/1.png)
```
SpanBuilder spanBuilder = new SpanBuilder()
    .append(new TextSetting("200", 32, Color.RED))
    .append(new TextSetting("元\n", 20, Color.BLACK))
    .append(new TextSetting("50", 34, Color.RED))
    .append(new TextSetting(".88", 24, Color.RED))
    .append(new TextSetting(" %\n", 16, Color.BLACK))
    .append(new TextSetting("这是", 12, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)))
    .append(new TextSetting("特殊的", 25, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)))
    .append(new TextSetting("内容\n", 18, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)));

textView.setText(spanBuilder);
```
##用法 2：拼接Span对象（单一样式、混合样式）
###2.1之单一样式：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.1.png)
```
SpanBuilder spanBuilder = new SpanBuilder()
    .append(SpanBuilder.getBackgroundColorSpan("这是背景颜色\n", Color.RED))
    .append(SpanBuilder.getUnderLineSpan("这是下划线\n"))
    .append(SpanBuilder.getDeleteLineSpan("这是删除线\n"))
    .append(SpanBuilder.getUnderLabelSpan("下标", 12))
    .append(SpanBuilder.getUpLabelSpan("上标\n", 14))
    .append(SpanBuilder.getClickSpan("此内容可点击\n", textView, new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            // ！！点击了内容，但是会触发TextView的点击事件
            Toast.makeText(getApplicationContext(), "ClickSpan点击了", Toast.LENGTH_LONG).show();
        }
    }))
    .append(SpanBuilder.getScaleXSpan("X轴缩放3倍\n", 3f))
    .append(SpanBuilder.getAlignmentSpan("设置此内容的对齐方式为相反\n", Layout.Alignment.ALIGN_OPPOSITE))
    .append(SpanBuilder.getStyleSpan("设置此字体为粗斜体\n", Typeface.BOLD_ITALIC))
    .append(SpanBuilder.getRelativeSizeSpan("设置字体大小为之前的1.2倍\n", 1.2f))
    .append(SpanBuilder.getAbsoluteSizeSpan("设置字体大小为30sp\n", 30))
    .append(SpanBuilder.getTypefaceSpan("设置字体类型为serif\n", "serif"))
    .append(SpanBuilder.getQuoteSpan("设置此段前有垂直的蓝色引用线\n", Color.BLUE))
    .append(SpanBuilder.getTextAppearanceSpan("设置此字体自定义style样式\n", this, R.style.MyTextStyle))
    .append(SpanBuilder.getTextColorSpan("设置字体的颜色为红色\n", Color.RED))
    .append(SpanBuilder.getImageSpan("此内容无效，会被图片给替换", drawable));

textView.setText(spanBuilder);

```
###2.2之混合样式：（一次合成、在原来样式上添加）
####2.2.1一次合成：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.2.1.png)
```
SpannableString span =SpanBuilder.getSpan("一次生成混合体，设置了红色字体、删除线、斜体加粗\n",
                                new ForegroundColorSpan(Color.RED),//字体红色
                                new StrikethroughSpan(),//删除线
                                new StyleSpan(Typeface.BOLD_ITALIC));//斜体加粗

textView.setText(span);

```
####2.2.2在原来样式上添加：（作用于全部、作用于部分内容）
#####2.2.2.1新样式作用于全部内容：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.2.2.1.png)
```
SpannableString underLineSpan = SpanBuilder.getUnderLineSpan(
                                                "在下划线样式的基础上，添加字体蓝色、斜体，新样式作用于全部内容\n");

// 在下划线样式的基础上添加，添加完后，TextView直接设置添加好的underLineSpan就OK
SpanBuilder.addSpanStyleAll(underLineSpan, new ForegroundColorSpan(Color.BLUE), new StyleSpan(Typeface.ITALIC));

textView.setText(underLineSpan);
```
#####2.2.2.2新样式作用于部分内容：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.2.2.2.png)
```
SpannableString styleSpan = SpanBuilder.getStyleSpan(
                        "在粗斜体样式的基础上给部分内容（斜粗体）添加红色、X轴缩放、背景绿色，新样式作用于部分内容\n",
                        Typeface.BOLD_ITALIC);
// 在粗斜体样式样式的基础上给[1,4)内容（即斜粗体）添加，添加完后，TextView直接设置添加好的styleSpan就OK
SpanBuilder.addSpanStylePart(styleSpan, 1, 4,
                        new ForegroundColorSpan(Color.RED),//字体红色
                        new BackgroundColorSpan(Color.GREEN), //删除线
                        new ScaleXSpan(2.5f));//斜体加粗

textView.setText(styleSpan);

```

