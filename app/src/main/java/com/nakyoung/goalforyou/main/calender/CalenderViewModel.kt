package com.nakyoung.goalforyou.main.calender

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nakyoung.goalforyou.main.database.domain.Goal
import com.nakyoung.goalforyou.main.database.domain.goalsFromUser
import java.time.LocalDate
import java.time.Month

class CalenderViewModel
    : ViewModel() {

    private val today = LocalDate.now().apply {
        Log.i("CalenderViewModel",this.toString())
    }

    val year: Int by lazy {
        today.year
    }

    val month: Month by lazy {
        today.month
    }
    private val dayOfMonth: Int by lazy {
        today.month.length(
            today.isLeapYear
        )
    }

    val goalsInMonth: ArrayList<Goal>

    val days: List<Day>


    init {

        goalsInMonth = ArrayList<Goal>().apply {
            for (goal in goalsFromUser) {
                if (goal.goalInMonth(month.value)) {
                    add(goal)
                }
            }
        }
        goalsInMonth.sortBy { it.goalStart }

        days = List<Day>(dayOfMonth) { day ->
            //해당 날짜의 goal 담고
            val tempGoals = ArrayList<Goal>().apply {
                for (goal in goalsFromUser) {
                    if (goal.goalInDate(LocalDate.of(year,month.value,day+1))) {
                        add(goal)
                    }
                }
            }
            tempGoals.sortBy { it.goalStart }

            //그 goal들을 포함해서 Day 객체 만들기.
            Day(day+1,tempGoals.toList())
        }

        days.listIterator().forEach {
            Log.i("CalenderViewModel",it.toString())
        }
    }
}

class CalenderViewModelFactory(): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(CalenderViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CalenderViewModel() as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}