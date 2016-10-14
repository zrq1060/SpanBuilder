# SpanBuilder
## <font color="red"> 一个TextView可设置如下的效果</font>
# 使用说明：

##导入
### Android studio 
```
compile 'com.zrq:spanbuilder:1.0.3'
  
```
### Eclipse
```
导入app/libs/spanbuilder-1.0.3.jar
  
```

##常用效果：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/0.png)
```
SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
// 基本写法:
//spannableStringBuilder
//        .append(new SpanBuilder("8").setTextSize(45).setTextColor(Color.RED))
//        .append(new SpanBuilder(".88").setTextSize(28).setTextColor(Color.RED))
//        .append(new SpanBuilder("%\n").setTextSize(16).setTextColor(Color.BLACK));

// 简便写法:只能简便字体大小、字体颜色
spannableStringBuilder
        .append(new SpanBuilder("8", 45, Color.RED))
        .append(new SpanBuilder(".88", 28, Color.RED))
        .append(new SpanBuilder("%\n", 16, Color.BLACK));

spannableStringBuilder
        .append(new SpanBuilder("10", 50, Color.RED)
                .setTypeface(Typeface.BOLD_ITALIC))// 粗斜体：BOLD_ITALIC，粗体：BOLD，斜体：ITALIC，正常：NORMAL
        .append(new SpanBuilder("元\n", 16, Color.BLACK));

spannableStringBuilder
        .append(new SpanBuilder("￥149", 24, Color.RED))
        .append(new SpanBuilder(".9  ", 16, Color.RED))
        .append(new SpanBuilder("￥259.00", 20, Color.BLACK).setDeleteLine())// 删除线
        .append(new SpanBuilder("   4738", 20, Color.RED))
        .append(new SpanBuilder("件已售\n", 20, Color.BLACK));

textView.setText(spannableStringBuilder);

```
##用法 1之单一样式：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/1.png)
```
// 1.单个样式
SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder()
        .append(new SpanBuilder("\n！！此页面看的的所有内容就是一个TextView\n\n").setBackgroundColor(Color.RED))
        .append(new SpanBuilder("字体30Sp").setTextSize(30))
        .append(new SpanBuilder("字体红色").setTextColor(Color.RED))
        .append(new SpanBuilder("字体背景绿色").setBackgroundColor(Color.GREEN))
        .append(new SpanBuilder("粗斜体").setTypeface(Typeface.BOLD_ITALIC))
        .append(new SpanBuilder("自定义的ttf、otf字体").
                        setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf")))
        .append(new SpanBuilder("自定义Style样式").setTextAppearance(this, android.R.style.TextAppearance_Small))
        .append(new SpanBuilder("可点击").setClick(textView, new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // ！！点击了内容，但是会触发TextView的点击事件
                Toast.makeText(getApplicationContext(), "ClickSpan点击了", Toast.LENGTH_LONG).show();
            }
        }))
        .append(new SpanBuilder("删除线").setDeleteLine())
        .append(new SpanBuilder("下划线").setUnderLine())
        .append(new SpanBuilder("此内容无效，会被图片给替换").setImage(drawable))
        .append(new SpanBuilder("字体类型为monospace\n").setFontFamily("monospace"))
        .append(new SpanBuilder("设置蓝色的引用线\n").setQuote(Color.BLUE))
        .append(new SpanBuilder("设置此内容的对齐方式为相反\n").setAlignment(Layout.Alignment.ALIGN_OPPOSITE))
        .append(new SpanBuilder("2倍字体\n").setRelativeSize(2.0f))
        .append(new SpanBuilder("上标").setUpLabel())
        .append(new SpanBuilder("X轴缩放3倍").setScaleX(3f))
        .append(new SpanBuilder("下标\n\n").setUnderLabel());

textView.setText(spannableStringBuilder);

```
##用法 2之混合样式：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.png)
```
// 2.混合样式
SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

// 此为混合样式应用于此SpanBuilder的所有内容,设置字体12sp、红色、背景绿色、粗斜体
SpanBuilder spanBuilder = new SpanBuilder("字体12sp、红色、背景绿色、粗斜体")
        .setTextSize(12)
        .setTextColor(Color.RED)
        .setBackgroundColor(Color.GREEN)
        .setTypeface(Typeface.BOLD_ITALIC);
spannableStringBuilder.append("默认字体样式").append(spanBuilder).append("默认字体样式\n\n");

textView.setText(spannableStringBuilder);

```
##用法 3之将原有样式的部分内容替换成新样式：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/3.png)
```
// 3.将原有样式的部分内容替换成新样式
// 原有样式，设置字体15sp、字体红色、背景绿色、粗斜体。
SpanBuilder oldStyle = new SpanBuilder("我是原有样式:我是新样式:我是原有样式\n")
        .setTextSize(15)
        .setTextColor(Color.RED)
        .setBackgroundColor(Color.GREEN)
        .setTypeface(Typeface.BOLD_ITALIC);

// 新样式，字体蓝色、背景红色。
SpanBuilder newStyle = new SpanBuilder()
        .setTextColor(Color.BLUE)
        .setBackgroundColor(Color.RED);

// 将原有内容7-12之间的内容，设置其newStyle: 字体蓝色、背景红色
oldStyle.addNewSpanStyle(7, 12, newStyle); // newStyle里面的样式会替换原来oldStyle的样式

textView.setText(oldStyle);
```
##用法 4之扩展：添加自定义样式
```
// 4.添加自定义样式
// 4.1 样式作用于所有内容
new SpanBuilder("X轴缩放3倍\n").setSpanAll(
        new ForegroundColorSpan(Color.RED),//字体红色
        new BackgroundColorSpan(Color.GREEN), //背景绿色
        new ScaleXSpan(3f));//X轴缩放3倍
        
// 4.2 样式作用于部分内容，即将1-5之间的内容，设置其为：字体红色、背景绿色、X轴缩放3倍
new SpanBuilder("X轴缩放3倍\n").setSpanPart(1, 5,
        new ForegroundColorSpan(Color.RED),//字体红色
        new BackgroundColorSpan(Color.GREEN), //背景绿色
        new ScaleXSpan(3f));//X轴缩放3倍
                
```

#####联系我：

QQ：273902141

邮箱：zrq1060@163.com


        
