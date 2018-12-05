package com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.garr.pavelbobrovko.domain.entity.Man
import com.garr.pavelbobrovko.omertextest.databinding.ManListItemBinding
import com.garr.pavelbobrovko.omertextest.presentation.base.recycler.BaseViewHolder

class ManItemViewHolder(binding: ManListItemBinding,
                        viewModel: ManListItemViewModel)
    : BaseViewHolder<Man, ManListItemViewModel, ManListItemBinding>(binding,viewModel) {

    companion object {
        fun create (parent: ViewGroup, viewModel: ManListItemViewModel): ManItemViewHolder{

            val binding = ManListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

            return ManItemViewHolder(binding,viewModel)
        }
    }
}