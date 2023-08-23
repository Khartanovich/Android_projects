package com.example.skillcinema.data.search


import com.example.skillcinema.entity.search.CountryAndGenresForSearchEntity
import com.google.gson.annotations.SerializedName

data class CountryAndGenresForSearchDto(
    @SerializedName("countries")
    override val countries: List<CountrySearchDto>,
    @SerializedName("genres")
    override val genres: List<GenreSearchDto>
) : CountryAndGenresForSearchEntity