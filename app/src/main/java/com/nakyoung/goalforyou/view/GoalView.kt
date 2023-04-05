package com.nakyoung.goalforyou.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.GoalViewBinding

class GoalView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes)
{
    val binding
        get() = _binding!!
    private var _binding: GoalViewBinding? = null


    init {
        _binding = GoalViewBinding.inflate(LayoutInflater.from(context),this,true)
    }
}

