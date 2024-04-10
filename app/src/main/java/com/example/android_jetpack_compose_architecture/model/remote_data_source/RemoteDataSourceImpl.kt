package com.example.android_jetpack_compose_architecture.model.remote_data_source

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient
) : RemoteDataSource {
    override suspend fun getGithubUser(userName: String): GithubUser {
        var response = apiClient.getGithubUser(userName)
        if (response.isSuccessful) {
            val githubUser: GithubUser = requireNotNull(response.body())
            return githubUser
        }
        throw HttpException()
    }
}

class HttpException : Throwable()