package com.coreStructure.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.coreStructure.R
import com.coreStructure.utils.Extensions.makeGone
import com.coreStructure.utils.Extensions.makeVisible


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected val TAG = javaClass.name

    private var toolbar: Toolbar? = null
    private var toolbarConfig: ToolbarConfig? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")

        // Set the base layout with toolbar
        setContentView(R.layout.activity_base_layout)

        // Initialize and set up the toolbar
        toolbar = findViewById(R.id.toolbar)
        if (hasToolbar()) {
            toolbar?.makeVisible()
        } else toolbar?.makeGone()
        toolbar?.let {
            setupToolbar(it)
        }

        // Inflate and set the content view from the derived activity
        _binding = inflateBinding(LayoutInflater.from(this))
        findViewById<FrameLayout>(R.id.content_container).addView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    abstract fun inflateBinding(inflater: LayoutInflater): VB

    open fun hasToolbar(): Boolean = true

    private fun setupToolbar(toolbar: Toolbar) {
        toolbarConfig?.let { config ->
            toolbar.title = config.title
            (if (config.showBackButton) config.backIcon else null)?.let {
                toolbar.setNavigationIcon(
                    it
                )
            }
            toolbar.setNavigationOnClickListener(config.onBackClicked)

//            if (config.showRightMenu) {
//                toolbar.menu.clear()
//                val menuInflater = toolbar.menuInflater
//                config.menuConfig?.let { menuConfig ->
//                    menuInflater.inflate(R.menu.menu_main, toolbar.menu)
//                    val menuItem = toolbar.menu.findItem(R.id.menu_item)
//                    menuItem.icon = getDrawable(menuConfig.icon)
//                    menuItem.setOnMenuItemClickListener {
//                        menuConfig.onMenuClicked.onClick(it.actionView)
//                        true
//                    }
//                }
//            }
        }
    }

    fun setToolbarConfig(config: ToolbarConfig) {
        toolbarConfig = config
        toolbar?.let {
            setupToolbar(it)
        }
    }

    data class ToolbarConfig(
        val title: String,
        val showBackButton: Boolean = true,
        val backIcon: Int = R.drawable.ic_back,
        val onBackClicked: View.OnClickListener? = null,
        val showRightMenu: Boolean = false,
        val menuConfig: ToolbarMenuConfig? = null
    )

    data class ToolbarMenuConfig(
        val icon: Int = R.drawable.ic_apps_menu,
        val onMenuClicked: View.OnClickListener
    )
}
