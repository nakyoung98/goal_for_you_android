package com.nakyoung.goalforyou.main.goal

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nakyoung.goalforyou.main.database.domain.Goal
import com.nakyoung.goalforyou.view.GoalView

class GoalRecyclerView(
    context: Context,
    layoutManager: LayoutManager? = LinearLayoutManager(context),
    val data: List<Goal>): RecyclerView(context) {
    init {
        setLayoutManager(layoutManager)
        adapter = GoalViewAdapter()
    }
}

class GoalViewAdapter() : RecyclerView.Adapter<GoalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}

class GoalViewHolder(goalView: GoalView) : RecyclerView.ViewHolder(goalView) {

}