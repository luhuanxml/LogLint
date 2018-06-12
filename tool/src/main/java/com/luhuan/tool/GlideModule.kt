package com.luhuan.tool

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

@GlideModule
class GlideModule : AppGlideModule()

fun ImageView.loadImage(url: String, @DrawableRes p: Int) {
    GlideApp.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(p).error(p).into(this)
}

fun ImageView.loadImage(url: String, @DrawableRes p: Int, @DrawableRes e: Int) {
    GlideApp.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(p).error(e).into(this)
}

fun Context.getBitmap(url:String,done:(bitmap:Bitmap)->Unit){
    GlideApp.with(this).asBitmap().load(url).into(object :SimpleTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            done.invoke(resource)
        }
    })
}