package com.example.jetpackccompose.data.model.character


import com.example.jetpackccompose.entity.CharacterEntity
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("created")
    override val created: String,
    @SerializedName("episode")
    override val episode: List<String>,
    @SerializedName("gender")
    override val gender: String,
    @SerializedName("id")
    override val id: Int,
    @SerializedName("image")
    override val image: String,
    @SerializedName("location")
    override val location: Location,
    @SerializedName("name")
    override val name: String,
    @SerializedName("origin")
    override val origin: Origin,
    @SerializedName("species")
    override val species: String,
    @SerializedName("status")
    override val status: String,
    @SerializedName("type")
    override val type: String,
    @SerializedName("url")
    override val url: String
) : CharacterEntity