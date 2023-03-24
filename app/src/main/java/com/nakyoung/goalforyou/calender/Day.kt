package com.nakyoung.goalforyou.calender

import com.nakyoung.goalforyou.database.domain.Goal
import java.time.LocalDate

enum class Week {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

data class Day (
    val day: Int,
    val goals: List<Goal>,
)