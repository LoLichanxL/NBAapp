package com.bobnevpavel.nbanews.presentation.rv.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bobnevpavel.domain.entities.Game
import com.bobnevpavel.domain.entities.GameStatus
import com.bobnevpavel.domain.entities.Item
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.SimpleMatchItemBinding
import com.bobnevpavel.nbanews.presentation.rv.BaseViewHolder
import com.bobnevpavel.nbanews.presentation.utils.GameUtil

class GameFingerPrint : ItemFingerprint<SimpleMatchItemBinding, Game> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is Game
    }

    override fun getLayoutId(): Int {
        return R.layout.simple_match_item
    }

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<SimpleMatchItemBinding, Game> {
        return GameViewHolder(SimpleMatchItemBinding.inflate(layoutInflater, parent, false))
    }

    class GameViewHolder(binding: SimpleMatchItemBinding) :
        BaseViewHolder<SimpleMatchItemBinding, Game>(binding) {

        override fun onBind(item: Game) {
            binding.firstTeamTitle.text = GameUtil.getFullTeamTitle(item.firstTeamId)
            binding.secondTeamTitle.text = GameUtil.getFullTeamTitle(item.secondTeamId)
            binding.firstTeamIcon.setImageResource(GameUtil.getTeamIcon(item.firstTeamId))
            binding.secondTeamIcon.setImageResource(GameUtil.getTeamIcon(item.secondTeamId))
            when(item.gameStatus){
                GameStatus.InProgress -> {
                    binding.gameStatus.text = "Live"
                    binding.firstTeamScore.text = item.firstTeamScore.toString()
                    binding.secondTeamScore.text = item.secondTeamScore.toString()
                }
                GameStatus.Scheduled -> {
                    binding.gameStatus.text = GameUtil.getDate(item.date)
                    binding.firstTeamScore.text = "-"
                    binding.secondTeamScore.text = "-"
                }
            }
        }
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<Game> {
        return diffUtil
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean =
            oldItem.firstTeamId == newItem.firstTeamId && oldItem.secondTeamId == newItem.secondTeamId

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem == newItem

    }


}