package com.example.android_jetpack_compose_architecture.model.repository

interface UserRepository {
    suspend fun getUser(userName: String): User
}