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
import androidx.navigation.fragment.navArgs
import com.umitytsr.dictionaryapp.databinding.FragmentDetailerBinding
import dagger.hilt.android.AndroidEntryPoint
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
        binding = FragmentDetailerBinding.inflate(inflater,container,false)
        val word = args.word
        collectDetailerData(word)
        return binding.root
    }

    private fun collectDetailerData(word: String){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                with(detailerViewModel){
                    launch {
                        insertWord(word)
                    }
                }
            }
        }
    }
}