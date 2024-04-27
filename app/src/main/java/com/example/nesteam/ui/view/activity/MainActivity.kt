package com.example.nesteam.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nesteam.ui.view.fragment.GamesListFragment
import com.example.nesteam.R
import com.example.nesteam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, GamesListFragment.newInstance())
            .commit()

    }
}


