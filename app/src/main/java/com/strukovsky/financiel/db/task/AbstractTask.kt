package com.strukovsky.financiel.db.task

import android.content.Context
import com.strukovsky.financiel.db.MainDatabase
import java.util.concurrent.Callable

abstract class AbstractTask<T>(private val context: Context): Callable<T>{
    protected val db = MainDatabase.getInstance(context)

}