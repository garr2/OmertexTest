package com.garr.pavelbobrovko.data.repositories

import android.util.Log
import com.garr.pavelbobrovko.data.entity.ImageApiResponse
import com.garr.pavelbobrovko.data.entity.transformToDomainManList
import com.garr.pavelbobrovko.data.net.ImageService
import com.garr.pavelbobrovko.data.net.TextInfoService
import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.domain.repositories.ManRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class ManRepositoryImpl(val textInfoService: TextInfoService, val imageService: ImageService): ManRepository {

    companion object {
        const val TIME_BUFFER = 1800000
    }
    private var currentUpdate: Long = 0
    private var dataList = ArrayList<Man>()



    override fun get(): Observable<List<Man>> {

        if (isDataUpdated() && dataList != emptyList<Man>()){
            return Observable.just(dataList)

        }else return textInfoService.get()
            .flatMap {textInfoResponse ->
                getStringImageData()
                    .flatMap {
                        Log.d("myLogs", "get.list.size: ${it.size}")
                        Log.d("myLogs", "get.Textlist.size: ${textInfoResponse.size}")
                        Observable.just(textInfoResponse.transformToDomainManList(it))
                            .doOnNext {
                                currentUpdate = System.currentTimeMillis()
                                dataList = it

                            }
                            .onErrorReturn {
                                if (dataList.isEmpty()){
                                    throw it
                                }else dataList
                            }
                    }
            }
    }

    override fun get(id: String): Observable<Man> {
        var man = Man()
        if (!isDataUpdated()){
            return get().flatMap {
                Observable.just(findMan(it,id))
            }
        }else return Observable.just(findMan(dataList,id))
    }

    private fun isDataUpdated(): Boolean =
         currentUpdate > (System.currentTimeMillis() - TIME_BUFFER)


    private fun getStringImageData(): Observable<ArrayList<String>>{
        return imageService.get()
            .flatMap{
                Log.d("myLogs", "getStringImageData.list.size: ${it.photos.photo.size}")
                Observable.just(generatePhotoUrl(it))
            }
    }

    private fun generatePhotoUrl(imageApiResponse: ImageApiResponse): ArrayList<String>{

        val list = ArrayList<String>(imageApiResponse.photos.photo.size)
        for (i in 0 until imageApiResponse.photos.photo.size){
            Log.d("myLogs", "generate: $i")
            val item = imageApiResponse.photos.photo[i]
            val url = "https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}_${item.secret}.jpg"
            list.add(url)
        }
        return list
    }

    private fun findMan(list: List<Man>, id: String): Man{
        var man = Man()
        for (item in list){
            if (item.id == id) {
                man = item
                break
            }
        }

        return man
    }
}