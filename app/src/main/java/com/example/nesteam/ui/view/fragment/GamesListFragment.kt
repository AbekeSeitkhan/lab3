package com.example.nesteam.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nesteam.data.model.entity.GameResponse
import com.example.nesteam.data.model.entity.Result
import com.example.nesteam.data.network.ApiClient
import com.example.nesteam.databinding.FragmentGamesListBinding
import com.example.nesteam.ui.adapter.GamesListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesListFragment : Fragment() {

    var results:List<Result>? = emptyList()

    companion object{
        fun newInstance() = GamesListFragment()
    }

    private var _binding : FragmentGamesListBinding? = null

    private val binding
        get() = _binding!!

    private val adapter: GamesListAdapter by lazy {
        GamesListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGamesListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gamesList.adapter = adapter

        ApiClient.apiService.getGames("ad61cc3d0cf246ffb16f9f76affe0844").enqueue(object : Callback<GameResponse>{
            override fun onResponse(call: Call<GameResponse>, gameResponse: Response<GameResponse>) {
                if (gameResponse.isSuccessful) {
                    results = gameResponse.body()?.results
                    adapter.submitList(results)

                } else {
                    Log.e("ApiError", "Error: ${gameResponse.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
            }
        })
    }

}