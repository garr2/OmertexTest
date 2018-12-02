package com.garr.pavelbobrovko.omertextest.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.garr.pavelbobrovko.omertextest.BR

abstract class BaseMvvmFragment<VM : BaseViewModel<R>,
        R : BaseRouter<*>,
        B : ViewDataBinding> : BaseFragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B
    protected var router: R? = null

    abstract fun prodiveViewModel(): VM

    abstract fun provideLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = prodiveViewModel()

        binding = DataBindingUtil.inflate(inflater,provideLayoutId(),container,false)
        binding.setVariable(BR.view_model,viewModel)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity is BaseMvvmActivity<*, *, *>) {
            router = (activity as BaseMvvmActivity<*, *, *>).router as R
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.addRouter(router)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeRouter()
    }
}