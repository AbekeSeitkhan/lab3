package com.example.nesteam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nesteam.R
import com.example.nesteam.data.model.entity.Result
import com.example.nesteam.databinding.GameItemBinding
import com.example.nesteam.ui.view.fragment.GameDetailsFragment
import com.example.nesteam.util.GamesListDiffUtil

class GamesListAdapter : ListAdapter<Result, GamesListAdapter.ViewHolder>(GamesListDiffUtil()){

    inner class ViewHolder(
        private val binding: GameItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedGame = getItem(position)
                    val gameId = selectedGame.id
                    (itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container_view, GameDetailsFragment.newInstance(gameId))
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
        }

        fun bind(result: Result) {
            with(binding){
                name.text = result.name
                released.text = result.released
                Glide.with(itemView.context)
                    .load(result.backgroundImage)
                    .into(backgroundImage)
                rating.text = result.rating.toString()
                metacritic.text = result.metacritic.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GameItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}