package com.nakyoung.goalforyou.main.database.domain

import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

val goalsFromUser = ArrayList<Goal>()

fun fillGoals() {

    goalsFromUser.add(
        Goal(
            0,
            "일찍일어나기",
            LocalDate.of(2023, 1, 5),
            LocalDate.of(2023, 1, 21),
            "COMPLETE",
            1
        )
    )

    goalsFromUser.add(
        Goal(
            1,
            "운동하기",
            LocalDate.of(2023, 3, 4),
            LocalDate.of(2023, 4, 21),
            "ONGOING",
            5
        )
    )

    goalsFromUser.add(
        Goal(
            2,
            "출근하기",
            LocalDate.of(2023, 3, 4),
            LocalDate.of(2023, 3, 24),
            "ONGOING",
            3
        )
    )

    goalsFromUser.add(
        Goal(
            3,
            "집까지걷기",
            LocalDate.of(2023, 4, 2),
            LocalDate.of(2023, 4, 4),
            "ONGOING",
            5
        )
    )
}