package com.example.sfassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfassignment.R
import com.example.sfassignment.adapters.CharacterFilmListAdapter
import com.example.sfassignment.databinding.FragmentCharacterDetailBinding
import com.example.sfassignment.model.CharacterDetailResponse
import com.example.sfassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var detailBinding: FragmentCharacterDetailBinding
    private lateinit var filmAdapter: CharacterFilmListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_character_detail,
            container,
            false
        )

        val characterDetails = arguments?.get("characterDetails") as CharacterDetailResponse?

        detailBinding.characterDetails = characterDetails
        detailBinding.lifecycleOwner = this

        setupObserver()

        detailBinding.rcvFilms.layoutManager = LinearLayoutManager(activity)
        filmAdapter = CharacterFilmListAdapter(listOf())
        detailBinding.rcvFilms.adapter = filmAdapter

        characterDetails?.films?.let { films ->
            mainViewModel.getFilms(films)
        }

        return detailBinding.root
    }

    private fun setupObserver() {
        mainViewModel.films.observe(viewLifecycleOwner) {
            it?.let {
                filmAdapter.loadFilmsData(it)
            }
        }
    }
}