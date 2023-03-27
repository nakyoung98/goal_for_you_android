package com.nakyoung.goalforyou.calender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.nakyoung.goalforyou.databinding.FragmentCalenderBinding
import kotlinx.coroutines.launch

class CalenderFragment : Fragment() {

    companion object {
        fun newInstance() = CalenderFragment()
    }

    private lateinit var viewModel: CalenderViewModel
    private lateinit var binding: FragmentCalenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalenderBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel = CalenderViewModelFactory().create(CalenderViewModel::class.java)
            binding.calenderView.calender.adapter = CalendarViewAdapter(viewModel.days)
            binding.calenderView.calender.layoutManager = GridLayoutManager(requireContext(), 7)
        }
    }
}