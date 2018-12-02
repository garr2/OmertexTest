package com.garr.pavelbobrovko.omertextest.inject

import com.garr.pavelbobrovko.omertextest.presentation.screen.man.detalis.ManDetalisViewModel
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.ManListViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(viewModel: ManListViewModel)

    fun inject(viewModel: ManDetalisViewModel)
}