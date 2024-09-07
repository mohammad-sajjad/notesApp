package com.coreStructure.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentHelper {

    fun FragmentManager.addFragment(containerId: Int, fragment: Fragment) {
        this.beginTransaction().apply {
            add(containerId, fragment)
            commit()
        }
    }

    fun FragmentManager.navigateTo(
        containerId: Int,
        fragment: Fragment,
        addToBackstack: Boolean = false,
        tag: String? = null
    ) {
        this.beginTransaction().apply {
            replace(containerId, fragment, tag)
            if (addToBackstack) addToBackStack(tag)
            commit()
        }
    }

    fun FragmentManager.navigateWithArgs(
        containerId: Int,
        fragment: Fragment,
        addToBackstack: Boolean = false,
        tag: String?,
        bundle: Bundle
    ) {
        fragment.arguments = bundle
        navigateTo(containerId, fragment, addToBackstack, tag)
    }

    fun FragmentManager.goBack() {
        if (this.backStackEntryCount > 0 ) {
            this.popBackStack()
        }
    }

}