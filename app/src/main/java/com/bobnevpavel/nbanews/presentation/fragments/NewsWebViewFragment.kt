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
import com.bobnevpavel.nbanews.databinding.FragmentNewsWebViewBinding
import com.bobnevpavel.nbanews.presentation.activities.MainActivity

class NewsWebViewFragment : Fragment() {
    private lateinit var binding:FragmentNewsWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentNewsWebViewBinding.inflate(layoutInflater, container, false)
        subscribeListeners()

        if (arguments != null) {
            Log.d("Navigate", requireArguments().get("url").toString())
            setUrlToWebView(requireArguments().get("url").toString())
        }
        return binding.root
    }
    private fun subscribeListeners(){
        binding.arrowBack.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_news_fragment_to_main_screen)
            if (requireActivity() is MainActivity){
                (requireActivity() as MainActivity).binding.root.transitionToStart()
            }
        })
    }
    private fun setUrlToWebView(url:String){
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
    }

}