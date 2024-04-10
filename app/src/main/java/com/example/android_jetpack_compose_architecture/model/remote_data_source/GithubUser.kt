package com.example.android_jetpack_compose_architecture.model.remote_data_source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUser(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String?,
    @SerialName("avatar_url") val avatar_url: String?,
    @SerialName("blog") val blog: String?
)