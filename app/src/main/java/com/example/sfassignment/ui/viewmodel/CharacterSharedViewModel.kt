package com.example.sfassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sfassignment.data.model.CharacterDetailResponse
import com.example.sfassignment.data.model.CharacterFilmResponse
import com.example.sfassignment.data.repository.CharacterRepoImpl
import com.example.sfassignment.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterSharedViewModel @Inject constructor(private val repository: CharacterRepoImpl) : ViewModel() {

    private val characterList = MutableLiveData<List<CharacterDetailResponse?>?>()
    val characters: LiveData<List<CharacterDetailResponse?>?>
        get() = characterList

    private val filmsList = MutableLiveData<List<CharacterFilmResponse?>?>()
    val films: LiveData<List<CharacterFilmResponse?>?>
        get() = filmsList

    private val apiError = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = apiError

    fun searchCharacter(name: String) {

        viewModelScope.launch {
            when (val response = repository.searchCharacter(name)) {
                is Result.Success -> {
                    characterList.postValue(response.data?.results)
                }
                is Result.Error -> {
                    apiError.postValue(response.message)
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFilms(films: List<String?>) {
        val tempList = mutableListOf<CharacterFilmResponse?>()
        viewModelScope.launch {
            films.map {
                it?.let {
                    val response = async { repository.getCharacterFilms(it) }
                    response.await()
                    if (response.getCompleted() is Result.Success) {
                        tempList.add((response.getCompleted() as Result.Success<CharacterFilmResponse?>).data)
                    }
                }
            }
            filmsList.postValue(tempList)
        }
    }
}
