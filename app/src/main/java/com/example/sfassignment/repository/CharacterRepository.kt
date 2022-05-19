package com.example.sfassignment.repository

import com.example.sfassignment.model.CharacterDetailResponse
import com.example.sfassignment.model.CharacterFilmResponse
import com.example.sfassignment.model.CharacterSearchResponse
import com.example.sfassignment.utils.Result

interface CharacterRepository {

    suspend fun searchCharacter(name: String): Result<CharacterSearchResponse?>

    suspend fun characterDetail(url: String): Result<CharacterDetailResponse?>

    suspend fun getCharacterFilms(url: String): Result<CharacterFilmResponse?>
}