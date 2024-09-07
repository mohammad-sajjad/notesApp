package com.coreStructure.core

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.coreStructure.utils.Helpers

abstract class BaseFragment<T: ViewBinding>: Fragment() {
    private lateinit var binding: T
    protected val TAG = javaClass.name
    protected lateinit var baseActivity: BaseActivity<*>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is BaseActivity<*>) {
            baseActivity = activity as BaseActivity<*>
        } else throw ClassCastException("$context must be an instance of BaseActivity")

        Log.e(TAG, "onAttach: ", )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = Helpers.inflateBinding(inflater, container!!, getLayoutRes())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onViewCreated: ", )
    }

    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ", )
    }


    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ", )
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ", )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ", )
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "onDetach: ", )
    }
}