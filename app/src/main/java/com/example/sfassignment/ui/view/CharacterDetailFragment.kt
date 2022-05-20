package com.example.sfassignment.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sfassignment.R
import com.example.sfassignment.ui.adapters.CharacterFilmListAdapter
import com.example.sfassignment.databinding.FragmentCharacterDetailBinding
import com.example.sfassignment.data.model.CharacterDetailResponse
import com.example.sfassignment.utils.UnitConverterUtil
import com.example.sfassignment.utils.gone
import com.example.sfassignment.utils.visible
import com.example.sfassignment.ui.viewmodel.CharacterSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val characterSharedViewModel: CharacterSharedViewModel by viewModels()
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
        detailBinding.lifecycleOwner = viewLifecycleOwner

        setupObserver()

        detailBinding.rcvFilms.layoutManager = LinearLayoutManager(activity)
        filmAdapter = CharacterFilmListAdapter(listOf())
        detailBinding.rcvFilms.adapter = filmAdapter

        characterDetails?.films?.let { films ->
            detailBinding.pbFilmsWait.visible()
            characterSharedViewModel.getFilms(films)
        }

        detailBinding.swHeight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                detailBinding.tvHeight.text = "Height: ${characterDetails?.height}"
            else
                detailBinding.tvHeight.text =
                    "Height: ${UnitConverterUtil.getFeet(characterDetails?.height)}"
        }

        return detailBinding.root
    }

    private fun setupObserver() {
        characterSharedViewModel.films.observe(viewLifecycleOwner) {
            detailBinding.pbFilmsWait.gone()
            it?.let {
                filmAdapter.loadFilmsData(it)
            }
        }
    }
}