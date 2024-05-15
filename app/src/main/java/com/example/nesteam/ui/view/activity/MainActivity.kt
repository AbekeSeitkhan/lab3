package com.example.nesteam.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nesteam.ui.view.fragment.GamesListFragment
import com.example.nesteam.R
import com.example.nesteam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_cart -> openFragment(GamesListFragment())
                R.id.nav_games -> openFragment(GamesListFragment())
                R.id.nav_search -> openFragment(GamesListFragment())
            }
            true
        }
        openFragment(GamesListFragment())
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}

