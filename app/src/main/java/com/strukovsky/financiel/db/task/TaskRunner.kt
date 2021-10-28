package com.strukovsky.financiel.db.task

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object TaskRunner {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    public interface Callback<R>
    {
        fun onComplete(result: R)
    }

    public fun <T> execute(call: Callable<T>, callback: Callback<T>)
    {
        executor.execute {
            val result = call.call()
            handler.post {
                callback.onComplete(result)
            }
        }
    }

}