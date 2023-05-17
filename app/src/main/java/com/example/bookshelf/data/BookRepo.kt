package com.example.bookshelf.data

import com.example.bookshelf.model.BookResources
import com.example.bookshelf.model.BookVolume
import com.example.bookshelf.network.BookApiService

interface BookRepo{
    suspend fun getBookData():BookResources
    suspend fun getIndividualBook(id: String): BookVolume
}
class BookNetworkdata(
    private val bookApiService: BookApiService
):BookRepo{
    override suspend fun getBookData(): BookResources {
        return bookApiService.getBookData()
    }

    override suspend fun getIndividualBook(id:String): BookVolume {
        return bookApiService.getIndividualBook(id)
    }
}