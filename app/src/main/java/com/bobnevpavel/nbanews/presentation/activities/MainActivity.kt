package com.bobnevpavel.nbanews.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.appComponent
import com.bobnevpavel.nbanews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if (findNavController(R.id.fragment_container).currentDestination!!.id == R.id.news_fragment){
            binding.root.transitionToStart()
        }
        super.onBackPressed()
    }
}