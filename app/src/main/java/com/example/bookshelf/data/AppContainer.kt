package com.example.bookshelf.data

import com.example.bookshelf.network.BookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer{
    val bookRepo: BookRepo
}
class DefaultAppContainer:AppContainer{
    private val BASE_URL = "https://www.googleapis.com/books/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitService: BookApiService by lazy{
        retrofit.create(BookApiService::class.java)
    }

    override val bookRepo: BookRepo by lazy{
        BookNetworkdata(retrofitService)
    }

}