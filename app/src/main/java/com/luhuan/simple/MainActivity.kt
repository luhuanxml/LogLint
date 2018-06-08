package com.luhuan.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luhuan.tool.logd

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logd("AAAA")
        "这就是TAG".logd("BBBBBBB")
        Test().test()
    }
}
