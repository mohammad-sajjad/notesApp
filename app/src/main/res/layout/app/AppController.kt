package com.coreStructure.app

import android.content.Context
import java.lang.ref.WeakReference

class AppController: AppControllerContract {

    companion object {
        private var INSTANCE: AppController? = null
        private var context: WeakReference<Context>? = null

        fun init(context: WeakReference<Context>): AppController? {
            Companion.context = context
            INSTANCE = AppController()
            return INSTANCE
        }

        fun get(): AppController = INSTANCE ?: throw Exception("App context is null, try calling init function of the")
    }


    override fun getContext(): Context {
        val localContext = context?.get()
        return localContext?: throw Exception("App context is null, try calling init function of the")
    }

}