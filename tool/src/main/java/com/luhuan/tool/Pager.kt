package com.luhuan.tool

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable

//Activity跳转 Intent传值
inline fun <reified T : Activity>
        Context.open(vararg params: Pair<String, Any>) {
    Anko.startActivity(Context@ this, T::class.java, params)
}

inline fun <reified T : Activity>
        Activity.openForResult(vararg params: Pair<String, Any>, requestCode: Int) {
    Anko.startActivityForResult(Activity@ this, T::class.java, requestCode, params)
}

object Anko {

    fun startActivity(context: Context, clazz: Class<out Activity>,
                      params: Array<out Pair<String, Any>>) {
        val intent = getIntent(context, clazz, params)
        context.startActivity(intent)
    }

    fun startActivityForResult(context: Activity, clazz: Class<out Activity>, requestCode: Int,
                               params: Array<out Pair<String, Any>>) {
        val intent = getIntent(context, clazz, params)
        context.startActivityForResult(intent, requestCode)
        context.startActivity(intent)
    }

    private fun getIntent(context: Context, clazz: Class<out Activity>,
                          params: Array<out Pair<String, Any>>): Intent {
        val intent = Intent(context, clazz)
        if (params.isNotEmpty()) {
            params.forEach {
                val value = it.second
                when (value) {
                    is Float -> intent.putExtra(it.first, value)
                    is Double -> intent.putExtra(it.first, value)
                    is Short -> intent.putExtra(it.first, value)
                    is Int -> intent.putExtra(it.first, value)
                    is Long -> intent.putExtra(it.first, value)
                    is Boolean -> intent.putExtra(it.first, value)
                    is String -> intent.putExtra(it.first, value)
                    is Serializable -> intent.putExtra(it.first, value)
                }
            }
        }
        return intent
    }
}
