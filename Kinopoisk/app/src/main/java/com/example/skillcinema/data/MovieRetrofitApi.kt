package com.example.skillcinema.ui.home

import com.example.skillcinema.data.home.dto.*
import com.example.skillcinema.data.home.dto.staff.InformationAboutStaffDto
import com.example.skillcinema.data.search.CountryAndGenresForSearchDto
import com.example.skillcinema.data.search.SearchFilmsDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "ab18f47f-c8ce-4558-a356-3923662a6582"
private const val BASE_URL = "https://kinopoiskapiunofficial.tech"

interface MovieListApi {
    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films")
    suspend fun searchFilm(
        @Query("page") page: Int,
        @Query("keyword") keyword: String?,
        @Query("country") country: Int,
        @Query("genres") genres: Int,
        @Query("ratingFrom") ratingFrom: Int,
        @Query("ratingTo") ratingTo: Int,
        @Query("yearFrom") yearFrom: Int,
        @Query("yearTo") yearTo: Int,
        @Query("order") order: String,
        @Query("type") type: String
    ): SearchFilmsDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/filters")
    suspend fun searchCountryAndGenresForSearch(): CountryAndGenresForSearchDto
    //
    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/premieres")
    suspend fun getPremiersMovies(@Query("year") year: Int, @Query("month") month: String): MovieListDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun getTopMovies(@Query("page") page: Int): TopAndPopularMoviesDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getPopularsMovies(@Query("page") page: Int): TopAndPopularMoviesDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/{id}")
    suspend fun getInfoAboutFilm(@Path("id") id: Int): InformationAboutFilmDto?

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v1/staff")
    suspend fun getAllActors(@Query("filmId") filmId: Int): List<AllActorsModelDto>

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/{id}/images")
    suspend fun getImagesFilm(
        @Path("id") id: Int,
        @Query("type") type: String,
        @Query("page") page: Int
    ): ImagesGalleryDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/{id}/similars")
    suspend fun getSimilarFilms(@Path("id") id: Int): SimilarFilmsDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v1/staff/{id}")
    suspend fun getInformationAboutStaff(@Path("id") id: Int): InformationAboutStaffDto
}

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: MovieListApi = retrofit.create(MovieListApi::class.java)
}