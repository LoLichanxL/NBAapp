package com.bobnevpavel.data.mappers

import android.util.Log
import com.bobnevpavel.data.entities.AllGamesResponseItem
import com.bobnevpavel.data.entities.NewsResponse
import com.bobnevpavel.domain.entities.Game
import com.bobnevpavel.domain.entities.GameStatus
import com.bobnevpavel.domain.entities.News


class NbaApiMapper {
    fun mapGamesResponseToGames(data: List<AllGamesResponseItem>): List<Game> {
        Log.d("Games", data[0].DateTime.split("-").toString())
        return data.map {
            when (it.Status) {
                "Final" -> Game(
                    it.HomeTeam,
                    it.AwayTeam,
                    it.HomeTeamScore,
                    it.AwayTeamScore,
                    GameStatus.Final,
                    it.HomeTeamID,
                    it.AwayTeamID,
                    it.DateTime
                )
                "Scheduled" -> {
                    Game(
                        it.HomeTeam,
                        it.AwayTeam,
                        it.HomeTeamScore,
                        it.AwayTeamScore,
                        GameStatus.Scheduled,
                        it.HomeTeamID,
                        it.AwayTeamID,
                        it.DateTime
                    )
                }
                "Postponed" -> Game(
                    it.HomeTeam,
                    it.AwayTeam,
                    it.HomeTeamScore,
                    it.AwayTeamScore,
                    GameStatus.Postponed,
                    it.HomeTeamID,
                    it.AwayTeamID,
                    it.DateTime
                )
                "Canceled" -> Game(
                    it.HomeTeam,
                    it.AwayTeam,
                    it.HomeTeamScore,
                    it.AwayTeamScore,
                    GameStatus.Canceled,
                    it.HomeTeamID,
                    it.AwayTeamID,
                    it.DateTime
                )
                "F/OT" -> Game(
                    it.HomeTeam,
                    it.AwayTeam,
                    it.HomeTeamScore,
                    it.AwayTeamScore,
                    GameStatus.FOT,
                    it.HomeTeamID,
                    it.AwayTeamID,
                    it.DateTime
                )
                "InProgress" -> Game(
                    it.HomeTeam,
                    it.AwayTeam,
                    it.HomeTeamScore,
                    it.AwayTeamScore,
                    GameStatus.InProgress,
                    it.HomeTeamID,
                    it.AwayTeamID,
                    it.DateTime
                )
                else -> throw IllegalArgumentException("Wrong game status ${it.Status}")
            }
        }
    }

    fun mapNewsResponseToNews(data: List<NewsResponse>): List<News> {
        return data.map {
            News(it.Title, it.Url, it.TeamID, it.PlayerID, it.Content, it.Source, it.TimeAgo)
        }
    }
}