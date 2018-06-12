package com.luhuan.tool

import android.content.res.Resources

val Int.dp: Int get() = (Int@ this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int get() = (Int@ this * Resources.getSystem().displayMetrics.density).toInt()
