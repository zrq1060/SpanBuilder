package com.zrq.spanbuilderdemo;

import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zrq.spanbuilder.Spans;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textViewAll = findViewById(R.id.textViewAll);
        final Drawable drawable = getResources().getDrawable(R.mipmap.ic_test);
        // 获取颜色的方法：
        // 1.Color.RED;
        // 2.Color.parseColor("#123456");
        // 3.ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        // 4.getResources().getColor(R.color.colorPrimary)

        // Spans的常用样式
        textView1.setText(Spans.builder()
                .text("8")
                .text(".88").size(28).color(Color.RED)
                .text("%").size(16).color(Color.BLACK)
                .build());

        textView2.setText(Spans.builder()
                .text("10").size(50).color(Color.RED).style(Typeface.BOLD)
                .text("元")
                .build());

        textView3.setText(Spans.builder()
                .text("￥149").size(24).color(Color.RED)
                .text(".9  ").size(16).color(Color.RED)
                .text("￥259.00").size(20).color(Color.BLACK).strikeThrough()
                .text("   4738").size(20).color(Color.RED).style(Typeface.BOLD_ITALIC)
                .text("件已售").size(20).color(Color.BLACK)
                .build());

        textView4.setText(Spans.builder()
                .text("请阅读")
                .text("《隐私权政策》").click(textView4, new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(getApplicationContext(), "隐私权政策", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        ds.setColor(Color.RED);
                        ds.setUnderlineText(false);
                    }
                })
                .text("和")
                .text("《用户协议》").click(textView4, new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(getApplicationContext(), "用户协议", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        ds.setColor(Color.RED);
                        ds.setUnderlineText(false);
                    }
                })
                .text(" ")
                .build());

        textView5.setText(Spans.builder()
                .text("表情前")
                .text("表情内容").image(getApplicationContext(), R.mipmap.ic_face)
                .text("表情后")
                .build());

        // Spans的全部样式
        textViewAll.setText(Spans.builder()
                .text("--！！下面内容全部是在一个TextView上--").backgroundColor(Color.RED)
                .text("\n字体大小：").text("24SP大小 ").size(24).text("24PX大小").sizePx(24)
                .text("\n字体颜色：").text("颜色").color(Color.RED)
                .text("\n字体样式：").text("粗体 ").style(Typeface.BOLD).text("斜体 ").style(Typeface.ITALIC).text("粗斜体").style(Typeface.BOLD_ITALIC)
                .text("\n------------------------------------------------------------")
                .text("\n字体背景：").text("背景").backgroundColor(Color.RED)
                .text("\n自定义字体：Hello Word ").text("Hello Word").typeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf"))
                .text("\n字体外貌：").text("外貌").appearance(this, android.R.style.TextAppearance_Small)
                .text("\n字体Family：").text("字体Family").fontFamily("monospace")
                .text("\n------------------------------------------------------------")
                .text("\n删除线：").text("删除线").strikeThrough()
                .text("\n下划线：").text("下划线").underLine()
                .text("\n引用线").quote(Color.BLUE)
                .text("\n------------------------------------------------------------")
                .text("\n对齐方式：")
                .text("\n正常对齐").alignment(Layout.Alignment.ALIGN_NORMAL)
                .text("\n反向对齐").alignment(Layout.Alignment.ALIGN_OPPOSITE)
                .text("\n居中对齐").alignment(Layout.Alignment.ALIGN_CENTER)
                .text("\n------------------------------------------------------------")
                .text("\nX倍字体：").text("2倍字体").relativeSize(2)
                .text("\nX轴缩放：").text("X轴缩0.5倍").scaleX(0.5f)
                .text("\n------------------------------------------------------------")
                .text("\n上标：").text("X").text("2").superscript()
                .text("\n下标：").text("X").text("2").subscript()
                .text("\n------------------------------------------------------------")
                .text("\n字体点击：").text("协议").click(textViewAll, new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(getApplicationContext(), "协议", Toast.LENGTH_SHORT).show();
                    }
                }).text(" ")// 点击协议后面也会触发点击事件，解决办法协议后面需有不可点击内容
                .text("\nURL点击：").text("百度").url(textViewAll, new URLSpan("http://www.baidu.com")).text(" ")// 点击百度后面也会触发点击事件，解决办法百度后面需有不可点击内容
                .text("\n------------------------------------------------------------")
                .text("\n设置图片：\n")
                .text("图片内容，").drawableMargin(drawable).text("可设置Drawable，不会替换内容，顶部对齐，文字在图片右边\n")// 设置 Drawable，不会替换内容
                .text("图片内容，").iconMargin(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test)).text("可设置Bitmap，不会替换内容，顶部对齐，文字在图片右边\n")// 设置 Bitmap，不会替换内容
                .text("前前前前").text("图片内容").dynamicDrawable(new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BOTTOM) {
                    @Override
                    public Drawable getDrawable() {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;// 需要设置setBounds，会替换内容
                    }
                }).text("后后后后，").text("可设置Drawable，会替换内容，底部对齐，文字环绕嵌入型\n")
                .text("前前前前").text("图片内容").image(drawable, DynamicDrawableSpan.ALIGN_BASELINE).text("后后后后，").text("可设置Drawable、Bitmap、Uri、resourceId，会替换内容，基线对齐，文字环绕嵌入型")// 设置 Bitmap，会替换内容
                .text("\n------------------------------------------------------------")
                .text("\n子弹：看不出效果，需前面无内容").bullet(2, Color.RED)// 前面不能设置内容
                .text("\n行前空隙：内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容").leadingMargin(100, 30)
                .text("\n制表符位置：内容\t内容内容内容内容内容内容内容内\t容内容内容内容内容内容内容").tabStop(900)
                .text("\n字体模糊：").text("容内容内容内容内容内容内容内容内容内容内容").blurMask(30, BlurMaskFilter.Blur.OUTER)
                .build());

