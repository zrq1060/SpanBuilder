package com.zrq.spanbuilderdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.BackgroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.zrq.spanbuilder.SpanBuilder;
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


        //简单样式: 统一风格
        Spans.builder().text("8").size(28).color(Color.RED).build();


        // 简单样式：只改变字体大小、颜色；
        textView1.setText(new Spans.Builder()
                .text("8")
                .text(".88").size(28).color(Color.RED)
                .text("%").size(16).color(Color.BLACK)
                .build()
        );

        // 复杂样式：用SpanBuilder生成
        textView2.setText(new Spans.Builder()
                .text("10").size(50).color(Color.RED).style(TextStyle.BOLD)
                .text("元")
                .build()
        );

        textView3.setText(new Spans.Builder()
                .text("￥149").size(24).color(Color.RED)
                .text(".9  ").size(16).color(Color.RED)
                .text("￥259.00").size(20).color(Color.BLACK).deleteLine()
                .text("   4738").size(20).color(Color.RED).style(TextStyle.BOLD_ITALIC)
                .text("件已售").size(20).color(Color.BLACK)
                .build()
        );
//  SpanBuilder说明
//  1.方法介绍：
//  1.内置的效果方法：
//  .setTextSize(30)                    // 设置字体30Sp
//  .setTextColor(Color.RED)            // 设置字体红色
//  .setBackgroundColor(Color.GREEN)    // 设置字体背景绿色
//  .setTypeface(Typeface.BOLD_ITALIC)  // 设置粗斜体 （粗斜体：BOLD_ITALIC，粗体：BOLD，斜体：ITALIC，正常：NORMAL）
//  .setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf"))//  设置自定义的ttf、otf字体
//  .setTextAppearance(this, android.R.style.TextAppearance_Small)//  设置自定义Style样式
//  .setClick(textView, clickableSpan)  // 设置可点击
//  .setDeleteLine()                    // 设置删除线
//  .setUnderLine()                     // 设置下划线
//  .setImage(drawable)                 // 设置图片，（此内容无效，会被图片给替换,drawable必须设置setBounds）
//  .setFontFamily("monospace")         // 设置字体类型为monospace (可设置"monospace", "serif", and "sans-serif"等)
//  .setQuote(Color.BLUE)               // 设置蓝色的引用线
//  .setAlignment(Layout.Alignment.ALIGN_CENTER)//设置对齐方式为居中(ALIGN_NORMAL、ALIGN_OPPOSITE、ALIGN_CENTER）
//  .setRelativeSize(2.0f)              // 设置2倍字体
//  .setUpLabel()                       // 设置为上标
//  .setScaleX(3f)                      // 设置X轴缩放3倍
//  .setUnderLabel()                    // 设置为下标

//  2.内置的功能方法：（方法注释已详细说明）
//  .setSpanAll()                       // 设置样式-作用于text全部
//  .setSpanPart()                      // 设置样式-作用于text文本start到end的位置

//  2.使用介绍：
//  2.1内置效果方法使用：
        //  此为混合样式应用于此SpanBuilder的所有内容,设置字体12sp、红色、背景绿色、粗斜体
        SpanBuilder spanBuilder = new SpanBuilder("字体12sp、红色、背景绿色、粗斜体")
                .setTextSize(12)
                .setTextColor(Color.RED)
                .setBackgroundColor(Color.GREEN)
                .setTypeface(Typeface.BOLD_ITALIC);

        textView4.setText(new Spans()
                .append("控件样式")     // ←样式依赖于控件
                .append(spanBuilder)    //←复杂样式：用SpanBuilder生成
                .append("控件样式"));   // ←样式依赖于控件


//  2.2内置功能方法使用（可用于自定义样式，详细说明请看setSpanPart方法注释）：

        // 1 样式作用于内容所有
        SpanBuilder customSpan1 = new SpanBuilder("自定义样式作用于内容所有\n")
                .setTextSize(20)
                .setSpanAll(new BackgroundColorSpan(Color.RED), new UnderlineSpan())        // ←自定义样式
                .setTextColor(Color.CYAN);
        // 2 样式作用于内容部分，即作用于5-8之间的内容
        SpanBuilder customSpan2 = new SpanBuilder("自定义样式作用于内容部分")
                .setTextSize(20)
                .setSpanPart(5, 8, new BackgroundColorSpan(Color.RED), new UnderlineSpan()) // ←自定义样式
                .setTextColor(Color.CYAN);

        textView5.setText(new Spans().append(customSpan1).append(customSpan2));
    }
}
