package com.example.nesteam.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nesteam.data.network.ApiClient
import com.example.nesteam.data.repository.ResultRepository
import com.example.nesteam.databinding.FragmentGamesListBinding
import com.example.nesteam.ui.adapter.GamesListAdapter
import com.example.nesteam.ui.viewmodel.ResultViewModel
import com.example.nesteam.ui.viewmodel.ResultViewModelFactory

class GamesListFragment : Fragment() {

    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!
    private val adapter: GamesListAdapter by lazy {
        GamesListAdapter()
    }

    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGamesListBinding.inflate(inflater, container, false)
        binding.gamesList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val repository = ResultRepository(ApiClient.apiService) // Adjust as needed for your setup
        val viewModelFactory = ResultViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        viewModel.results.observe(viewLifecycleOwner, Observer {
            // Update your UI here
            adapter.submitList(it)
        })

        viewModel.loadGames()
    }

}