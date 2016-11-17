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
// 简单样式：只改变字体大小、颜色；
textView1.setText(new Spans()
        .append("8")// ←样式依赖于控件
        .append(".88", 28, Color.RED)// ←简单样式
        .append("%", 16, Color.BLACK));// ←简单样式

// 复杂样式：用SpanBuilder生成
textView2.setText(new Spans()
        .append(new SpanBuilder("10", 50, Color.RED).setTypeface(Typeface.BOLD))// ←复杂样式：用SpanBuilder生成
        .append("元"));// ←样式依赖于控件

textView3.setText(new Spans()
        .append("￥149", 24, Color.RED)// ←简单样式
        .append(".9  ", 16, Color.RED)// ←简单样式
        .append(new SpanBuilder("￥259.00", 20, Color.BLACK).setDeleteLine())//←复杂样式：用SpanBuilder生成
        .append("   4738", 20, Color.RED)// ←简单样式
        .append("件已售", 20, Color.BLACK));// ←简单样式

```
#SpanBuilder说明介绍：
##1.方法介绍：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/1.png)
```
//  1.内置的效果方法：
//  .setTextSize(30)// 设置字体30Sp
//  .setTextColor(Color.RED) // 设置字体红色
//  .setBackgroundColor(Color.GREEN) // 设置字体背景绿色
//  .setTypeface(Typeface.BOLD_ITALIC)//  设置粗斜体 （粗斜体：BOLD_ITALIC，粗体：BOLD，斜体：ITALIC，正常：NORMAL）
//  .setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf"))//  设置自定义的ttf、otf字体
//  .setTextAppearance(this, android.R.style.TextAppearance_Small)//  设置自定义Style样式
//  .setClick(textView, clickableSpan)//  设置可点击
//  .setDeleteLine() //  设置删除线
//  .setUnderLine()//  设置下划线
//  .setImage(drawable)//  设置图片，（此内容无效，会被图片给替换,drawable必须设置setBounds）
//  .setFontFamily("monospace") // 设置字体类型为monospace (可设置"monospace", "serif", and "sans-serif"等)
//  .setQuote(Color.BLUE)//  设置蓝色的引用线
//  .setAlignment(Layout.Alignment.ALIGN_CENTER)//设置对齐方式为居中(ALIGN_NORMAL、ALIGN_OPPOSITE、ALIGN_CENTER）
//  .setRelativeSize(2.0f)//  设置2倍字体
//  .setUpLabel()// 设置为上标
//  .setScaleX(3f)// 设置X轴缩放3倍
//  .setUnderLabel()// 设置为下标

//  2.内置的功能方法：（方法注释已详细说明）
//  .setSpanAll()// 设置样式-作用于text全部
//  .setSpanPart()// 设置样式-作用于text文本start到end的位置
//  .addNewSpanStyle()// 给原有样式增加（替换）新样式

```
##2.使用介绍：
###2.1内置效果方法使用：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.png)
```
//  此为混合样式应用于此SpanBuilder的所有内容,设置字体12sp、红色、背景绿色、粗斜体
SpanBuilder spanBuilder = new SpanBuilder("字体12sp、红色、背景绿色、粗斜体")
        .setTextSize(12)
        .setTextColor(Color.RED)
        .setBackgroundColor(Color.GREEN)
        .setTypeface(Typeface.BOLD_ITALIC);

textView4.setText(new Spans()
        .append("控件样式")// ←样式依赖于控件
        .append(spanBuilder)//←复杂样式：用SpanBuilder生成
        .append("控件样式"));// ←样式依赖于控件

```
###2.2内置功能方法使用：
#### <font color="red">（可用于自定义样式，可添加的样式详情请看setSpanPart方法注释）</font>
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/3.png)
```
// 1 样式作用于内容所有
SpanBuilder customSpan1 = new SpanBuilder("自定义样式作用于内容所有\n")
        .setTextSize(20)
        .setSpanAll(new BackgroundColorSpan(Color.RED), new UnderlineSpan())// ←自定义样式
        .setTextColor(Color.CYAN);
        
// 2 样式作用于内容部分，即作用于5-8之间的内容
SpanBuilder customSpan2 = new SpanBuilder("自定义样式作用于内容部分")
        .setTextSize(20)
        .setSpanPart(5, 8, new BackgroundColorSpan(Color.RED), new UnderlineSpan())// ←自定义样式
        .setTextColor(Color.CYAN);

textView5.setText(new Spans().append(customSpan1).append(customSpan2));
                
```

##联系我：

QQ：273902141

邮箱：zrq1060@163.com


        
