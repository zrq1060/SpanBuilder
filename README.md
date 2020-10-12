# SpanBuilder

## 特点

* **内置大量常用样式**
* **支持自定义样式**

## Demo

### 下载
![qrcode](https://github.com/zrq1060/SpanBuilder/blob/master/screenshots/qrcode.png)

### 项目演示

#### 常用效果
<img src="https://github.com/zrq1060/SpanBuilder/blob/master/screenshots/common.jpg" width="70%">

#### 全部效果
<img src="https://github.com/zrq1060/SpanBuilder/blob/master/screenshots/all.jpg" width="70%">  

## 简单使用

### 引入
```
compile 'com.zrq:spans:1.1.0'
```

### 使用
```
textView.setText(Spans.builder()
        .text("8")
        .text(".88").size(28).color(Color.RED)
        .text("%").size(16).color(Color.BLACK)
        .build());
```

## 方法说明
| 方法 | 说明 |
| :-------- | :--------|
| text()             | 设置拼接内容，下面的方法全部作用于此text |
| size()             | 设置字体大小，单位sp |
| sizePx()           | 设置字体大小，单位px |
| color()            | 设置字体颜色 |
| style()            | 设置字体样式 （Typeface NORMAL：正常，BOLD：粗体，ITALIC：斜体，BOLD_ITALIC：粗斜体） |
| backgroundColor()  | 设置字体背景 |
| typeface()         | 设置自定义的ttf、otf字体 |
| appearance()       | 设置字体外貌 |
| fontFamily()       | 设置字体Family (可设置"monospace", "serif", and "sans-serif"等) |
| strikeThrough()    | 设置删除线 |
| underLine()        | 设置下划线 |
| quote()            | 设置引用线颜色 |
| alignment()        | 设置对齐方式(ALIGN_NORMAL、ALIGN_OPPOSITE、ALIGN_CENTER） |
| relativeSize()     | 设置X倍字体 |
| scaleX()           | 设置X轴缩放x倍 |
| superscript()      | 设置为上标 |
| subscript()        | 设置为下标 |
| click()            | 设置可点击 |
| url()              | 设置Url，点击会打开网页 |
| drawableMargin()   | 可设置Drawable，不会替换内容，顶部对齐，文字在图片右边 |
| iconMargin()       | 可设置Bitmap，不会替换内容，顶部对齐，文字在图片右边 |
| dynamicDrawable()  | 可设置Drawable，会替换内容，底部对齐，文字环绕嵌入型 |
| image()            | 可设置Drawable、Bitmap、Uri、resourceId，会替换内容，基线对齐，文字环绕嵌入型 |
| bullet()           | 设置子弹，可设置gapWidth、color |
| leadingMargin()    | 设置行前空隙 |
| tabStop()          | 设置制表符位置 |
| blurMask()         | 设置字体模糊 |
| setSpanAll()       | 设置自定义样式，作用于当前text()全部内容 |
| setSpanPart()      | 设置自定义样式，作用于当前text()部分内容 |
| build()      		 |  |


## 联系我
QQ：273902141

邮箱：zrq1060@163.com
