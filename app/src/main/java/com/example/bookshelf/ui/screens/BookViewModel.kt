package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookRepo
import com.example.bookshelf.model.BookResources
import com.example.bookshelf.model.VolumeInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookUiState{
    data class Success(val data: MutableList<VolumeInfo>):BookUiState

    object Error: BookUiState

    object Loading: BookUiState
}

class BookViewModel(private val bookRepo: BookRepo):ViewModel(){
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set
    init {
        getBookData()
    }
    fun getBookData(){
        viewModelScope.launch {
            bookUiState = BookUiState.Loading

            bookUiState = try{

                var allBookData : BookResources = bookRepo.getBookData()
                var bookData: MutableList<VolumeInfo> = mutableListOf()
                for(vol in allBookData.items){
                    if (vol != null) {
                        bookData.add(vol.volumeInfo)
                    }


                }

                BookUiState.Success(bookData)

                }catch (e:IOException){
                    BookUiState.Error
                }catch (e : HttpException){
                    BookUiState.Error
                }


        }

    }
    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookShelfApplication)
                val bookRepo = application.container.bookRepo
                BookViewModel(bookRepo = bookRepo)
            }
        }
    }
}