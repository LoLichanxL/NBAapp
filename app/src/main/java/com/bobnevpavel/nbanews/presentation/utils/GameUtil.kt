package com.bobnevpavel.nbanews.presentation.utils

import com.bobnevpavel.nbanews.R

class GameUtil {
    companion object {
        fun getTeamIcon(teamId: Int): Int {
            return when (teamId) {
                1 -> R.drawable.ic_washington_logo
                2 -> R.drawable.ic_charlotte_hornets_logo
                3 -> R.drawable.ic_atlanta_hawks_logo
                4 -> R.drawable.ic_miami_heat_logo
                5 -> R.drawable.ic_orlando_magic_logo
                6 -> R.drawable.ic_new_york_knicks_logo
                7 -> R.drawable.ic_philadelphia_76ers_logo
                8 -> R.drawable.ic_brooklyn_nets_logo
                9 -> R.drawable.ic_boston_celtics_logo
                10 -> R.drawable.ic_toronto_raptors_logo
                11 -> R.drawable.ic_chicago_bulls_logo
                12 -> R.drawable.ic_cleveland_cavaliers_logo
                13 -> R.drawable.ic_indiana_pacers_logo
                14 -> R.drawable.ic_pistons_logo17
                15 -> R.drawable.ic_milwaukee_bucks_logo
                16 -> R.drawable.ic_minnesota_timberwolves_logo
                17 -> R.drawable.ic_utah_jazz_logo
                18 -> R.drawable.ic_oklahoma_city_thunder_logo
                19 -> R.drawable.ic_portland_trail_blazers_logo
                20 -> R.drawable.ic_denver_nuggets_logo
                21 -> R.drawable.ic_memphis_grizzlies_logo
                22 -> R.drawable.ic_houston_rockets
                23 -> R.drawable.ic_new_orleans_pelicans_logo
                24 -> R.drawable.ic_san_antonio_spurs
                25 -> R.drawable.ic_dallas_mavericks_logo
                26 -> R.drawable.ic_golden_state_warriors_logo
                27 -> R.drawable.ic_los_angeles_lakers_logo
                28 -> R.drawable.ic_los_angeles_clippers
                29 -> R.drawable.ic_phoenix_suns_logo
                30 -> R.drawable.ic_sacramento_kings_logo
                else -> 0
            }
        }

        fun getFullTeamTitle(id: Int): String {
            return when (id) {
                1 -> "Washington Wizards"
                2 -> "Charlotte Hornets"
                3 -> "Atlanta Hawks"
                4 -> "Miami Heat"
                5 -> "Orland Magic"
                6 -> "New York Knicks"
                7 -> "Philadelphia 76ers"
                8 -> "Brooklyn Nets"
                9 -> "Boston Celtics"
                10 -> "Toronto Raptors"
                11 -> "Chicago Bulls"
                12 -> "Cleveland Cavaliers"
                13 -> "Indiana Pacers"
                14 -> "Detroit Pistons"
                15 -> "Milwaukee Bucks"
                16 -> "Minnesota Timberwolves"
                17 -> "Utah Jazz"
                18 -> "Oklahoma City Thunder"
                19 -> "Portland Trail Blazers"
                20 -> "Denver Nuggets"
                21 -> "Memphis Grizzlies"
                22 -> "Houston Rockets"
                23 -> "New Orleans Pelicans"
                24 -> "San Antonio Spurs"
                25 -> "Dallas Mavericks"
                26 -> "Golden State Warriors"
                27 -> "Los Angeles Lakers"
                28 -> "Los Angeles Clippers"
                29 -> "Phoenix Suns"
                30 -> "Sacramento Kings "

                else -> "Null"
            }
        }

        fun getDate(date: String?): String {
            if (date != null) {
                val result = date.split("-")
                return result[1] + "." + result[2].substring(0, 2) 
            }else return "Date"
        }
    }
}