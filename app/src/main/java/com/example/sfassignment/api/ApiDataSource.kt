package com.example.sfassignment.api

import com.example.sfassignment.model.CharacterDetailResponse
import com.example.sfassignment.model.CharacterFilmResponse
import com.example.sfassignment.model.CharacterSearchResponse
import com.example.sfassignment.utils.Result
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val swApi: SWApi) {
    suspend fun searchCharacter(name: String): Result<CharacterSearchResponse?> {
        val response = swApi.searchCharacter(name)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            Result.Error(response.errorBody()?.string())
        }
    }

    suspend fun characterDetail(url: String): Result<CharacterDetailResponse?> {
        val response = swApi.characterDetail(url)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            Result.Error(response.errorBody()?.string())
        }
    }

    suspend fun getCharacterFilms(url: String): Result<CharacterFilmResponse?> {
        val response = swApi.getCharacterFilms(url)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            Result.Error(response.errorBody()?.string())
        }
    }
}