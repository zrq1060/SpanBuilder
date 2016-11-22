# SpanBuilder
## <font color="red"> 一个TextView可设置如下的效果</font>
# 使用说明：

##导入
### Android studio 
```
compile 'com.zrq:spanbuilder:1.0.5'
  
```
### Eclipse
```
导入app/libs/spanbuilder-1.0.5.jar
  
```

##常用效果：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/0.png)
```
// Spans的Builder模式：原始写法
textView1.setText(Spans.builder()
        .text("8")
        .text(".88").size(28).color(Color.RED)
        .text("%").size(16).color(Color.BLACK)
        .build());

// Spans的Builder模式：简单写法：只简单了字体大小、颜色；
textView2.setText(Spans.builder()
        .text("10", 50, Color.RED).style(TextStyle.BOLD)
        .text("元")
        .build());

// Spans的Builder模式,可在text方法后面随意拼接
textView3.setText(Spans.builder()
        .text("￥149", 24, Color.RED)
        .text(".9  ", 16, Color.RED)
        .text("￥259.00", 20, Color.BLACK).deleteLine()
        .text("   4738", 20, Color.RED).style(TextStyle.BOLD_ITALIC)
        .text("件已售", 20, Color.BLACK)
        .build());

```
#SpanBuilder说明介绍：
##1.方法介绍：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/1.png)
```
//  Spans.Builder说明     //  SpanBuilder说明

//  1.内置的效果方法：
//  .size()              //  .setTextSize(30)                    // 设置字体30Sp
//  .color()             //  .setTextColor(Color.RED)            // 设置字体红色
//  .backgroundColor()   //  .setBackgroundColor(Color.GREEN)    // 设置字体背景绿色
//  .style()             //  .setTextStyle(TextStyle.BOLD_ITALIC)// 设置粗斜体 （BOLD_ITALIC、BOLD、TALIC、NORMAL）
//  .typeface()          //  .setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf"))//  设置自定义的ttf、otf字体
//  .appearance()        //  .setTextAppearance(this, android.R.style.TextAppearance_Small)//  设置自定义Style样式
//  .click()             //  .setClick(textView, clickableSpan)  // 设置可点击
//  .deleteLine()        //  .setDeleteLine()                    // 设置删除线
//  .underLine()         //  .setUnderLine()                     // 设置下划线
//  .image()             //  .setImage(drawable)                 // 设置图片（此内容无效,drawable必须设置setBounds）
//  .fontFamily()        //  .setFontFamily("monospace")         // 设置字体类型为monospace
//  .quote()             //  .setQuote(Color.BLUE)               // 设置蓝色的引用线
//  .alignment()         //  .setAlignment(Layout.Alignment.ALIGN_CENTER)//设置对齐方式为居中
//  .relativeSize()      //  .setRelativeSize(2.0f)              // 设置2倍字体
//  .upLabel()           //  .setUpLabel()                       // 设置为上标
//  .scaleX()            //  .setScaleX(3f)                      // 设置X轴缩放3倍
//  .underLabel()        //  .setUnderLabel()                    // 设置为下标

//  2.内置的功能方法：（方法注释已详细说明）
//  .newSpanAll()        //  .setSpanAll()
//  .newSpanPart()       //  .setSpanPart()
//  .text()
//  .build()

```
##2.使用介绍：
###2.1内置效果方法使用：

![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/2.png)
```
textView4.setText(Spans.builder()
        .text("控件样式")             // ←样式依赖于控件
        .text("12sp、红色、背景绿色、粗斜体",12,Color.RED).backgroundColor(Color.GREEN).style(TextStyle.BOLD_ITALIC)
        .text("控件样式").build());   // ←样式依赖于控件

```
###2.2扩展效果方法使用：（可用于自定义样式，详细说明请看方法注释）：
![image](https://github.com/zrq1060/SpanBuilderDemo/blob/master/screenshots/3.png)
```
// 1 样式作用于内容所有
Spans spans1 = Spans.builder()
        .text("自定义样式作用于内容所有\n")
        .size(20)
        .newSpanAll(new BackgroundColorSpan(Color.RED), new UnderlineSpan())        // ←自定义样式
        .color(Color.BLUE).build();
// 2 样式作用于内容部分，即作用于5-8之间的内容
Spans spans2 = Spans.builder(spans1)// 在spans1的基础上拼接
        .text("自定义样式作用于内容部分")
        .size(20)
        .newSpanPart(5, 8, new BackgroundColorSpan(Color.RED), new UnderlineSpan()) // ←自定义样式
        .color(Color.BLUE).build();

textView5.setText(spans2);
                
```

##联系我：

QQ：273902141

邮箱：zrq1060@163.com


        
