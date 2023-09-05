package com.example.jetpackccompose.data

import com.example.jetpackccompose.data.model.character.CharacterDto
import com.example.jetpackccompose.data.model.location.Locations
import com.example.jetpackccompose.data.model.character.AllCharacter
import com.example.jetpackccompose.data.model.episode.EpisodeDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com/"

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rickAndMortyCharacter : RickAndMortyCharacterApi = retrofit.create(RickAndMortyCharacterApi::class.java)
}

interface RickAndMortyCharacterApi {
    @GET("api/character")
    suspend fun getAllCharacterApi(@Query("page") page: Int) : AllCharacter

    @GET("api/location")
    suspend fun getAllLocationsApi(@Query("page") page: Int) : Locations

    @GET("api/character/{id}")
    suspend fun getOneCharacter(@Path("id") id: Int): CharacterDto

    @GET("api/episode/{listId}")
    suspend fun getEpisode(@Path("listId") listId: List<Int>): List<EpisodeDto>
}