package com.luhuan.simple

import android.app.Application
import com.luhuan.tool.canLog

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        canLog(true)
    }
}
