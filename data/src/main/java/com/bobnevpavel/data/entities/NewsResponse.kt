package com.bobnevpavel.data.entities

data class NewsResponse(
    val Author: String,
    val Categories: String,
    val Content: String,
    val NewsID: Int,
    val OriginalSource: String,
    val OriginalSourceUrl: String,
    val PlayerID: Int,
    val PlayerID2: Int,
    val Source: String,
    val Team: String,
    val Team2: String,
    val TeamID: Int,
    val TeamID2: Int,
    val TermsOfUse: String,
    val TimeAgo: String,
    val Title: String,
    val Updated: String,
    val Url: String
)