package com.garr.pavelbobrovko.omertextest.presentation.screen.man.list

import android.util.Log
import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.domain.useCases.GetManUseCase
import com.garr.pavelbobrovko.omertextest.app.TestApp
import com.garr.pavelbobrovko.omertextest.presentation.base.BaseViewModel
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.ManRouter
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.items.ManListAdapter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ManListViewModel: BaseViewModel<ManRouter>() {

    val adapter = ManListAdapter()

    @Inject
    lateinit var getManUseCase: GetManUseCase

    init {

        TestApp.appComponent.inject(this)

        val disposable = getManUseCase.get()
            .subscribeBy (
                onNext = {
                    Log.d("myLog", it.toString())
                    adapter.itemList = it
                    adapter.notifyDataSetChanged()
                },
                onError = {
                    router?.showError(it)
                }
            )

        addToDisposable(disposable)

        adapter.onItemClickListener = object : ManListAdapter.OnItemClickListener{
            override fun onClick(man: Man) {
                router?.goToDetalisFragment(man.id)
            }

        }
    }

}