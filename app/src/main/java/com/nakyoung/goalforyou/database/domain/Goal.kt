package com.nakyoung.goalforyou.database.domain

import java.time.LocalDate
import java.util.Date

data class Goal(
    val goalId: Int,
    val goalContent: String,
    val goalStart: LocalDate,
    val goalFinish: LocalDate,
    val goalStatus: String,
    val calendarId: Int,
){
    //해당 날짜에 해당 goal이 존재하는지 확인
    fun goalInDate(today: LocalDate): Boolean {
        if ((goalStart.isBefore(today) || goalStart.isEqual(today)) &&
            (goalFinish.isAfter(today) || goalFinish.isEqual(today))
        ) {
            return true
        }
        return false
    }

}