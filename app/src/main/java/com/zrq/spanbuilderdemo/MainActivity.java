package com.zrq.spanbuilderdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zrq.spanbuilder.SpanBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 50, 50);

        // 1.单个样式
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder()
                .append(new SpanBuilder("\n！！此页面看的的所有内容就是一个TextView\n\n").setBackgroundColor(Color.RED))
                .append(new SpanBuilder("字体30Sp").setTextSize(30))
                .append(new SpanBuilder("字体红色").setTextColor(Color.RED))
                .append(new SpanBuilder("字体背景绿色").setBackgroundColor(Color.GREEN))
                .append(new SpanBuilder("粗斜体").setTypeface(Typeface.BOLD_ITALIC))
                .append(new SpanBuilder("自定义的ttf、otf字体").setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Aileron-Light.otf")))
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

        // 2.混合样式
        // 此为混合样式应用于此SpanBuilder的所有内容,设置字体12sp、红色、背景绿色、粗斜体
        SpanBuilder spanBuilder = new SpanBuilder("字体12sp、红色、背景绿色、粗斜体")
                .setTextSize(12)
                .setTextColor(Color.RED)
                .setBackgroundColor(Color.GREEN)
                .setTypeface(Typeface.BOLD_ITALIC);
        spannableStringBuilder.append("默认字体样式").append(spanBuilder).append("默认字体样式\n\n");

        // 3.给原有样式替换新样式
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

        spannableStringBuilder.append(oldStyle);

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

        // 5.常用组合
        // 基本写法:
//        spannableStringBuilder
//                .append(new SpanBuilder("8").setTextSize(45).setTextColor(Color.RED))
//                .append(new SpanBuilder(".88").setTextSize(28).setTextColor(Color.RED))
//                .append(new SpanBuilder("%\n").setTextSize(16).setTextColor(Color.BLACK));

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

    }
}
