package com.nakyoung.goalforyou.calender

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.CalenderDateViewBinding
import com.nakyoung.goalforyou.view.CalenderDateCardview
import java.time.DayOfWeek
import java.time.LocalDate

class CalenderViewHolder(cardView:CalenderDateCardview): RecyclerView.ViewHolder(cardView){
    val binding: CalenderDateViewBinding

    init {
        binding = CalenderDateViewBinding.bind(cardView)
    }
}

class CalendarViewAdapter(daysInMonth:List<Day>):
    RecyclerView.Adapter<CalenderViewHolder>() {

    val length = 7 * 5

    init {
        val today = LocalDate.now()
        val firstDayOfWeek = LocalDate.of(today.year,today.month,0).dayOfWeek

        when (firstDayOfWeek) {
            DayOfWeek.MONDAY -> //
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calender_date_view, parent, false)
                    as CalenderDateCardview

        return CalenderViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return
    }
}

class CalendarRecyclerView(context: Context) :
    RecyclerView(context) {

}