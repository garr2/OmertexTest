package com.garr.pavelbobrovko.omertextest.presentation.screen.man.detalis

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.garr.pavelbobrovko.omertextest.R
import com.garr.pavelbobrovko.omertextest.databinding.FragmentDetalisManBinding
import com.garr.pavelbobrovko.omertextest.presentation.base.BaseMvvmFragment
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.ManRouter

class ManDetalisFragment: BaseMvvmFragment<ManDetalisViewModel,ManRouter,FragmentDetalisManBinding>() {

    companion object {
        const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(id: String): ManDetalisFragment{
            val fragment = ManDetalisFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): ManDetalisViewModel {
        return  ViewModelProviders.of(this).get(ManDetalisViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_detalis_man
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(ID_EXTRA)
        if (id!=null)viewModel.setManId(id)
        else router?.goBack()
    }
}