//  -------------------方法介绍：----------------------
//  .text()              // 设置拼接内容，下面的方法全部作用于此text
//  .size()              // 设置字体大小，单位sp
//  .sizePx()            // 设置字体大小，单位px
//  .color()             // 设置字体颜色
//  .style()             // 设置字体样式 （Typeface NORMAL：正常，BOLD：粗体，ITALIC：斜体，BOLD_ITALIC：粗斜体）

//  .backgroundColor()   // 设置字体背景
//  .typeface()          // 设置自定义的ttf、otf字体
//  .appearance()        // 设置字体外貌
//  .fontFamily()        // 设置字体Family (可设置"monospace", "serif", and "sans-serif"等)

//  .strikeThrough()     // 设置删除线
//  .underLine()         // 设置下划线
//  .quote()             // 设置引用线颜色

//  .alignment()         // 设置对齐方式(ALIGN_NORMAL、ALIGN_OPPOSITE、ALIGN_CENTER）

//  .relativeSize()      // 设置X倍字体
//  .scaleX()            // 设置X轴缩放x倍

//  .superscript()       // 设置为上标
//  .subscript()         // 设置为下标

//  .click()             // 设置可点击
//  .url()               // 设置Url，点击会打开网页

//  .drawableMargin()    // 可设置Drawable，不会替换内容，顶部对齐，文字在图片右边
//  .iconMargin()        // 可设置Bitmap，不会替换内容，顶部对齐，文字在图片右边
//  .dynamicDrawable()   // 可设置Drawable，会替换内容，底部对齐，文字环绕嵌入型
//  .image()             // 可设置Drawable、Bitmap、Uri、resourceId，会替换内容，基线对齐，文字环绕嵌入型

//  .bullet()            // 设置子弹，可设置gapWidth、color
//  .leadingMargin()     // 设置行前空隙
//  .tabStop()           // 设置制表符位置
//  .blurMask()          // 设置字体模糊


//  .setSpanAll()        // 设置自定义样式，作用于当前text()全部内容
//  .setSpanPart()       // 设置自定义样式，作用于当前text()部分内容
//  .build()


    }
}
