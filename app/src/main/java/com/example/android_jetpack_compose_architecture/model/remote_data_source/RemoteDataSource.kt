package com.example.android_jetpack_compose_architecture.model.remote_data_source

interface RemoteDataSource {
    suspend fun getGithubUser(userName: String): GithubUser
}