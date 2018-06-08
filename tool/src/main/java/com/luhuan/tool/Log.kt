package com.luhuan.tool

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log

private var mCanLog:Boolean=true

//规定该方法只能被Application的子类调用
fun Application.canLog(canLog: Boolean){
    mCanLog=canLog
}

@SuppressLint("LogNotLogKt")
fun Any.logd(msg :String){
    if (mCanLog) {
        Log.d(this as? String?:this.javaClass.simpleName,msg)
    }
}

@SuppressLint("LogNotLogKt")
fun Any.loge(throwable: Throwable){
    if (mCanLog) {
        Log.e(this as? String?:this.javaClass.simpleName,throwable.message)
    }
}


