package com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.items

import android.databinding.ObservableField
import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.omertextest.presentation.base.recycler.BaseItemViewModel
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.ManRouter

class ManListItemViewModel(): BaseItemViewModel<Man>() {

    val name = ObservableField<String>("")
    val photoUrl = ObservableField<String>("")

    override fun bindItem(item: Man, position: Int) {
       name.set(item.name)
        photoUrl.set(item.photoUrl)
    }
}