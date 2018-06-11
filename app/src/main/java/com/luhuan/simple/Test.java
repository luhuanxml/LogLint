package com.luhuan.simple;

import android.util.Log;

import com.luhuan.tool.LogKt;

public class Test {

    public void test(){
        Log.d("","");
        LogKt.logd("aaa", "test: ");
        LogKt.logd(this, "aaaa");
    }
}
