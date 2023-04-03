package com.nakyoung.goalforyou.calender

import java.time.DayOfWeek
import java.time.LocalDate

class CalenderUtil {

    companion object{

        val today = LocalDate.now()

        /**
         * 해당 월의 총 날짜를 반환
         * 금년을 기준으로 함
         **/
        fun lengthOfMonth(month: Int): Int {
            val tempMonth = LocalDate.of(today.year,month,1).month
            return tempMonth.length(today.isLeapYear)
        }

        /**
         * 이번 달의 총 날짜를 반환
         * 금년을 기준으로 함
         **/
        fun lengthOfThisMonth(): Int {
            val tempMonth = LocalDate.of(today.year, today.month,1).month
            return tempMonth.length(today.isLeapYear)
        }

        /**
         * 해당 월 1일의 요일을 반환
         * 금년을 기준으로 함
         **/
        fun firstDayOfWeek(month: Int): DayOfWeek {
            return LocalDate.of(today.year,month,1).dayOfWeek
        }

        fun firstPositionOfMonth(firstDayOfWeek: DayOfWeek): Int {
            return when (firstDayOfWeek) {
                DayOfWeek.SUNDAY -> 0
                DayOfWeek.MONDAY -> 1
                DayOfWeek.TUESDAY -> 2
                DayOfWeek.WEDNESDAY -> 3
                DayOfWeek.THURSDAY -> 4
                DayOfWeek.FRIDAY -> 5
                DayOfWeek.SATURDAY -> 6
                else -> -1
            }
        }
    }
}