package com.nakyoung.goalforyou.main.database.domain

import com.nakyoung.goalforyou.main.calender.CalenderUtil
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
        return (goalStart.isBefore(today) || goalStart.isEqual(today)) &&
                (goalFinish.isAfter(today) || goalFinish.isEqual(today))
    }

    fun goalInMonth(month: Int): Boolean {
        val dateBeforeMonth: LocalDate = LocalDate.of(CalenderUtil.today.year,month,1).minusDays(1)
        val dateAfterMonth: LocalDate = LocalDate.of(CalenderUtil.today.year,month,1).plusMonths(1)

        return goalFinish.isAfter(dateBeforeMonth) && goalStart.isBefore(dateAfterMonth)
    }
}