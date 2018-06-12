package com.luhuan.tool

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log

private var mCanLog:Boolean=true

//规定该方法只能被Application的子类调用
fun Application.canLog(canLog: Boolean){
    mCanLog=canLog
}

//用来弹出http请求结果log
@SuppressLint("LogNotLogKt")
fun logv(msg :String){
    if (mCanLog) {
        Log.v("HTTP_S",msg)
    }
}

//一般的log
@SuppressLint("LogNotLogKt")
fun Any.logd(msg :String){
    if (mCanLog) {
        Log.d(this as? String?:this.javaClass.simpleName,msg)
    }
}

//错误信息
@SuppressLint("LogNotLogKt")
fun Any.loge(errorMsg: String){
    if (mCanLog) {
        Log.e(this as? String?:this.javaClass.simpleName,errorMsg)
    }
}

//异常信息
@SuppressLint("LogNotLogKt")
fun Any.loge(throwable: Throwable){
    if (mCanLog) {
        Log.e(this as? String?:this.javaClass.simpleName,throwable.message)
    }
}


