package com.nowik.moviesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nowik.moviesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    /**
     * Pas eu le temps mais ça devrait être un fragment container avec un nav graph et deux
     * fragments (liste + détail)
     */

    private lateinit var binding: ActivityMainBinding
    private val searchResultsAdapter = SearchResultsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        handleSearch()
    }

    private fun setUpRecyclerView() {
        binding.searchResultsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.searchResultsRecyclerView.adapter = searchResultsAdapter
    }

    private fun handleSearch() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    GlobalScope.launch {
                        val movies = ImdbApiClient.getInstance()
                            .searchMovies(ImdbApiClient.API_KEY, query).results
                        searchResultsAdapter.movies = movies
                        runOnUiThread {
                            searchResultsAdapter.notifyDataSetChanged()
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

}