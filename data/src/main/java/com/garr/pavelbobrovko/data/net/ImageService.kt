package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.ImageApiResponse
import io.reactivex.Observable

class ImageService(apiUrl: String): RestService(apiUrl) {

    private lateinit var restApi: ImageApi

    private val method = "flickr.photos.getRecent"
    private val apiKey = "b9cb8775e4c743d93368712fa6d4a791"
    private val format = "json"
    private val nojsoncallback = 1

    override fun buildApiClass() {
        restApi = retrofit.create(ImageApi::class.java)
    }

    fun  get(): Observable<ImageApiResponse>{
        return restApi.getImageData(method,apiKey,format,nojsoncallback)
    }
}