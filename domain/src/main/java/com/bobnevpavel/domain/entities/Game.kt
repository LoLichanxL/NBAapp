package com.bobnevpavel.domain.entities

data class Game(
    val firstTeam: String,
    val secondTeam: String,
    val firstTeamScore: Int,
    val secondTeamScore: Int,
    val gameStatus: GameStatus,
    val firstTeamId: Int,
    val secondTeamId: Int,
    val date:String?
): Item()
