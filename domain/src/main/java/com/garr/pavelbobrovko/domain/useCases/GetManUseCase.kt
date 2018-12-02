package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.ManRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetManUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val manRepository: ManRepository): BaseUseCase(postExecutorThread) {

    fun get(): Observable<List<Man>>{
        return manRepository.get()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun get(id: String): Observable<Man>{
        return manRepository.get(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}