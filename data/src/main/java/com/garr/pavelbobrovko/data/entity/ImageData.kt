package com.garr.pavelbobrovko.data.entity

data class ImageData(val photo: Array<Item>): DataEntity {


    data class Item(val id: String = "", val owner: String = "", val secret: String = "", val server: String = "",
                    val farm: String = "")
}