package com.nakyoung.goalforyou.main.calender

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.view.CalenderDateCardview
import java.time.DayOfWeek
import java.time.LocalDate


class CalenderRecyclerView @JvmOverloads constructor(
     context: Context,
     attr: AttributeSet? = null,
     defStyleAttr: Int = androidx.recyclerview.R.attr.recyclerViewStyle
) : RecyclerView(context, attr, defStyleAttr) {

    init {
        adapter = CalendarViewAdapter(CalenderViewModelFactory().create(CalenderViewModel::class.java).days)
        addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: State
            ) {
                outRect.set(10,10,10,10)
                Log.i("CalenderRecyclerView","invalidate")
            }
        })
    }

    override fun onDraw(c: Canvas?) {
        super.onDraw(c)
        invalidateItemDecorations()
    }

}

class CalenderViewHolder(val cardView:CalenderDateCardview, val itemLongClickListener: CalendarViewAdapter.ItemLongClickListener? = null): RecyclerView.ViewHolder(cardView){
    fun bind(day: Day?) {
        if (day != null) {
            cardView.date = day.day
            cardView.addGoal(day.goals)
        } else {
            cardView.isVisible = false
        }
    }
}

class CalendarViewAdapter(val days: List<Day>,
                          var itemLongClickListener: ItemLongClickListener? = null) :
    RecyclerView.Adapter<CalenderViewHolder>() {

    interface ItemLongClickListener {
        fun onItemLongClick(item: View, indexInDays: Int)
    }

    fun setOnItemLongClickListener(listener: ItemLongClickListener) {
        itemLongClickListener = listener
    }

    val startCount:Int
    val endCount:Int

    init {
        val lengthOfMonth = CalenderUtil.lengthOfThisMonth()
        val firstDayOfWeek = CalenderUtil.firstDayOfWeek(CalenderUtil.today.monthValue)

        //startCount Initial
        //startCount는 Calendar Recycler View에서 첫 주에 며칠부터 Visible하게 할 것인지를 정함
        startCount = CalenderUtil.firstPositionOfMonth(firstDayOfWeek)

        //lastCount Initial
        //lastCount는 Calendar Recycler View에서 마지막 주에 며칠까지 Visible하게 할 것인지를 정함
        //startCount시점에서 dayOfMonth를 더하고 1을 빼면 끝나는 날의 Index가 됨
        endCount = startCount + lengthOfMonth

    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val view = CalenderDateCardview(parent.context)
//        val view = CalenderDateCardview(parent.context,)
        //TODO attr 받아오도록 ViewAdapter에 생성자 파라미터 추가해야함
        return CalenderViewHolder(view, itemLongClickListener)
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        val day = if (position in startCount..endCount) days[position - startCount] else null
        holder.bind(day)
        holder.cardView.binding.calenderDateView.setOnLongClickListener {
            itemLongClickListener?.onItemLongClick(it, position - startCount)
            true
        }
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return endCount
    }
}