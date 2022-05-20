package com.example.sfassignment.data.repository

import com.example.sfassignment.data.model.CharacterDetailResponse
import com.example.sfassignment.data.model.CharacterFilmResponse
import com.example.sfassignment.data.model.CharacterSearchResponse
import com.example.sfassignment.utils.Result

interface CharacterRepository {

    suspend fun searchCharacter(name: String): Result<CharacterSearchResponse?>

    suspend fun characterDetail(url: String): Result<CharacterDetailResponse?>

    suspend fun getCharacterFilms(url: String): Result<CharacterFilmResponse?>
}