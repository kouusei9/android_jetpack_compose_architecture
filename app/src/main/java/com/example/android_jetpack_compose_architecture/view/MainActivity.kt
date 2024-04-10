package com.example.android_jetpack_compose_architecture.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_jetpack_compose_architecture.model.repository.User
import com.example.android_jetpack_compose_architecture.ui.theme.TestGitRequestTheme
import com.example.android_jetpack_compose_architecture.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TestGitRequestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun MainView(mainViewModel: MainViewModel) {
    val uiState: MainViewModel.UiState by mainViewModel.uiState

    Column {
        SearchView(
            searchQuery = mainViewModel.searchQuery,
            onSearchButtonTapped = { mainViewModel.onSearchTaped() }
        )
        when (uiState) {
            is MainViewModel.UiState.Initial -> {
                InitialView()
            }

            is MainViewModel.UiState.Loading -> {
                LoadingView()
            }

            is MainViewModel.UiState.Success -> {
                UserDetialView(user = uiState.requireUser())
            }

            is MainViewModel.UiState.Failure -> {
                ErrorView(message = (uiState as MainViewModel.UiState.Failure).errorMessage)
            }
        }
    }
}

private fun MainViewModel.UiState.requireUser(): User {
    if (this !is MainViewModel.UiState.Success) throw IllegalArgumentException("user is not loaded")
    return user
}

private fun MainViewModel.UiState.requireErrorMsg(): String {
    if (this !is MainViewModel.UiState.Failure) throw IllegalArgumentException("error not happened")
    return errorMessage
}

@Composable
fun UserDetialView(
    user: User
) {
    Column {
        Text(text = user.userId.value.toString())
        Text(text = user.name)
        Text(text = user.avatarImage.url.value)
        Text(text = user.blogUrl.value)
    }
}

@Composable
fun ErrorView(
    message: String
) {
    Text(text = message)
}

@Composable
fun LoadingView() {
    Text(text = "Loading")
}

@Composable
fun InitialView() {
    Text(text = "Please Input Github Account name")
}

@Composable
fun SearchView(
    searchQuery: MutableState<String>,
    onSearchButtonTapped: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        TextField(
            label = {
                Text("Input GitHub Account")
            },
            value = searchQuery.value,
            onValueChange = { text ->
                searchQuery.value = text
            })

        Button(
            onClick = {
                onSearchButtonTapped()
            }) {
            Text(text = "Search")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestGitRequestTheme {
//        MainView()
    }
}