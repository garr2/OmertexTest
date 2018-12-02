package com.garr.pavelbobrovko.domain.repositories

import com.garr.pavelbobrovko.domain.entity.Man
import io.reactivex.Observable

interface ManRepository: BaseRepository {

    fun get(): Observable<List<Man>>

    fun get(id: String): Observable<Man>
}