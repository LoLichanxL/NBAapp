package com.bobnevpavel.nbanews.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.FragmentLoginBinding
import com.bobnevpavel.nbanews.presentation.activities.MainActivity
import com.bobnevpavel.nbanews.presentation.utils.LoginViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            findNavController().navigate(R.id.action_login_fragment_to_main_screen)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupViewPager()
        hideNavView()
        return binding.root
    }

    private fun setupViewPager() {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            binding.loginViewPager.adapter = LoginViewPagerAdapter(activity)
        }
        TabLayoutMediator(binding.loginTabLayout, binding.loginViewPager) {tab, position ->
            when(position){
                0 -> tab.text = "Login"
                else -> tab.text = "Sign Up"
            }
        }.attach()
    }

    private fun hideNavView() {
        (requireActivity() as MainActivity).binding.root.transitionToEnd()
    }



}