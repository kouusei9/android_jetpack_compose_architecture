package com.example.android_jetpack_compose_architecture.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_jetpack_compose_architecture.model.repository.User
import com.example.android_jetpack_compose_architecture.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    sealed class UiState {
        // init
        object Initial : UiState()

        // loading
        object Loading : UiState()

        // reading success
        data class Success(val user: User) : UiState()

        // reading failure
        data class Failure(val errorMessage: String) : UiState()
    }

    val uiState: MutableState<UiState> = mutableStateOf(UiState.Initial)

    val searchQuery: MutableState<String> = mutableStateOf("")

    fun onSearchTaped() {
        val searchQuery: String = searchQuery.value

        viewModelScope.launch {
            uiState.value = UiState.Loading

            runCatching {
                repository.getUser(searchQuery)
            }.onSuccess {
                uiState.value = UiState.Success(it)
            }.onFailure {
                System.out.println(it.printStackTrace())
                uiState.value = UiState.Failure(it.message.toString())
            }
        }
    }
}