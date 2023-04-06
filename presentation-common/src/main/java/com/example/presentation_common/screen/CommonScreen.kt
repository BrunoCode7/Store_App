package com.example.presentation_common.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.presentation_common.state.UiState

@Composable
fun<T:Any> CommonScreen(state: UiState<T>, onSuccess: @Composable (T) -> Unit) {
    when (state){
        is UiState.Loading ->{
            LoadingView()
        }
        is UiState.Error ->{
            ErrorView(state.errorMessage)
        }
        is UiState.Success -> {
            onSuccess(state.data)
        }
    }
}


@Composable
fun ErrorView(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar {
            Text(text = errorMessage)
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =
        Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}