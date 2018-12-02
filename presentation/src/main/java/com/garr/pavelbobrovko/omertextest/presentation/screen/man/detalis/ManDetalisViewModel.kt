package com.garr.pavelbobrovko.omertextest.presentation.screen.man.detalis

import android.databinding.ObservableField
import com.garr.pavelbobrovko.domain.useCases.GetManUseCase
import com.garr.pavelbobrovko.omertextest.app.TestApp
import com.garr.pavelbobrovko.omertextest.presentation.base.BaseViewModel
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.ManRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ManDetalisViewModel: BaseViewModel<ManRouter>() {

    @Inject
    lateinit var getManUseCase: GetManUseCase

    val ivPhoto = ObservableField<String>("")
    val tvName = ObservableField<String>("")
    val tvEmail = ObservableField<String>("")
    val tvAbout = ObservableField<String>("")

    fun setManId(id: String) {
        TestApp.appComponent.inject(this)

        val disposable = getManUseCase.get(id)
                .subscribeBy (
                        onNext = {
                            ivPhoto.set(it.photoUrl)
                            tvName.set(it.name)
                            tvEmail.set(it.email)
                            tvAbout.set(it.about)

                        },
                        onError = {
                            router?.showError(it)
                        }
                )

        addToDisposable(disposable)
    }
}