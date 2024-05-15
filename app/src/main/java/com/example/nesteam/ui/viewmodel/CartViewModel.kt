package com.example.nesteam.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nesteam.data.model.entity.Cart
import com.example.nesteam.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application, private val cartRepository: CartRepository) : AndroidViewModel(application) {

    val cartItems: LiveData<List<Cart>> = cartRepository.getCartItems()

    fun addToCart(cart: Cart) {
        viewModelScope.launch {
            cartRepository.addToCart(cart)
        }
    }

    fun removeFromCart(gameId: Int) {
        viewModelScope.launch {
            cartRepository.removeFromCart(gameId)
        }
    }
}

class CartViewModelFactory(private val application: Application, private val cartRepository: CartRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(application, cartRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}