package com.example.jetpackccompose.data.model.episode


import com.example.jetpackccompose.entity.EpisodeEntity
import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("air_date")
    override val airDate: String,
    @SerializedName("characters")
    override val characters: List<String>,
    @SerializedName("created")
    override val created: String,
    @SerializedName("episode")
    override val episode: String,
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    override val name: String,
    @SerializedName("url")
    override val url: String
): EpisodeEntity