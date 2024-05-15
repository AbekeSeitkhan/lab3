package com.example.nesteam.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nesteam.R
import com.example.nesteam.data.model.entity.CartManager
import com.example.nesteam.data.repository.CartRepository
import com.example.nesteam.databinding.FragmentCartBinding
import com.example.nesteam.ui.adapter.CartAdapter
import com.example.nesteam.ui.viewmodel.CartViewModel
import com.example.nesteam.ui.viewmodel.CartViewModelFactory

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = CartViewModelFactory(requireActivity().application, CartRepository(requireContext()))
        viewModel = ViewModelProvider(this, factory).get(CartViewModel::class.java)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(mutableListOf(), requireContext()) { cart ->
            viewModel.removeFromCart(cart.game.id)
        }
        binding.cartRecyclerView.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewModel() {
        viewModel.cartItems.observe(viewLifecycleOwner, Observer { items ->
            cartAdapter.updateItems(items)
        })
    }
}