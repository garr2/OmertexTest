package com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.items

import android.app.Activity
import android.databinding.DataBindingUtil
import android.view.ViewGroup
import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.omertextest.R
import com.garr.pavelbobrovko.omertextest.databinding.ManListItemBinding
import com.garr.pavelbobrovko.omertextest.presentation.base.recycler.BaseRecyclerAdapter
import com.garr.pavelbobrovko.omertextest.presentation.base.recycler.BaseViewHolder
import io.reactivex.rxkotlin.subscribeBy

class ManListAdapter
    : BaseRecyclerAdapter<Man,ManListItemViewModel>() {

    var onItemClickListener: OnItemClickListener? = null

    init {
        clickItemSubject.subscribeBy{
            onItemClickListener?.onClick(it.item)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int)
            : BaseViewHolder<Man, ManListItemViewModel, ManListItemBinding> {
        return ManItemViewHolder.create(viewGroup,ManListItemViewModel())
    }

    interface OnItemClickListener{
        fun onClick(man: Man)
    }
}