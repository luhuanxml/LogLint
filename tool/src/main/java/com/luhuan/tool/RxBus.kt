package com.luhuan.tool

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

object RxBus{
    private val mBus = PublishProcessor.create<Any>().toSerialized()

    private var mSubscription: Subscription? = null

    fun post(type: Int, any: Any)=mBus.onNext(BusBean(type, any))

    /**
     * 该方法在reStart中注册的情况，第一遍回来不会走执行，因为RxBus需要先注册接收
     * 所以放在onStart中，但是放在onStart 中，当手机在当前页面锁定或者屏幕熄灭后回到当前页面，RxBus接收器会注册两个。
     * 这时候跳转返回，数据会接收两次。所以在onStart中订阅前，如果 Subscription 没有解除之前的订阅就先解除之前的订阅
     * 防止出现重复订阅导致接收两次的情况
     * 这里使用了 doOnSubscribe  doAfterNext 操作符，将 Subscription 放在RxBus中不暴露给其他类。
     * 更单纯的调用RxBus自己的方法，将RxJava中间处理隐藏起来
     */
    fun except(): Flowable<BusBean<*>> {
        //重点在这里
        if (mSubscription != null) mSubscription!!.cancel()
        return mBus.ofType(BusBean::class.java).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { subscription -> mSubscription = subscription }
                .doAfterNext {  mSubscription!!.cancel() }
    }

    fun unRegister() {
        if (mSubscription != null) {
            mSubscription!!.cancel()
        }
    }
}

class BusBean<T>(var type: Int, var obj: T?)


