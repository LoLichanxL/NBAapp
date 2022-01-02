package com.bobnevpavel.nbanews.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bobnevpavel.nbanews.presentation.activities.MainActivity
import com.bobnevpavel.nbanews.presentation.fragments.SignInFragment
import com.bobnevpavel.nbanews.presentation.fragments.SignUpFragment
import org.jetbrains.annotations.NotNull

class LoginViewPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    private val fragmentsCount = 2
    override fun getItemCount(): Int {
        return fragmentsCount
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return SignInFragment()
            }
            1 -> {
                return SignUpFragment()
            }
            else -> return SignUpFragment()
        }
    }

}