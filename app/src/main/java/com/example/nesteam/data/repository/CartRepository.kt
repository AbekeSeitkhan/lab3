package com.example.nesteam.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nesteam.data.model.entity.Cart
import com.example.nesteam.data.model.entity.CartManager

data class CartRepository(private val context: Context){
    fun getCartItems(): LiveData<List<Cart>> {
        return MutableLiveData<List<Cart>>().apply {
            value = CartManager.getCartItems()
        }
    }

    suspend fun addToCart(item: Cart) {
        CartManager.addToCart(context, item)
    }

    suspend fun removeFromCart(gameId: Int) {
        CartManager.removeFromCart(gameId, context)
    }
}
