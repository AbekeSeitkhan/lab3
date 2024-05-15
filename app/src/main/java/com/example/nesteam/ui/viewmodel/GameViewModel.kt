package com.example.nesteam.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nesteam.data.model.entity.Cart
import com.example.nesteam.data.model.entity.Game
import com.example.nesteam.data.repository.CartRepository
import com.example.nesteam.data.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(private val repository: GameRepository, private val cartRepository: CartRepository):ViewModel() {

    private val _gameDetails = MutableLiveData<Game?>()
    val gameDetails: LiveData<Game?> = _gameDetails

    fun loadGameDetails(gameId: Int) {
        viewModelScope.launch {
            val game = repository.getGameById(gameId)
            _gameDetails.postValue(game)
        }
    }

    fun addToCart(game: Game) {
        viewModelScope.launch {
            val cartItem = Cart(game, 1)  // Assuming quantity is always 1 when added from details
            cartRepository.addToCart(cartItem)
        }
    }

}

class GameViewModelFactory(private val repository: GameRepository, private val cartRepository: CartRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repository, cartRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}