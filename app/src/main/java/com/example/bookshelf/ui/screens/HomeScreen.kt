package com.example.bookshelf.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.VolumeInfo

@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    retryAction:()-> Unit,
    modifier: Modifier = Modifier
){
    when(bookUiState){
        is BookUiState.Error -> ErrorScreen()
        is BookUiState.Success -> BookGrid(bookUiState.data, modifier = modifier)
        is BookUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun LoadingScreen() {
    Text(text = "loading")
}

@Composable
fun ErrorScreen(){
    Text(text = "something went wrong")
}

@Composable
fun BookGrid(bookData: MutableList<VolumeInfo>, modifier: Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)){

        items(items = bookData, itemContent = {
            book ->
            if (book != null) {
                BookCard(book = book,modifier.padding(start = 10.dp))
            }
        })
    }
}

@Composable
fun BookCard(book: VolumeInfo, modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .background(MaterialTheme.colorScheme.surface)
        .fillMaxSize()
        .padding(15.dp)) {
        Column(modifier.padding(20.dp)) {
            if (book != null) {

                Text(book.title.toString())

                var url: String = book.imageLinks?.thumbnail.toString().trim()
                url = url.replace("http","https")

                BookImage(imgUrl = url)
            
            }else{
                Text(text = "error")
            }
        }

    }

}
@Composable
fun BookImage(imgUrl:String){
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgUrl)
            .crossfade(true)
            .build(),
        contentDescription = "a book",
        contentScale = ContentScale.FillBounds,
        error = painterResource(R.drawable.ic_launcher_background),
        placeholder = painterResource(R.drawable.ic_launcher_foreground)
    )
}

