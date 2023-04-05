package com.nakyoung.goalforyou.main.calender

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.DialogCalenderDetailBinding
import kotlinx.coroutines.launch

class CalenderDetailDialog( val indexIndays: Int
        ): DialogFragment() {

    private lateinit var binding: DialogCalenderDetailBinding

    private val viewModel: CalenderViewModel by navGraphViewModels(R.id.nav_graph_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = DialogCalenderDetailBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            binding.day.text = viewModel.days[indexIndays].day.toString()
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setNegativeButton("확인") { _, _ -> dialog?.cancel() }
            .create()
    }
}