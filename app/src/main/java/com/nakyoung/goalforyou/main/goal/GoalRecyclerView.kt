package com.nakyoung.goalforyou.main.goal

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
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
import androidx.constraintlayout.widget.ConstraintLayout
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
 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0, layoutManager: RecyclerView.LayoutManager? = GridLayoutManager(context,1),)
    : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes)
 {

    val binding
        get() = _binding!!
    private var _binding: RecyclerviewGoalBinding? = null

    var data: List<Goal>? = null
        set(value) {
            binding.goalRecyclerView.adapter = GoalViewAdapter(value!!)
            field = value}

    val itemDecoration: RecyclerView.ItemDecoration
    init {
        _binding = RecyclerviewGoalBinding.inflate(LayoutInflater.from(context),this,true)
        binding.goalRecyclerView.layoutManager = layoutManager
        itemDecoration = GoalItemDecoration(context)
        binding.goalRecyclerView.addItemDecoration(itemDecoration)
    }

     override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
         super.onSizeChanged(w, h, oldw, oldh)
         val spanCount: Int = w / resources.getDimension(R.dimen.goal_view_width) .toInt()
//         Log.i("GoalRecyclerView","onSizeChanged w:${w}, spancount:${spanCount}")

         when (binding.goalRecyclerView.layoutManager) {
             is GridLayoutManager -> if(spanCount != (binding.goalRecyclerView.layoutManager as GridLayoutManager).spanCount )(binding.goalRecyclerView.layoutManager as GridLayoutManager).spanCount = spanCount
         }
         binding.goalRecyclerView.invalidateItemDecorations()
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
        Log.i("GoalViewHolder","bind")
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
            val margin: Int = view.resources.getDimension(R.dimen.margin_small).toInt()
            if (layoutManager.orientation == LinearLayoutManager.HORIZONTAL) outRect.right = margin
            else outRect.bottom = margin
        }

//        Log.i("margin", "Rect margin right: ${outRect.right}, left: ${outRect.left}, top: ${outRect.top}, bottom: ${outRect.bottom}")
    }
}