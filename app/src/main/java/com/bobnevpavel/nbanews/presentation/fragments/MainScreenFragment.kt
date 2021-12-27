package com.bobnevpavel.nbanews.presentation.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobnevpavel.domain.common.LoadingState
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.appComponent
import com.bobnevpavel.nbanews.databinding.FragmentMainScreenBinding
import com.bobnevpavel.nbanews.presentation.rv.MainScreenRVAdapter
import com.bobnevpavel.nbanews.view.MainScreenFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenFragmentViewModel

    @Inject
    lateinit var viewModelFactory: MainScreenFragmentViewModel.Factory

    private val adapter: MainScreenRVAdapter by lazy { MainScreenRVAdapter(viewModel.fingerPrints) }
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelFactory.create(MainScreenFragmentViewModel::class.java)
        fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        subscribeOnViewModel()
        setupRecyclerView()
        return binding.root
    }


    private fun subscribeOnViewModel() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            binding.mainScreenRecyclerView.postDelayed( {adapter.submitList(viewModel.data.value)}, 10L)
        })
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchAllData()
        }
    }

    private fun setupRecyclerView() {
        binding.mainScreenRecyclerView.adapter = adapter
        binding.mainScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}