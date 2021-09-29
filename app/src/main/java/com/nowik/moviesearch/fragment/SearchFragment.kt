package com.nowik.moviesearch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nowik.moviesearch.R
import com.nowik.moviesearch.adapter.SearchResultsAdapter
import com.nowik.moviesearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), SearchResultsAdapter.OnMovieClickListener {

    private val searchResultsAdapter = SearchResultsAdapter(this)
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeResults()
        handleSearch()
    }

    private fun setUpRecyclerView() {
        binding.searchResultsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.searchResultsRecyclerView.adapter = searchResultsAdapter
    }

    private fun observeResults() {
        viewModel.searchResultLiveData.observe(viewLifecycleOwner) { movies ->
            searchResultsAdapter.movies = movies
            requireActivity().runOnUiThread {
                searchResultsAdapter.notifyDataSetChanged()

                if (movies.isNotEmpty()) {
                    binding.searchResultsRecyclerView.visibility = View.VISIBLE
                    binding.placeHolderLayout.visibility = View.GONE
                }

            }
        }
    }

    private fun handleSearch() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    viewModel.searchMovies(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    override fun onMovieClicked(movieId: Int) {
        val bundle = Bundle().apply {
            putInt(MovieDetailsFragment.ARGS_MOVIE_ID, movieId)
        }
        findNavController().navigate(R.id.action_search_to_details, bundle)
    }
}