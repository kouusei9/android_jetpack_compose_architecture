package com.example.android_jetpack_compose_architecture.model.repository

import com.example.android_jetpack_compose_architecture.model.remote_data_source.GithubUser
import com.example.android_jetpack_compose_architecture.model.remote_data_source.RemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override suspend fun getUser(userName: String): User {
        return remoteDataSource.getGithubUser(userName).toUser()
    }
}

private fun GithubUser.toUser(): User {
    return User(
        userId = UserId(id),
        name = name ?: "",
        avatarImage = NetworkImage(URL(this.avatar_url ?: "")),
        blogUrl = URL(this.blog ?: "")
    )
}