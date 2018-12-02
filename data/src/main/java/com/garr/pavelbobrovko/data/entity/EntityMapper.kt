package com.garr.pavelbobrovko.data.entity

import android.util.Log
import com.garr.pavelbobrovko.domain.entity.Man

fun List<TextInfoApiResponse>.transformToDomainManList(imageApiResponse: Array<String> ): List<Man>{
    val list: List<Man> = imageApiResponse.transformToPathOfManList()

    val listSize: Int
    if (list.size < this.size){
        listSize = list.size
    }else listSize = this.size

    for (i in 0 until listSize - 1){
        val resp = this[i]
        val man = list[i]
        man.id = resp.id.toString()
        man.name = resp.name
        man.about = resp.body
        man.email = resp.email
    }

    return list
}

private fun Array<String>.transformToPathOfManList(): List<Man>{
    Log.d("myLogs", "list.size: ${this.toString()}")
    return this.map {
        it.transformToPathOfMan()
    }
}

private fun String.transformToPathOfMan(): Man{
    return Man(photoUrl = this)
}