package com.garr.pavelbobrovko.omertextest.presentation.base.recycler

import android.support.v7.widget.RecyclerView
import com.garr.pavelbobrovko.domain.entity.Man
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerAdapter<Entity,
        VM: BaseItemViewModel<Entity>>

    : RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>(){

    var itemList: List<Entity> = emptyList()

    val clickItemSubject = PublishSubject.create<ItemClick<Entity>>()

    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM, *>, position: Int) {
        holder.bind(itemList[position], position)
    }

    override fun getItemCount(): Int = itemList.size

    fun addItems(items: List<Entity>) {
        val startPos = itemList.size
        itemList.plus(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun clearItems() {
        itemList = emptyList()
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener{
            val pos = holder.adapterPosition
            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
            holder.viewModel.onItemClick()
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}