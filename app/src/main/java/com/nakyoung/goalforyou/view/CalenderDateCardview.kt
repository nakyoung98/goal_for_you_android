package com.nakyoung.goalforyou.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.database.domain.Goal
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

class CalenderDateCardview(context: Context, attrs: AttributeSet?) : MaterialCardView(context, attrs) {

    private val binding: CalenderDateViewBinding by lazy {
        CalenderDateViewBinding.inflate(LayoutInflater.from(context))
    }

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

    var dateBackgroundColor: Int = Color.WHITE
        set(value) {
            binding.calenderDateCardview.setCardBackgroundColor(value)
            field = value
        }

    var dateColor: Int = Color.BLACK
        set(value) {
            binding.date.setTextColor(value)
            field = value
        }

    var roundedCornerRadius: Float = 0f
        set(value) {
            binding.calenderDateCardview.radius = value
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

    val goals: List<TextView> by lazy { listOf(binding.firstGoal, binding.secondGoal, binding.thirdGoal) }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CalenderDateCardview,
            0,0).apply {
                try {
                    date = getInt(R.styleable.CalenderDateCardview_date, date)
                    datePosition = getInt(R.styleable.CalenderDateCardview_datePosition,Gravity.START)
                    dateColor = getColor(R.styleable.CalenderDateCardview_dateColor, dateColor)
                    dateBackgroundColor = getColor(R.styleable.CalenderDateCardview_dateBackgroundColor, dateBackgroundColor)
                    strokeColor = getColor(R.styleable.CalenderDateCardview_strokeColor, strokeColor)
                    roundedCornerRadius = getDimension(R.styleable.CalenderDateCardview_roundedCornerRadius, roundedCornerRadius)
                    firstGoalColor = getColor(R.styleable.CalenderDateCardview_firstGoalColor, firstGoalColor)
                    secondGoalColor = getColor(R.styleable.CalenderDateCardview_secondGoalColor, secondGoalColor)
                    thirdGoalColor = getColor(R.styleable.CalenderDateCardview_thirdGoalColor, thirdGoalColor)
                } finally {
                  recycle()
                }
            }

        addView(binding.root)
    }

    //TODO 추후에 goal을 객체로 바꿔야함
    fun addGoal(goalIndex: GoalIndex, goal: Goal) {
        if (!goals[goalIndex.ordinal].isVisible) {
            goals[goalIndex.ordinal].text = goal.goalContent
            goals[goalIndex.ordinal].isVisible = true
        }
        //TODO else의 경우 어떻게 처리?
    }

    fun addGoal(goals: List<Goal>) {
        for((index,goal) in goals.withIndex())
            addGoal(intToGoal(index), goal)
    }
}

