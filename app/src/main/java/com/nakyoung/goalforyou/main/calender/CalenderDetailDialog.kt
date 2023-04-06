package com.nakyoung.goalforyou.main.calender

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.DialogCalenderDetailBinding
import kotlinx.coroutines.launch

class CalenderDetailDialog( val indexIndays: Int
        ): DialogFragment() {

    private lateinit var binding: DialogCalenderDetailBinding

    private val calenderViewModel: CalenderViewModel by navGraphViewModels(R.id.nav_graph_main)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogCalenderDetailBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            binding.day.text = calenderViewModel.days[indexIndays].day.toString()
            binding.goalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.goalRecyclerView.data = calenderViewModel.days[indexIndays].goals
        }
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setNegativeButton("확인") { _, _ -> dialog?.cancel() }
            .create()
    }


}