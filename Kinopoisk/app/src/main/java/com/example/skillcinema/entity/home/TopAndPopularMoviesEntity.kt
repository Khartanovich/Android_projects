package com.example.skillcinema.entity.home

import com.example.skillcinema.data.home.dto.MovieDto

interface TopAndPopularMoviesEntity {
        val pagesCount: Int
        val films: List<MovieEntity>
}