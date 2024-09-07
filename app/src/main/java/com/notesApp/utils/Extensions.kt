package com.notesApp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

object Extensions {

    inline fun <reified T : Any> Activity.launchActivity(
        requestCode: Int = -1,
        options: Bundle? = null,
        init: Intent.() -> Unit = {}
    ) {
        val intent = newIntent<T>(this)
        intent.init()
        if (requestCode == -1) startActivity(intent, options)
        else startActivityForResult(intent, requestCode, options)
    }

    inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

    fun ViewGroup.inflate(resId: Int): View {
        return LayoutInflater.from(context).inflate(resId, this, false)
    }

    fun View.makeGone() {
        this.visibility = View.GONE
    }

    fun View.makeVisible() {
        this.visibility = View.VISIBLE
    }

    fun View.makeInvisible() {
        this.visibility = View.INVISIBLE
    }
}