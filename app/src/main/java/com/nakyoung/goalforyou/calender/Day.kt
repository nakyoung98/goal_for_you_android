package com.nakyoung.goalforyou.calender

import com.nakyoung.goalforyou.database.domain.Goal
import java.time.LocalDate

data class Day (
    val day: Int,
    val goals: List<Goal>,
)