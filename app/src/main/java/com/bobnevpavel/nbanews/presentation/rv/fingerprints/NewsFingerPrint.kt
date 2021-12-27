package com.bobnevpavel.nbanews.presentation.rv.fingerprints

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bobnevpavel.domain.entities.Item
import com.bobnevpavel.domain.entities.News
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.MainNewsBinding
import com.bobnevpavel.nbanews.presentation.activities.MainActivity
import com.bobnevpavel.nbanews.presentation.rv.BaseViewHolder
import com.bobnevpavel.nbanews.presentation.utils.GameUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.ContextWrapper




class NewsFingerPrint() : ItemFingerprint<MainNewsBinding, News> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is News
    }

    override fun getLayoutId(): Int {
        return R.layout.main_news
    }

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup

    ): RecyclerView.ViewHolder {
        return NewsViewHolder(MainNewsBinding.inflate(layoutInflater, parent, false), getActivityFromContext(parent.context)!!)
    }


    class NewsViewHolder(binding: MainNewsBinding, val activity: Activity): BaseViewHolder<MainNewsBinding, News>(binding){
        override fun onBind(item: News) {
            binding.cardBackground.setOnClickListener(View.OnClickListener {
                val bundle = Bundle()
                bundle.putString("url", item.url)
                launchAnimation(it, bundle)
                Log.d("Navigation:", item.url)
            })
            binding.newsTitle.text = item.title
            binding.newsDate.text = item.timeAgo
            binding.newsSportTitle.text = "Basketball"
            binding.newsImage.setImageResource(GameUtil.getTeamIcon(item.teamId))
        }

        private fun launchAnimation(it:View, bundle:Bundle){
            CoroutineScope(Dispatchers.Main).launch {
                if(activity is MainActivity){
                    activity.binding.root.transitionToEnd()
                }
                binding.root.transitionToEnd()
                delay(550L)
                it.findNavController()
                    .navigate(R.id.action_main_screen_to_news_fragment, bundle)
            }
        }
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<News> {
        return diffUtil
    }
    private val diffUtil = object : DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean = newItem.title.equals(oldItem.title)

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = newItem == oldItem

    }
    private fun getActivityFromContext(context: Context):Activity? {
        if (context is ContextWrapper) {
            return if (context is Activity) {
                context as Activity
            } else {
                getActivityFromContext((context as ContextWrapper).baseContext)
            }
        }
        return null
    }
}