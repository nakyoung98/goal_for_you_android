package com.nakyoung.goalforyou.view.progressbar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.TypedArrayUtils
import androidx.core.view.marginEnd
import androidx.lifecycle.*
import com.nakyoung.goalforyou.databinding.GoalViewBinding
import com.nakyoung.goalforyou.databinding.ProgressbarGoalBinding
import com.nakyoung.goalforyou.main.database.domain.Goal
import com.nakyoung.goalforyou.main.goal.GoalUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GoalProgressBar
    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes){

    companion object {

        private const val MIN_PROGRESS_STATUS_BAR_FOR_PROGRESS_STATUS = 25
    }

    private val binding: ProgressbarGoalBinding

    init {
        binding = ProgressbarGoalBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setProgressStatus(goal: Goal) {
        val progressStatus = GoalUtil.getProgressStatus(goal)
        setProgressStatus(progressStatus)
    }

    private fun setProgressStatus(progress: Int) {

        var width: Int
        val progressStatus = progress.let { progressStatus ->
            binding.progressStatus.text ="${progressStatus} %"
            progressStatus
        }

        binding.root.viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    width = binding.root.width

                    ConstraintSet().apply {
                        clone(binding.root)
                        clear(binding.ongoingProgressBarStatus.id,ConstraintSet.END)
                        val progressStatusPixel = width * progressStatus / 100
                        Log.i("GoalProgressBar","progressStatus= ${progressStatus}, root width p/x = ${width}, progressStatusPixel =${progressStatusPixel}")
                        connect(binding.ongoingProgressBarStatus.id,ConstraintSet.END,
                            binding.ongoingProgressBar.id,ConstraintSet.END)
                        setMargin(binding.ongoingProgressBarStatus.id,ConstraintSet.END,width - progressStatusPixel)
                        Log.i("GoalProgressBar","binding.ongoingProgressBarStatus.marginEnd =${binding.ongoingProgressBarStatus.marginEnd}")
                        applyTo(binding.root)

                        if (progressStatus < MIN_PROGRESS_STATUS_BAR_FOR_PROGRESS_STATUS ) {
                            val tempMargin = binding.progressStatus.marginEnd
                            clear(binding.progressStatus.id,ConstraintSet.END)
                            connect(binding.progressStatus.id, ConstraintSet.START,
                                binding.ongoingProgressBarStatus.id,ConstraintSet.END)
                            setMargin(binding.progressStatus.id, ConstraintSet.START, tempMargin)
                            binding.progressStatus.setTextColor((binding.ongoingProgressBarStatus.background as ColorDrawable).color)
                        }
                        applyTo(binding.root)

                    }
                }
            }
        )
    }

}