package com.garr.pavelbobrovko.omertextest.presentation.base.recycler

abstract class BaseItemViewModel<Entity> {

    abstract fun bindItem(item: Entity, position: Int)

    open fun onItemClick() {
    }
}