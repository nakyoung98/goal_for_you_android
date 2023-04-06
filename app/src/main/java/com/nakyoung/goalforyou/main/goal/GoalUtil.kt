package com.nakyoung.goalforyou.main.goal

import com.nakyoung.goalforyou.main.calender.CalenderUtil
import com.nakyoung.goalforyou.main.database.domain.Goal
import java.time.Duration
import java.time.LocalDate

enum class GoalStatus {
    SOON,
    ONGOING,
    COMPLETE,
}
class GoalUtil {
    companion object {

        fun getDDay(goalFinish: LocalDate): Long {
            return Duration.between(CalenderUtil.today.atStartOfDay(), goalFinish.atStartOfDay()).toDays()
        }

        fun DDaytoString(dDay:Long): String {
            return if (dDay >= 0) "D - ${dDay}" else "D + ${-dDay}"
        }

        fun getStatus(goal: Goal): GoalStatus {
            //오늘이 시작날 전이면 SOON
            if(CalenderUtil.today.isBefore(goal.goalStart)) return GoalStatus.SOON
            //오늘이 마지막날 이후면 COMPLETE
            else if(CalenderUtil.today.isAfter(goal.goalFinish)) return GoalStatus.COMPLETE
            //그 사이면 ONGOING
            else return GoalStatus.ONGOING
        }

        fun getProgressStatus(goal: Goal): Int {
            when (getStatus(goal)) {
                GoalStatus.SOON -> return 0
                GoalStatus.COMPLETE -> return 100
                GoalStatus.ONGOING -> {
                    val dday = getDDay(goal.goalFinish)
                    val duration = Duration.between(goal.goalStart.atStartOfDay(), goal.goalFinish.atStartOfDay()).toDays()
                    return ((dday.toFloat()/duration.toFloat())*100).toInt()
                }
            }
        }
    }
}