package com.luhuan.tool

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.toast(msgId: Int) = Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()