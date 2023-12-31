package com.umitytsr.dictionaryapp.ui.detailer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.dictionaryapp.data.model.remote.synonyms.WordSynonymsResponse
import com.umitytsr.dictionaryapp.domain.model.TypeOfItemWord
import com.umitytsr.dictionaryapp.databinding.FragmentDetailerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailerFragment : Fragment() {
    private lateinit var binding: FragmentDetailerBinding
    private val detailerViewModel: DetailerViewModel by viewModels()
    private val args: DetailerFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailerBinding.inflate(inflater, container, false)
        binding.backgroundButton.setOnClickListener {
            findNavController().navigate(
                DetailerFragmentDirections.actionDetailerFragmentToSearchFragment()
            )
        }
        val word = args.word
        detailerViewModel.getWordDetails(word)
        collectDetailerData(word)
        return binding.root
    }

    private fun collectDetailerData(word: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(detailerViewModel) {
                    launch {
                        insertWord(word)
                    }

                    launch {
                        wordMeaning.collectLatest {
                            detailerRecyclerView(it)
                        }
                    }
                    launch {
                        wordSynonyms.collectLatest {
                            synonymsRecyclerView(it)
                        }
                    }
                }
            }
        }
    }

    private fun detailerRecyclerView(wordMeanings: List<TypeOfItemWord>) {
        val _adapter = DetailerAdapter(wordMeanings)
        val _layout = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        with(binding.detailerRecyclerView) {
            adapter = _adapter
            layoutManager = _layout
        }
    }

    private fun synonymsRecyclerView(wordSynonyms: List<WordSynonymsResponse>) {
        val _adapter = SynonymsAdapter(wordSynonyms)
        val spanCount = 4
        val _layout = GridLayoutManager(requireContext(), spanCount)
        with(binding.synonymsRecyclerView) {
            adapter = _adapter
            layoutManager = _layout
        }
    }
}