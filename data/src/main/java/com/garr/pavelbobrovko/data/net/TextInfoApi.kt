package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.TextInfoApiResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface TextInfoApi {

    @GET("comments")
    fun getMan(): Observable<ArrayList<TextInfoApiResponse>>

}