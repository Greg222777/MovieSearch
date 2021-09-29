package com.nowik.moviesearch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nowik.moviesearch.ImdbApiClient
import com.nowik.moviesearch.SearchResultsAdapter
import com.nowik.moviesearch.databinding.FragmentSearchBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val searchResultsAdapter = SearchResultsAdapter()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        handleSearch()
    }

    private fun setUpRecyclerView() {
        binding.searchResultsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
                        requireActivity().runOnUiThread {
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

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Search"
    }
}