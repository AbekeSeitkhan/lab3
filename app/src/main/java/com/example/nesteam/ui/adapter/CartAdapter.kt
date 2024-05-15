package com.example.nesteam.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nesteam.data.model.entity.Cart
import com.example.nesteam.data.model.entity.CartManager
import com.example.nesteam.databinding.ItemCartBinding

class CartAdapter(private var cartItems: MutableList<Cart>, private val context: Context, private val onDeleteClick: (Cart) -> Unit) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: Cart, onDeleteClick: (Cart)-> Unit) {
            binding.gameName.text = cartItem.game.name
            binding.genre.text = cartItem.game.genres.joinToString(", "){it.name}
            binding.metacriticCart.text = "${cartItem.game.metacritic.toString()}/100"
            binding.deleteButton.setOnClickListener { onDeleteClick(cartItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position]){
            deleteItem(it)
        }
    }

    private fun deleteItem(cartItem: Cart) {
        val position = cartItems.indexOf(cartItem)
        if (position != -1) {
            CartManager.removeFromCart(cartItem.game.id, context)
            cartItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = cartItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<Cart>) {
        cartItems = newItems.toMutableList()
        notifyDataSetChanged()  // Consider using DiffUtil for better performance
    }
}