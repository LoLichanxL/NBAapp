package com.bobnevpavel.nbanews.presentation.rv.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.bobnevpavel.domain.entities.Item
import com.bobnevpavel.nbanews.presentation.rv.fingerprints.ItemFingerprint

class FingerprintDiffUtil(val fingerprints: List<ItemFingerprint<*, *>>) :
    DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        if (oldItem::class != newItem::class) return false

        return getItemCallback(oldItem).areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        if (oldItem::class != newItem::class) return false
        return getItemCallback(oldItem).areContentsTheSame(oldItem, newItem)
    }

    private fun getItemCallback(
        item: Item
    ): DiffUtil.ItemCallback<Item> = fingerprints.find { it.isRelativeItem(item) }
        ?.getDiffUtil()
        ?.let { it as DiffUtil.ItemCallback<Item> }
        ?: throw IllegalStateException("DiffUtil not found for $item")
}