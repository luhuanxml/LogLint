package com.luhuan.tool

import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import java.util.concurrent.TimeUnit

/**
 * 使用Kotlin高阶函数写法，更加方便
 */

private const val TIME = 2L

fun View.click(done: () -> Unit) {
    RxView.clicks(this).throttleFirst(TIME, TimeUnit.SECONDS).subscribe { done.invoke() }
}

fun View.longClick(done: () -> Unit) {
    RxView.clicks(this).throttleFirst(TIME, TimeUnit.SECONDS).subscribe { done.invoke() }
}

fun TextView.textChange(done: (text: String) -> Unit) {
    RxTextView.textChanges(this).map { it.toString() }.subscribe { done.invoke(it) }
}

fun CompoundButton.check(done: (isChecked: Boolean) -> Unit) {
    RxCompoundButton.checkedChanges(this).subscribe { done.invoke(it) }
}
