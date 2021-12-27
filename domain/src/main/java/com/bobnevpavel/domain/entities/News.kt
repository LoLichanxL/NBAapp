package com.bobnevpavel.domain.entities

data class News(
    val title: String,
    val url: String,
    val teamId: Int,
    val playerID: Int,
    val content: String,
    val source: String,
    val timeAgo: String
) : Item()