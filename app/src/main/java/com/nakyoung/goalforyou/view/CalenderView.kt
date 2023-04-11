package com.nakyoung.goalforyou.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.nakyoung.goalforyou.databinding.CalenderViewBinding
import com.nakyoung.goalforyou.databinding.ProgressbarGoalBinding

class CalenderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    val binding: CalenderViewBinding
    private val TAG = "CalenderView"

    init {
        Log.i(TAG, "init")
        binding = CalenderViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
}
