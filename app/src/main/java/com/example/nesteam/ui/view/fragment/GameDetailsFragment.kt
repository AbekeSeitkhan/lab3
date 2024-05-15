package com.example.nesteam.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.nesteam.R
import com.example.nesteam.data.model.entity.Game
import com.example.nesteam.data.network.ApiClient
import com.example.nesteam.data.repository.CartRepository
import com.example.nesteam.data.repository.GameRepository
import com.example.nesteam.databinding.FragmentGameDetailsBinding
import com.example.nesteam.ui.viewmodel.GameViewModel
import com.example.nesteam.ui.viewmodel.GameViewModelFactory
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailsFragment : Fragment() {
    private var _binding: FragmentGameDetailsBinding? = null
    private val binding get() = _binding!!
    private var gameId: Int? = null
    private var game: Game? = null
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameId = it.getInt("gameId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        viewModel.gameDetails.observe(viewLifecycleOwner, Observer { gameDetails ->
            game = gameDetails
            gameDetails?.let { updateUI(it) }
        })

        val gameId = arguments?.getInt("gameId") ?: throw IllegalArgumentException("Missing game ID")
        viewModel.loadGameDetails(gameId)

        binding.addToCartButton.setOnClickListener {
            game?.let {
                viewModel.addToCart(it)
                Toast.makeText(context, "${it.name} added to cart", Toast.LENGTH_SHORT).show()
            }
        }

        gameId?.let { viewModel.loadGameDetails(it) }
    }

    private fun setupViewModel() {
        val repository = GameRepository(ApiClient.apiService)
        val cartRepository = CartRepository(requireContext())
        val viewModelFactory = GameViewModelFactory(repository, cartRepository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        viewModel.gameDetails.observe(viewLifecycleOwner, Observer { game ->
            game?.let { updateUI(it) }
        })
    }

    private fun updateUI(game: Game) {
        binding.gameName.text = game.name
        binding.gameRating.text = "Rating: ${game.rating}"
        binding.gameDescription.text = Jsoup.parse(game.description).text()
        when(game.esrbRating?.id){
            0 -> {
                binding.esrbRatingImg.setImageResource(R.drawable.everyone)
            }
            1 -> {
                binding.esrbRatingImg.setImageResource(R.drawable.everyone_ten_plus)
            }
            2 -> {
                binding.esrbRatingImg.setImageResource(R.drawable.teens)
            }
            3 -> {
                binding.esrbRatingImg.setImageResource(R.drawable.mature)
            }
            4 -> {
                binding.esrbRatingImg.setImageResource(R.drawable.adults_only)
            }
            5 -> {
                binding.esrbRatingImg.setImageResource(R.drawable.rating_pending)
            }
        }
        Glide.with(this@GameDetailsFragment)
            .load(game.backgroundImage)
            .into(binding.gameImg)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(gameId: Int) = GameDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt("gameId", gameId)
            }
        }
    }
}