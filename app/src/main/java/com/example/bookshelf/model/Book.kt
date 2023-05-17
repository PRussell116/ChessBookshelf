package com.example.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("id") val id: String,
    @SerialName("selfLink") val selfLink: String,
)
//
//@Serializable
//data class VolumeInfo(
//    val title:String,
//    val authors:List<String>
//)

// BookVolume -> Book -> VolumeInfo