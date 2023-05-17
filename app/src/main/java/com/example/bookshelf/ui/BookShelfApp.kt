package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.screens.BookViewModel
import com.example.bookshelf.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfApp(modifier: Modifier = Modifier){
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        //todo
    }) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it), color = MaterialTheme.colorScheme.surface) {
            val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
            HomeScreen(bookUiState =bookViewModel.bookUiState, retryAction = bookViewModel::getBookData)


        }


    }
}