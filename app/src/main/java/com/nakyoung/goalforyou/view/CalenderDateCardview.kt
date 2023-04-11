package com.nakyoung.goalforyou.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.main.database.domain.Goal
import com.nakyoung.goalforyou.databinding.CalenderDateViewBinding

/**
 * [CalenderDateCardview]
 *
 * @constructor: CalenderDateCardview(Context, AttributeSet) -> android studio가 view와 interaction 하기위해 필요한 최소한의 매개변수. 해당 생성자를 사용하면 LayoutEditor에서 뷰의 instance를 만들고 수정할 수 있음음 * @author 이나경
 *
 * **/

enum class GoalIndex {
    FIRST,
    SECOND,
    THIRD;
}
fun intToGoal(int: Int): GoalIndex {
    return when (int) {
        GoalIndex.FIRST.ordinal -> GoalIndex.FIRST
        GoalIndex.SECOND.ordinal -> GoalIndex.SECOND
        GoalIndex.THIRD.ordinal -> GoalIndex.THIRD
        else -> throw EnumConstantNotPresentException(GoalIndex::class.java, int.toString())
    }
}

class CalenderDateCardview
    @JvmOverloads constructor
    (context: Context,
     attrs: AttributeSet? = null,
     defStyle: Int = R.attr.nakyoungCalenderDateCardviewStyle
    ) : LinearLayout(context, attrs, defStyle) {

    companion object{
        private const val DEF_STYLE_RES = R.style.Nakyoung_MaterialComponents_calendarCardView
        private const val MAX_GOAL = 3
    }

    val binding: CalenderDateViewBinding

    var date: Int = 0
        set(value) {
            binding.date.setText(value.toString())
            field = value
        }

    var datePosition: Int = Gravity.START
        set(value) {
            when (value) {
                Gravity.START -> {binding.date.gravity = Gravity.START }
                Gravity.CENTER_HORIZONTAL -> {binding.date.gravity = Gravity.CENTER_HORIZONTAL }
                Gravity.END -> {binding.date.gravity = Gravity.END}
                else -> Log.e("CalenderDateCardView",
                    """
                        |Only Gravity.START, Gravity.CENTER_HORIZONTAL, Gravity.END can be a value.
                        |Set Position with default value: "Gravity.START"
                    """.trimMargin())
            }
            field = value
        }

    var dateBackgroundColor: Int = Color.TRANSPARENT
        set(value) {
            binding.calenderDateView.setBackgroundColor(value)
            field = value
        }

    var dateTextColor: Int = Color.BLACK
        set(value) {
            binding.date.setTextColor(value)
            field = value
        }


    var goalTextColor: Int = Color.BLACK
        set(value) {
            binding.firstGoal.setTextColor(value)
            binding.secondGoal.setTextColor(value)
            binding.thirdGoal.setTextColor(value)
            field = value
        }

    var firstGoalColor: Int = Color.BLUE
        set(value) {
            binding.firstGoal.setBackgroundColor(value)
            field = value
        }

    var secondGoalColor: Int = Color.RED
        set(value) {
            binding.secondGoal.setBackgroundColor(value)
            field = value
        }

    var thirdGoalColor: Int = Color.GREEN
        set(value) {
            binding.thirdGoal.setBackgroundColor(value)
            field = value
        }

    private val goalViews: List<TextView>

    init {
        binding = CalenderDateViewBinding.inflate(LayoutInflater.from(context),this,true)
        goalViews = listOf(binding.firstGoal, binding.secondGoal, binding.thirdGoal)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CalenderDateCardview,
            defStyle, DEF_STYLE_RES).apply {
                try {
                    date = getInt(R.styleable.CalenderDateCardview_date, date)
                    datePosition = getInt(R.styleable.CalenderDateCardview_datePosition,Gravity.START)
                    dateTextColor = getColor(R.styleable.CalenderDateCardview_dateTextColor, dateTextColor)
                    dateBackgroundColor = getColor(R.styleable.CalenderDateCardview_dateBackgroundColor, dateBackgroundColor)
                    goalTextColor = getColor(R.styleable.CalenderDateCardview_goalTextColor, goalTextColor)
                    firstGoalColor = getColor(R.styleable.CalenderDateCardview_firstGoalColor, firstGoalColor)
                    secondGoalColor = getColor(R.styleable.CalenderDateCardview_secondGoalColor, secondGoalColor)
                    thirdGoalColor = getColor(R.styleable.CalenderDateCardview_thirdGoalColor, thirdGoalColor)
                } finally {
                  recycle()
                }
            }
    }

    //TODO 추후에 goal을 객체로 바꿔야함
    fun addGoal(goalIndex: GoalIndex, goal: Goal) {
        if (!goalViews[goalIndex.ordinal].isVisible) {
            goalViews[goalIndex.ordinal].text = goal.goalContent
            goalViews[goalIndex.ordinal].isVisible = true
        }
        //TODO else의 경우 어떻게 처리?
    }

    fun addGoal(goals: List<Goal>) {
        for((index,goal) in goals.withIndex()) {
            if (index == MAX_GOAL) break

            addGoal(intToGoal(index), goal)
        }
    }

    fun addGoal(goal: Goal, isTextVisible: Boolean, background: Int?) {
        for (goalView in goalViews) {
            if (!goalView.isVisible) {
                if (background != null) goalView.setBackgroundColor(background)
                goalView.text = if (isTextVisible) goal.goalContent else ""
                goalView.isVisible = true
                return
            }
        }
    }

    fun addGoal(goal: Goal, isTextVisible: Boolean) {
        addGoal(goal, isTextVisible, null)
    }

    fun addGoal(goal: Goal) {
        addGoal(goal, true, null)
    }

}

