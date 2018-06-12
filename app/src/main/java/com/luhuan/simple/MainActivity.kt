package com.luhuan.simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.luhuan.tool.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Test().test(this)
        findViewById<TextView>(R.id.text).click { toast("你好") }
        findViewById<EditText>(R.id.edit).textChange { logd(it) }

        getBitmap("",{ImageView(this).setImageBitmap(it)})

        RxBus.except().subscribe({
            if (it.type == 1) {
                val ss:String= it.obj as String
            }
        })
    }
}
