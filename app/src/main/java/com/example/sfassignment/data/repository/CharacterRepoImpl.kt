package com.example.sfassignment.data.repository

import com.example.sfassignment.data.api.ApiDataSource
import com.example.sfassignment.data.model.CharacterDetailResponse
import com.example.sfassignment.data.model.CharacterFilmResponse
import com.example.sfassignment.data.model.CharacterSearchResponse
import com.example.sfassignment.utils.Result
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(private val dataSource: ApiDataSource) :
    CharacterRepository {
    override suspend fun searchCharacter(name: String): Result<CharacterSearchResponse?> {
        return when (val response = dataSource.searchCharacter(name)) {
            is Result.Success -> {
                Result.Success(response.data)
            }
            is Result.Error -> {
                Result.Error(response.message)
            }
        }
    }

    override suspend fun characterDetail(url: String): Result<CharacterDetailResponse?> {
        return when (val response = dataSource.characterDetail(url)) {
            is Result.Success -> {
                Result.Success(response.data)
            }
            is Result.Error -> {
                Result.Error(response.message)
            }
        }
    }

    override suspend fun getCharacterFilms(url: String): Result<CharacterFilmResponse?> {
        return when (val response = dataSource.getCharacterFilms(url)) {
            is Result.Success -> {
                Result.Success(response.data)
            }
            is Result.Error -> {
                Result.Error(response.message)
            }
        }
    }
}