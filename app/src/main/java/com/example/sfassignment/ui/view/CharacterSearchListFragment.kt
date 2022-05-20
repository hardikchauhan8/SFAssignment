package com.example.sfassignment.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sfassignment.ui.adapters.CharacterListAdapter
import com.example.sfassignment.databinding.FragmentCharacterSearchListBinding
import com.example.sfassignment.utils.KeyboardUtils
import com.example.sfassignment.utils.gone
import com.example.sfassignment.utils.visible
import com.example.sfassignment.ui.viewmodel.CharacterSharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterSearchListFragment : Fragment() {

    private val characterSharedViewModel: CharacterSharedViewModel by viewModels()
    private lateinit var binding: FragmentCharacterSearchListBinding
    private lateinit var adapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterSearchListBinding.inflate(inflater, container, false)

        binding.rcvCharacterList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        setupObserver()

        adapter = CharacterListAdapter(listOf())
        binding.rcvCharacterList.adapter = adapter

        binding.imgSearch.setOnClickListener {
            searchCharacter(binding.tilCharacterSearch.editText?.text.toString())
        }

        binding.tilCharacterSearch.editText?.setOnEditorActionListener { _, _, _ ->
            searchCharacter(binding.tilCharacterSearch.editText?.text.toString())
            true
        }

        return binding.root
    }

    private fun setupObserver() {

        characterSharedViewModel.characters.observe(viewLifecycleOwner) { characterList ->
            binding.pbCharacterWait.gone()
            characterList?.let {
                if (characterList.isNotEmpty()) {
                    adapter.loadCharacters(characterList)
                    binding.tvNoListMessage.gone()
                    binding.rcvCharacterList.visible()
                } else {
                    binding.rcvCharacterList.gone()
                    binding.tvNoListMessage.visible()
                }
            }
        }

        characterSharedViewModel.error.observe(viewLifecycleOwner) {
            binding.pbCharacterWait.gone()
        }
    }

    private fun searchCharacter(query: String) {
        if (query.isNotEmpty()) {
            characterSharedViewModel.searchCharacter(query)
            binding.pbCharacterWait.visible()
            activity?.let { KeyboardUtils.hideKeyboard(it) }
        }
    }
}
