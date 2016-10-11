package com.zrq.spanbuilderdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zrq.spanbuilder.SpanBuilder;
import com.zrq.spanbuilder.TextSetting;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());


        SpanBuilder spanBuilder = new SpanBuilder()
                // 用法 1，拼接TextSetting对象
                .append(new TextSetting("!!此页面所有内容就是一个TextView\n", 22, Color.RED))
                .append(new TextSetting("200", 32, Color.RED))
                .append(new TextSetting("元\n", 20, Color.BLACK))
                .append(new TextSetting("50", 34, Color.RED))
                .append(new TextSetting(".88", 24, Color.RED))
                .append(new TextSetting(" %\n", 16, Color.BLACK))
                .append(new TextSetting("这是", 12, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)))
                .append(new TextSetting("特殊的", 25, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)))
                .append(new TextSetting("内容\n", 18, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)))
                // 用法 2之单一样式
                .append(SpanBuilder.getBackgroundColorSpan("↑↑上面这个是用法1（内容、大小、颜色）\n↓↓↓↓↓↓下面这个是用法2之单一样式↓↓↓↓↓↓\n", Color.RED))
                .append(SpanBuilder.getUnderLineSpan("这是下划线\n"))
                .append(SpanBuilder.getDeleteLineSpan("这是删除线\n"))
                .append("这是")// 拼接没有任何样式的字符串
                .append(SpanBuilder.getUnderLabelSpan("下标", 12))
                .append("这是")
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
                .append("后面这是一张图片")
                .append(SpanBuilder.getImageSpan("此内容无效，会被图片给替换", drawable))
                .append("前面这是一张图片\n");

        // 用法 2之混合样式（高级混合拼接）

        // 2.1 一次生成合体
        spanBuilder.append(SpanBuilder.getBackgroundColorSpan("\n↓↓↓↓↓↓下面这个是用法2之混合样式↓↓↓↓↓↓\n", Color.RED));
        spanBuilder.append(SpanBuilder.getBackgroundColorSpan("\n1.一次生成混合体\n", Color.GREEN));
        SpannableString span = SpanBuilder.getSpan("一次生成混合体，设置了红色字体、删除线、斜体加粗\n",
                new ForegroundColorSpan(Color.RED), new StrikethroughSpan(), new StyleSpan(Typeface.BOLD_ITALIC));
        spanBuilder.append(span);


        // 2.2 在原来span的基础上添加新的样式
        spanBuilder.append(SpanBuilder.getBackgroundColorSpan("2.在原来样式基础上添加生成混合体\n", Color.GREEN));

        // 2.2.1 新样式作用于所有
        spanBuilder.append(SpanBuilder.getBackgroundColorSpan("  2.1----添加样式作用于全部----\n", Color.YELLOW));
        SpannableString underLineSpan = SpanBuilder.getUnderLineSpan("在下划线样式的基础上，添加字体蓝色、斜体，新样式作用于全部内容\n");
        SpanBuilder.addSpanStyleAll(underLineSpan, new ForegroundColorSpan(Color.BLUE), new StyleSpan(Typeface.ITALIC));
        spanBuilder.append(underLineSpan);

        // 2.2.2 新样式作用于部分
        spanBuilder.append(SpanBuilder.getBackgroundColorSpan("  2.2----添加样式作用于部分----\n", Color.YELLOW));
        SpannableString styleSpan = SpanBuilder.getStyleSpan("在粗斜体样式的基础上给部分内容（斜粗体）添加红色、X轴缩放、背景绿色，新样式作用于部分内容\n", Typeface.BOLD_ITALIC);
        SpanBuilder.addSpanStylePart(styleSpan, 1, 4, new ForegroundColorSpan(Color.RED),new BackgroundColorSpan(Color.GREEN), new ScaleXSpan(2.5f));
        spanBuilder.append(styleSpan);
        textView.setText(spanBuilder);

    }
}
