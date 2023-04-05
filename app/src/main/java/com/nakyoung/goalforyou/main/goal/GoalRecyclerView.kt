package com.nakyoung.goalforyou.main.goal

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.RecyclerviewGoalBinding
import com.nakyoung.goalforyou.main.database.domain.Goal
import com.nakyoung.goalforyou.view.GoalView
import java.util.concurrent.atomic.AtomicInteger


class GoalRecyclerView
    @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = com.google.android.material.R.attr.recyclerViewStyle,
        layoutManager: LayoutManager? = LinearLayoutManager(context),
        data: List<Goal>? = null
        ): RecyclerView(context, attrs, defStyle) {

    val binding
        get() = _binding!!
    private var _binding: RecyclerviewGoalBinding? = null

    init {
        setLayoutManager(layoutManager)
        adapter = GoalViewAdapter()
        _binding = RecyclerviewGoalBinding.inflate(LayoutInflater.from(context))
    }

}

class GoalViewAdapter() : RecyclerView.Adapter<GoalViewHolder>() {
    val count: AtomicInteger = AtomicInteger(1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(GoalView(parent.context))
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(count.getAndIncrement())
    }

}

class GoalViewHolder(val goalView: GoalView) : RecyclerView.ViewHolder(goalView) {
    val count: AtomicInteger = AtomicInteger(1)

    fun bind(count: Int) {
        goalView.binding.goalTitle.text = "" + count + " & " + this.count.getAndIncrement() + ""
    }
}