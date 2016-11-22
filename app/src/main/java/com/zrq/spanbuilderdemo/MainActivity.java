package com.zrq.spanbuilderdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.BackgroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.zrq.spanbuilder.Spans;
import com.zrq.spanbuilder.TextStyle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);

        // 获取颜色的方法：
        // 1.ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        // 2.Color.parseColor("#123456");
        // 3.Color.RED;


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

        // Spans的append模式
//        textView3.setText(new Spans()
//                .append("￥149", 24, Color.RED)  // ←简单样式
//                .append(".9  ", 16, Color.RED)  // ←简单样式
//                .append(new SpanBuilder("￥259.00", 20, Color.BLACK).setDeleteLine())//←复杂样式：用SpanBuilder生成
//                .append(new SpanBuilder("   4738", 20, Color.RED).setTextStyle(TextStyle.BOLD_ITALIC))//←复杂样式：用SpanBuilder生成
//                .append("件已售", 20, Color.BLACK));// ←简单样式

//  -------------------方法介绍：----------------------
//  Spans.Builder说明     //  SpanBuilder说明

//  1.内置的效果方法：
//  .size()              //  .setTextSize(30)                    // 设置字体30Sp
//  .color()             //  .setTextColor(Color.RED)            // 设置字体红色
//  .backgroundColor()   //  .setBackgroundColor(Color.GREEN)    // 设置字体背景绿色
//  .style()             //  .setTextStyle(TextStyle.BOLD_ITALIC)// 设置粗斜体 （粗斜体：BOLD_ITALIC，粗体：BOLD，斜体：ITALIC，正常：NORMAL）
//  .typeface()          //  .setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf"))//  设置自定义的ttf、otf字体
//  .appearance()        //  .setTextAppearance(this, android.R.style.TextAppearance_Small)//  设置自定义Style样式
//  .click()             //  .setClick(textView, clickableSpan)  // 设置可点击
//  .deleteLine()        //  .setDeleteLine()                    // 设置删除线
//  .underLine()         //  .setUnderLine()                     // 设置下划线
//  .image()             //  .setImage(drawable)                 // 设置图片，（此内容无效，会被图片给替换,drawable必须设置setBounds）
//  .fontFamily()        //  .setFontFamily("monospace")         // 设置字体类型为monospace (可设置"monospace", "serif", and "sans-serif"等)
//  .quote()             //  .setQuote(Color.BLUE)               // 设置蓝色的引用线
//  .alignment()         //  .setAlignment(Layout.Alignment.ALIGN_CENTER)//设置对齐方式为居中(ALIGN_NORMAL、ALIGN_OPPOSITE、ALIGN_CENTER）
//  .relativeSize()      //  .setRelativeSize(2.0f)              // 设置2倍字体
//  .upLabel()           //  .setUpLabel()                       // 设置为上标
//  .scaleX()            //  .setScaleX(3f)                      // 设置X轴缩放3倍
//  .underLabel()        //  .setUnderLabel()                    // 设置为下标

//  2.内置的功能方法：（方法注释已详细说明）
//  .newSpanAll()        //  .setSpanAll()
//  .newSpanPart()       //  .setSpanPart()
//  .text()
//  .build()

//  2.使用介绍：
//  2.1内置效果方法使用：
        textView4.setText(Spans.builder()
                .text("控件样式")     // ←样式依赖于控件
                .text("字体12sp、红色、背景绿色、粗斜体", 12, Color.RED).backgroundColor(Color.GREEN).style(TextStyle.BOLD_ITALIC)
                .text("控件样式").build());   // ←样式依赖于控件


//  2.2扩展效果方法使用（可用于自定义样式，详细说明请看setSpanPart方法注释）：
        // 1 样式作用于内容所有
        Spans spans1 = Spans.builder()
                .text("自定义样式作用于内容所有\n")
                .size(20)
                .newSpanAll(new BackgroundColorSpan(Color.RED), new UnderlineSpan())        // ←自定义样式
                .color(Color.BLUE).build();
        // 2 样式作用于内容部分，即作用于5-8之间的内容
        Spans spans2 = Spans.builder(spans1)
                .text("自定义样式作用于内容部分")
                .size(20)
                .newSpanPart(5, 8, new BackgroundColorSpan(Color.RED), new UnderlineSpan()) // ←自定义样式
                .color(Color.BLUE).build();

        textView5.setText(spans2);
    }
}
