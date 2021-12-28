package com.bobnevpavel.nbanews.presentation.rv

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bobnevpavel.domain.entities.Item


abstract class BaseViewHolder<out V: ViewBinding, in I: Item> (val binding: V): RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: I)
}