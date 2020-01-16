/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.heliossoftwaredeveloper.common.viewModel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * Base Fragment Class that will handle shared events/transactions to all fragments that will subclass this
 *
 * @author Ruel N. Grajo on 01/16/2020.
 */

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel>  : Fragment(){
    private lateinit var viewDataBinding: T
    private var viewModel: V? = null

    /**
     * Abstract function to set fragment layout
     *
     * @return the layout id
     */
    abstract fun createLayout(): Int

    /**
     * Abstract function to set binding variable
     *
     * @return the binding variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * Abstract function to initialize variables/objects
     */
    abstract fun initData()

    /**
     * Abstract function to initialize views
     */
    abstract fun initViews()

    /**
     * Abstract function to subscribe to live data of view model
     */
    abstract fun subscribeUi()

    /**
     * Function to get the data binding of the current fragment
     *
     * @return the data binding of the current fragment
     */
    fun getBinding() = viewDataBinding

    /**
     * Function to get the viewModel of the current fragment
     *
     * @return the viewModel of the current fragment
     */
    fun getViewModel() = viewModel!!

    /**
     * Function to get the viewModel class
     *
     * @return the viewModel class
     */
    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<V>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        performDataBinding()
        initData()
        initViews()
        subscribeUi()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, createLayout(), container, false)
        return viewDataBinding.root
    }

    // Function to execeute the data binding
    private fun performDataBinding() {
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())

        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    override fun onStart() {
        super.onStart()
        viewModel?.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel?.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel?.onStop()
    }
}