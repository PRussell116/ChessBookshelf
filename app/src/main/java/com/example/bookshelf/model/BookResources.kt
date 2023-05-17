package com.example.bookshelf.model

data class BookResources(
    val items: List<BookVolume?>,
    val kind: String?,
    val totalItems:Int?,
)