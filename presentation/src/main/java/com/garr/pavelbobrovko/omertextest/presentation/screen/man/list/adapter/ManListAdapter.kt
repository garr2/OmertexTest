package com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR.viewModel
import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.omertextest.R
import com.garr.pavelbobrovko.omertextest.databinding.ManListItemBinding
import com.garr.pavelbobrovko.omertextest.presentation.base.recycler.BaseRecyclerAdapter
import com.garr.pavelbobrovko.omertextest.presentation.base.recycler.BaseViewHolder
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.ManRouter
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.items.ManListItemViewModel
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
        val binding: ManListItemBinding = DataBindingUtil.setContentView(viewGroup.context as Activity, R.layout.man_list_item)
        return BaseViewHolder(binding,ManListItemViewModel())
    }

    interface OnItemClickListener{
        fun onClick(man: Man)
    }
}