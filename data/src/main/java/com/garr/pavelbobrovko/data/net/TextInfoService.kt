package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.TextInfoApiResponse
import io.reactivex.Observable

class TextInfoService( apiUrl: String): RestService(apiUrl) {

    private lateinit var restApi: TextInfoApi

    override fun buildApiClass() {
        restApi = retrofit.create(TextInfoApi::class.java)
    }

    fun get(): Observable<ArrayList<TextInfoApiResponse>>{
        return restApi.getMan()
    }
}