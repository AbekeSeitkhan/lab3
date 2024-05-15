package com.example.nesteam.data.model.entity

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

data class Cart(
    val game: Game,
    var quantity: Int = 1
) : Serializable

object CartManager {
    private val cartItems = mutableListOf<Cart>()

    fun addToCart(context: Context, item: Cart) {
        val existingItem = cartItems.find { it.game.id == item.game.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
        saveCartItems(context)
    }

    private fun saveCartItems(context: Context) {
        val sharedPreferences = context.getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(cartItems)
        editor.putString("cartItems", json)
        editor.apply()
    }

    fun loadCartItems(context: Context) {
        val sharedPreferences = context.getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("cartItems", null)
        val type = object : TypeToken<List<Cart>>() {}.type
        cartItems.clear()
        cartItems.addAll(gson.fromJson(json, type) ?: listOf())
    }

    fun removeFromCart(gameId: Int, context: Context) {
        cartItems.removeAll { it.game.id == gameId }
        saveCartItems(context)  // Call save method if you're persisting changes
    }

    fun getCartItems(): List<Cart> = cartItems
}
