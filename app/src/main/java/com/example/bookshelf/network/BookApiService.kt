package com.example.bookshelf.network

//import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.model.BookResources
import com.example.bookshelf.model.BookVolume
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApiService{
    @GET("v1/volumes?q=chess&maxResults=40")
    suspend fun getBookData(): BookResources


    @GET("v1/volumes/{bookId}")
    suspend fun getIndividualBook(@Path("bookId")bookId:String):BookVolume

//    @GET("v1/volumes/<volumeId>")
//    suspend fun getBookInfo(): List<VolumeInfo>
}