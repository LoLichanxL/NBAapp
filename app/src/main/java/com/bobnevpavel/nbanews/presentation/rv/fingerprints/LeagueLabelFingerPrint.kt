package com.bobnevpavel.nbanews.presentation.rv.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bobnevpavel.domain.entities.Item
import com.bobnevpavel.domain.entities.LeagueLabel
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.LeagueTitleSimpleItemBinding
import com.bobnevpavel.nbanews.presentation.rv.BaseViewHolder

class LeagueLabelFingerPrint : ItemFingerprint<LeagueTitleSimpleItemBinding, LeagueLabel> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is LeagueLabel
    }

    override fun getLayoutId(): Int {
        return R.layout.league_title_simple_item
    }

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<LeagueTitleSimpleItemBinding, LeagueLabel> {
        return LeagueViewHolder(LeagueTitleSimpleItemBinding.inflate(layoutInflater, parent, false))
    }


    class LeagueViewHolder(binding: LeagueTitleSimpleItemBinding) :
        BaseViewHolder<LeagueTitleSimpleItemBinding, LeagueLabel>(binding) {
        override fun onBind(item: LeagueLabel) {
            binding.leagueTitle.text = item.league
            binding.leagueCountry.text = item.leagueTitle
        }
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<LeagueLabel> {
        return diffUtil
    }

    private val diffUtil = object : DiffUtil.ItemCallback<LeagueLabel>() {
        override fun areItemsTheSame(oldItem: LeagueLabel, newItem: LeagueLabel): Boolean =
            oldItem.league.equals(newItem.league)

        override fun areContentsTheSame(oldItem: LeagueLabel, newItem: LeagueLabel): Boolean =
            oldItem == newItem
    }


}
