package com.bobnevpavel.nbanews.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.bobnevpavel.domain.entities.Item
import com.bobnevpavel.nbanews.presentation.rv.diffutils.FingerprintDiffUtil
import com.bobnevpavel.nbanews.presentation.rv.fingerprints.ItemFingerprint
import java.lang.IllegalArgumentException

class MainScreenRVAdapter(private val fingerPrints: List<ItemFingerprint<*, *>>) :
    ListAdapter<Item,BaseViewHolder<ViewBinding, Item>>(FingerprintDiffUtil(fingerPrints)) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerPrints.find { it.getLayoutId() == viewType }?.getViewHolder(inflater, parent)?.let { it as BaseViewHolder<ViewBinding, Item> }?:throw IllegalArgumentException("ViewType not found $viewType")

    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerPrints.find { it.isRelativeItem(item) }?.getLayoutId()?:throw IllegalArgumentException("View type not found: $item")

    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(currentList[position])

    }

}