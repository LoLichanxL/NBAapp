package com.bobnevpavel.nbanews.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.FragmentNewsBinding
import com.bobnevpavel.nbanews.presentation.activities.MainActivity

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val newsUrl = "NewsUrlFromBundle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        subscribeListeners()

        if (arguments != null) {
            Log.d(newsUrl, requireArguments().get("url").toString())
            setupWebView(requireArguments().get("url").toString())
        }

        return binding.root
    }

    private fun subscribeListeners() {
        binding.arrowBack.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_news_fragment_to_main_screen)
            if (requireActivity() is MainActivity) {
                (requireActivity() as MainActivity).binding.root.transitionToStart()
            }
        })
    }

    private fun setupWebView(url: String) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
    }

}