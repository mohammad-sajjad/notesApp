package com.notesApp.app

import android.app.Application
import com.notesApp.app.AppController
import java.lang.ref.WeakReference


class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppController.init(WeakReference(this))

    }
}