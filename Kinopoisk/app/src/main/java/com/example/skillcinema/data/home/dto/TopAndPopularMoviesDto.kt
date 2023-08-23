package com.example.skillcinema.data.home.dto

import com.example.skillcinema.entity.home.TopAndPopularMoviesEntity


data class TopAndPopularMoviesDto(
    override val pagesCount: Int,
    override val films: List<MovieDto>
) : TopAndPopularMoviesEntity
