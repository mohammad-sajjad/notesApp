package com.coreStructure.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm = createViewModel()
        if (modelClass.isAssignableFrom(vm::class.java)) return vm as T
        throw IllegalArgumentException("${vm::class.java} is not a valid ViewModel")

    }

    abstract fun createViewModel(): BaseViewModel
}