package com.umitytsr.dictionaryapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.dictionaryapp.data.model.local.DictionaryWord
import com.umitytsr.dictionaryapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchAdapter.DictionaryItemClickListener {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.searchButton.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToDetailerFragment(s.toString())
                    )
                }
            }

        })
        collectData()
        return binding.root
    }

    private fun collectData() {
        searchViewModel.deleteOldSearch()
        searchViewModel.loadSearchHistory()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchHistory.collectLatest {
                    searchRecyclerView(it)
                }
            }
        }
    }

    private fun searchRecyclerView(wordHistory: List<DictionaryWord>) {
        val _adapter = SearchAdapter(wordHistory, this@SearchFragment)
        val _layout = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        with(binding.historyRecyclerView) {
            adapter = _adapter
            layoutManager = _layout
            setHasFixedSize(false)
        }
    }

    override fun dictionaryItemClicked(word: String) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailerFragment(word)
        )
    }
}