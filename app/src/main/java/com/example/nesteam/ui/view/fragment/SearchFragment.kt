package com.example.nesteam.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nesteam.R
import com.example.nesteam.data.network.ApiClient
import com.example.nesteam.data.repository.ResultRepository
import com.example.nesteam.databinding.FragmentSearchBinding
import com.example.nesteam.ui.adapter.GamesListAdapter
import com.example.nesteam.ui.viewmodel.ResultViewModel
import com.example.nesteam.ui.viewmodel.ResultViewModelFactory


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter: GamesListAdapter by lazy {
        GamesListAdapter()
    }
    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupSearch()
    }

    private fun setupViewModel() {
        val repository = ResultRepository(ApiClient.apiService) // Ensure your ApiClient is correctly initialized
        val viewModelFactory = ResultViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        viewModel.searchResults.observe(viewLifecycleOwner, Observer {
            // Update your UI here
            adapter.submitList(it)
        })
    }

    private fun setupSearch() {
        binding.searchButton.setOnClickListener {
            val query = binding.searchEt.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchGames(query)
            } else {
                Toast.makeText(context, "Please enter a search term", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}