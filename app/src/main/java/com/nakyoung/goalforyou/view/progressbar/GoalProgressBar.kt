package com.nakyoung.goalforyou.view.progressbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.nakyoung.goalforyou.databinding.GoalViewBinding
import com.nakyoung.goalforyou.databinding.ProgressbarGoalBinding

class GoalProgressBar
    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes){

    private val binding: ProgressbarGoalBinding

    init {
        binding = ProgressbarGoalBinding.inflate(LayoutInflater.from(context),this,true)
    }
}