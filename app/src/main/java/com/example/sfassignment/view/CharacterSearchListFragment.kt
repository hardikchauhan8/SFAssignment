package com.example.sfassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sfassignment.adapters.CharacterListAdapter
import com.example.sfassignment.databinding.FragmentCharacterSearchListBinding
import com.example.sfassignment.utils.gone
import com.example.sfassignment.utils.visible
import com.example.sfassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterSearchListFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var adapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharacterSearchListBinding.inflate(inflater, container, false)

        binding.rcvCharacterList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = CharacterListAdapter(listOf())
        binding.rcvCharacterList.adapter = adapter

        mainViewModel.characters.observe(viewLifecycleOwner) { characterList ->
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

        binding.imgSearch.setOnClickListener {
            mainViewModel.searchCharacter(binding.tilCharacterSearch.editText?.text.toString())
        }

        binding.tilCharacterSearch.editText?.setOnEditorActionListener { _, _, _ ->
            mainViewModel.searchCharacter(binding.tilCharacterSearch.editText?.text.toString())
            true
        }

        return binding.root
    }
}

