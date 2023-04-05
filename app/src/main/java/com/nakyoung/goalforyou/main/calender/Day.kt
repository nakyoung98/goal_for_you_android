package com.nakyoung.goalforyou.main.calender

import com.nakyoung.goalforyou.main.database.domain.Goal
import java.time.LocalDate

data class Day (
    val day: Int,
    val goals: List<Goal>,
)