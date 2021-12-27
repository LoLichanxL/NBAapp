package com.bobnevpavel.nbanews.presentation.rv.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.bobnevpavel.domain.entities.Item

interface ItemFingerprint <V:ViewBinding, I: Item>{
    fun isRelativeItem(item: Item):Boolean

    @LayoutRes
    fun getLayoutId():Int

    fun getViewHolder(layoutInflater: LayoutInflater, parent:ViewGroup): ViewHolder

    fun getDiffUtil():DiffUtil.ItemCallback<I>
}