package com.example.android_jetpack_compose_architecture.model.repository


data class User(
    val userId: UserId,
    val name: String,
    val avatarImage: NetworkImage,
    val blogUrl: URL
)
