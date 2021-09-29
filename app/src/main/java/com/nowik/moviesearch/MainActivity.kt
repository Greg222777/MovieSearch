package com.nowik.moviesearch

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.nowik.moviesearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * Pas eu le temps mais ça devrait être un fragment container avec un nav graph et deux
     * fragments (liste + détail)
     */

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}