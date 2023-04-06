package com.nakyoung.goalforyou.main.goal

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.RecyclerviewGoalBinding
import com.nakyoung.goalforyou.main.database.domain.Goal
import com.nakyoung.goalforyou.util.GraphicUtil
import com.nakyoung.goalforyou.view.GoalView


class GoalRecyclerView
    @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = com.google.android.material.R.attr.recyclerViewStyle,
        layoutManager: LayoutManager? = LinearLayoutManager(context),
        ): RecyclerView(context, attrs, defStyle) {

    val binding
        get() = _binding!!
    private var _binding: RecyclerviewGoalBinding? = null

    var data: List<Goal>? = null
        set(value) {
            adapter = GoalViewAdapter(value!!)
            field = value}

    init {
        setLayoutManager(layoutManager)
        _binding = RecyclerviewGoalBinding.inflate(LayoutInflater.from(context))
    }

    fun paddingRight() {
        addItemDecoration(GoalItemDecoration(context))
    }
}

class GoalViewAdapter(private val data: List<Goal>) : RecyclerView.Adapter<GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(GoalView(parent.context).apply {

        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

class GoalViewHolder(val goalView: GoalView) : RecyclerView.ViewHolder(goalView) {

    fun bind(goal: Goal) {
        Log.i("GoalViewHolder","bind")
        goalView.binding.goalStatus.text = GoalUtil.getStatus(goal).name
        goalView.binding.goalTitle.text = goal.goalContent

        val leftDDay = GoalUtil.getDDay(goal.goalFinish)
        goalView.binding.goalDDay.text = GoalUtil.DDaytoString(leftDDay)

        goalView.binding.goalProgressBar.setProgressStatus(goal)
    }
}

class GoalItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    var padding = context.resources.getDimensionPixelSize(R.dimen.margin_xsmall)
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutManager = parent.layoutManager

        if ( layoutManager is GridLayoutManager) {
            parent.getChildAdapterPosition(view)
            layoutManager.spanCount
        }
    }
}