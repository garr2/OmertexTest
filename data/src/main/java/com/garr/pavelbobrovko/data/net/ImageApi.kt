package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.ImageApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("rest/")
    fun getImageData(@Query("method")method: String,
                     @Query("api_key")apiKey: String,
                     @Query("format")format: String,
                     @Query("nojsoncallback")nojsoncallback: Int): Observable<ImageApiResponse>
}