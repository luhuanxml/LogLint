package com.luhuan.simple;

import android.util.Log;
import android.widget.Toast;

import com.luhuan.tool.LogKt;

public class Test {

    public void test(){
        Log.d("aaa", "test: ");
        LogKt.logd(this, "aaaa");
    }
}
