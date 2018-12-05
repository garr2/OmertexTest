package com.garr.pavelbobrovko.data.entity

import com.garr.pavelbobrovko.domain.entity.Man

fun ArrayList<TextInfoApiResponse>.transformToDomainManList(imageApiResponse: ArrayList<String> ): ArrayList<Man>{

    if (this.size> imageApiResponse.size){
        return imageApiResponse.mapIndexed { position, photoUrl ->
            photoUrl.transformToMan(this[position])
        } as ArrayList<Man>
    } else {
         return this.mapIndexed{position, textInfoResp ->
             if (!imageApiResponse.isEmpty()) {
                 textInfoResp.transformToMan(imageApiResponse[position])
             } else {
                 textInfoResp.transformToMan(null)
             }

         } as ArrayList<Man>
    }
}

private fun String.transformToMan(textInfoResp: TextInfoApiResponse): Man{
    return Man(photoUrl = this, id = textInfoResp.id.toString(),name = textInfoResp.name,
        email = textInfoResp.email, about = textInfoResp.body)
}

private fun TextInfoApiResponse.transformToMan(imageList: String?): Man{
    return Man(id = id.toString(),name = name,email = email,about = body,photoUrl = imageList ?: "")
}