package com.coreStructure.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

object Helpers {
    inline fun <reified T : ViewDataBinding> inflateBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        layoutResId: Int
    ): T {
        return DataBindingUtil.inflate(inflater, layoutResId, viewGroup, false)
    }
}