package com.nakyoung.goalforyou.calender

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nakyoung.goalforyou.database.domain.Goal
import com.nakyoung.goalforyou.database.domain.goalsFromUser
import java.time.LocalDate
import java.time.Month

class CalenderViewModel
    private constructor()
    : ViewModel() {

    companion object {
        val viewModel: CalenderViewModel? = null
        fun getInstance() = if (viewModel == null) CalenderViewModel() else viewModel
    }

    private val goals = goalsFromUser

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

    val days: List<Day> by lazy {

        List<Day>(dayOfMonth) {
            //해당 날짜의 goal 담고
            val tempGoals = ArrayList<Goal>().apply {
                for (goal in goals) {
                    if (goal.goalInDate(LocalDate.of(today.year,today.month,it+1))) {
                        add(goal)
                    }
                }
            }

            //그 goal들을 포함해서 Day 객체 만들기.
            Day(it+1,tempGoals.toList())
        }
    }


    init {
        days.listIterator().forEach {
            Log.i("CalenderViewModel",it.toString())
        }
    }

}

class CalenderViewModelFactory(): ViewModelProvider.Factory{

    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(CalenderViewModel::class.java)){
            synchronized(this) {
                @Suppress("UNCHECKED_CAST")
                return CalenderViewModel.getInstance() as T
            }
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}