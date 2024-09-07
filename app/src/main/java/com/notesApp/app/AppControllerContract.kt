package com.notesApp.app

import android.content.Context

interface AppControllerContract {

    companion object {
        fun get(): AppControllerContract = AppController()
    }

    fun getContext(): Context
}