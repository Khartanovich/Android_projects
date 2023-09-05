package com.example.jetpackccompose.entity

import com.example.jetpackccompose.data.model.character.Location
import com.example.jetpackccompose.data.model.character.Origin

interface CharacterEntity {
    val created: String
    val episode: List<String>
    val gender: String
    val id: Int
    val image: String
    val location: Location
    val name: String
    val origin: Origin
    val species: String
    val status: String
    val type: String
    val url: String
}
