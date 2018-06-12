# LogLint

## Kotlin 扩展函数封装 

### RxBus封装，使用不用考虑接收完解绑，不会出现重复的接收，只需要在activity生命周期停止的时候调用解绑方法即可
### Rxbinding 扩展函数封装，Kotlin使用更简洁
```
findViewById<TextView>(R.id.text).click { toast("你好") }
findViewById<EditText>(R.id.edit).textChange { logd(it) }
```
### GlideApp图片加载
```
ImageView(this).loadImage("url",R.mipmap.ic_launcher)
```
获得Bitmap对象
```
context.getBitmap("url",{ImageView(this).setImageBitmap(it)}) //it即bitmap
```
### dp to px ,px to dp
```
val heightPx= 100.px
val widthDp= 100.dp
```

## 包含toast log的lint检查，lint只针对Java检测 ,防止用户直接使用android.util.Log  android.widget.Toast 发出黄色背景警告

[ ![Download](https://api.bintray.com/packages/luhuanxml/maven/LogLint/images/download.svg) ](https://bintray.com/luhuanxml/maven/LogLint/_latestVersion)


gradle 引用

```
compile 'com.luhuan.tool:LogLint:0.0.3'
```
