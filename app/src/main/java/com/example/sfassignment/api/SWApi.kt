package com.example.sfassignment.api

import com.example.sfassignment.model.CharacterDetailResponse
import com.example.sfassignment.model.CharacterFilmResponse
import com.example.sfassignment.model.CharacterSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SWApi {

    @GET("people")
    suspend fun searchCharacter(@Query("search") search: String): Response<CharacterSearchResponse>

    @GET
    suspend fun characterDetail(@Url url: String): Response<CharacterDetailResponse>

    @GET
    suspend fun getCharacterFilms(@Url filmUrl: String): Response<CharacterFilmResponse>
}