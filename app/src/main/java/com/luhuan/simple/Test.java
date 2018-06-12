package com.luhuan.simple;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.luhuan.tool.LogKt;

public class Test {

    public void test(Activity mActivity){
        Toast.makeText(mActivity, "", Toast.LENGTH_SHORT).show();
        LogKt.logd("", "");
    }
}
