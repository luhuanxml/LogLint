package com.luhuan.tool

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

@SuppressLint("ToastNotToastKt")
fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

@SuppressLint("ToastNotToastKt")
fun Context.toast(msgId: Int) = Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()

