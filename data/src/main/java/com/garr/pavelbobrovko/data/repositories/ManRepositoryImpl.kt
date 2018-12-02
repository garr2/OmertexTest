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
    private var dataList: List<Man> = emptyList()



    override fun get(): Observable<List<Man>> {

        if (isDataUpdated() && dataList != emptyList<Man>()){
            return Observable.just(dataList)

        }else return textInfoService.get()
            .flatMap {textInfoResponse ->
                getStringImageData()
                    .flatMap {
                        Log.d("myLogs", "list.size: ${it.size}")
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
            get().subscribeBy(
                onComplete = {
                 man = findMan(dataList,id)
                }
            )
        }else man = findMan(dataList,id)

        return Observable.just(man)
    }

    private fun isDataUpdated(): Boolean =
         currentUpdate > (System.currentTimeMillis() - TIME_BUFFER)


    private fun getStringImageData(): Observable<Array<String>>{
        return imageService.get()
            .flatMap{
                Log.d("myLogs", "list.size: ${it.photos.photo.size}")
                Observable.just(generatePhotoUrl(it))
            }
    }

    private fun generatePhotoUrl(imageApiResponse: ImageApiResponse): Array<String>{

        val list: Array<String> = Array(imageApiResponse.photos.photo.size) {"1"}
        for (i in 0 until imageApiResponse.photos.photo.size){
            val item = imageApiResponse.photos.photo[i]
            val url = "https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}_${item.secret}.png"
            list[i] = url
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