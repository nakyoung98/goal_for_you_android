package com.nakyoung.goalforyou.main.goal

import android.content.Context
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.RecyclerviewGoalBinding
import com.nakyoung.goalforyou.main.database.domain.Goal
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

    val itemDecoration: ItemDecoration
    init {
        setLayoutManager(layoutManager)
        _binding = RecyclerviewGoalBinding.inflate(LayoutInflater.from(context))
        itemDecoration = GoalItemDecoration(context)
        addItemDecoration(itemDecoration)
    }

    override fun draw(c: Canvas?) {
        super.draw(c)
        invalidateItemDecorations()
    }
}

class GoalViewAdapter(private val data: List<Goal>) : RecyclerView.Adapter<GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(GoalView(parent.context))
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
//        Log.i("GoalViewHolder","bind")
        goalView.binding.goalStatus.text = GoalUtil.getStatus(goal).name
        goalView.binding.goalTitle.text = goal.goalContent

        val leftDDay = GoalUtil.getDDay(goal.goalFinish)
        goalView.binding.goalDDay.text = GoalUtil.DDaytoString(leftDDay)

        goalView.binding.goalProgressBar.setProgressStatus(goal)
    }
}

class GoalItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val nowPosition = parent.getChildAdapterPosition(view)

//        Log.i("GoalItemDecoration", "decoration, now Position: ${nowPosition}")

        val layoutManager = parent.layoutManager

        if (layoutManager is GridLayoutManager) {
//            Log.i("GoalItemDecoration", "GridLayoutManager, spancount: ${layoutManager.spanCount}")

            val spanCount = layoutManager.spanCount
            val margin: Int =
                (parent.width - view.resources.getDimension(R.dimen.goal_view_width).toInt() * spanCount) / (spanCount + 1) / 2
//            Log.i("margin", "recyclerview width ${parent.width}, view width ${view.width}, margin: ${margin}")

            if (nowPosition % spanCount == 0) {
//                Log.i("margin", "right")
                outRect.right = margin
                outRect.left = margin*2
            } else if (nowPosition % spanCount == spanCount - 1) {
//                Log.i("margin", "left")
                outRect.left = margin
                outRect.right = margin*2
            } else {
//                Log.i("margin", "right/left")
                outRect.right = margin
                outRect.left = margin
            }

            if (nowPosition < spanCount) {
                outRect.top = margin*2
            }
            outRect.bottom = margin*2

        } else if (layoutManager is LinearLayoutManager) {
//            Log.i("GoalItemDecoration", "LinearLayoutManager")

            if (layoutManager.orientation == LinearLayoutManager.HORIZONTAL) outRect.right = 10
            else outRect.bottom = 10
        }

//        Log.i("margin", "Rect margin right: ${outRect.right}, left: ${outRect.left}, top: ${outRect.top}, bottom: ${outRect.bottom}")
    }
}