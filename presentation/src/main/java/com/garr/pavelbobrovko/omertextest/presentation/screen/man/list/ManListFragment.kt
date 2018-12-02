package com.garr.pavelbobrovko.omertextest.presentation.screen.man.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.garr.pavelbobrovko.omertextest.R
import com.garr.pavelbobrovko.omertextest.databinding.FragmentListManBinding
import com.garr.pavelbobrovko.omertextest.presentation.base.BaseMvvmFragment
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.ManRouter

class ManListFragment: BaseMvvmFragment<ManListViewModel,ManRouter,FragmentListManBinding>() {

    companion object {
        fun getInstance(): ManListFragment{
            return ManListFragment()
        }
    }

    override fun prodiveViewModel(): ManListViewModel {
        return ViewModelProviders.of(this).get(ManListViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_list_man
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvListOfMan.adapter = viewModel.adapter
        binding.rvListOfMan.layoutManager = LinearLayoutManager(view.context)
        binding.rvListOfMan.setHasFixedSize(true)
    }
}