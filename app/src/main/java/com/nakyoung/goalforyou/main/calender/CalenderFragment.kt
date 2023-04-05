package com.nakyoung.goalforyou.main.calender

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.FragmentCalenderBinding
import kotlinx.coroutines.launch

class CalenderFragment : Fragment() {

    companion object {
        fun newInstance() = CalenderFragment()
    }

    private val viewModel: CalenderViewModel by navGraphViewModels(R.id.nav_graph_main)
    private lateinit var binding: FragmentCalenderBinding
    private lateinit var adapter: CalendarViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("CalenderFragment","onCreateView")

        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        binding.calenderView.calender.layoutManager = GridLayoutManager(requireContext(), 7)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("CalenderFragment","onViewCreated")

        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel = CalenderViewModelFactory().create(CalenderViewModel::class.java)
            binding.calenderView.year.text = viewModel.year.toString()
            binding.calenderView.month.text = viewModel.month.name

            adapter = CalendarViewAdapter(viewModel.days)

            var itemLongClickListener = object : CalendarViewAdapter.ItemLongClickListener {
                override fun onItemLongClick(item: View, indexInDays: Int) {
                    Log.i("CalendarViewAdapter","Item onClick!")
                    CalenderDetailDialog(indexInDays).show(childFragmentManager, "CalenderDetailDialog")
                }
            }
            adapter.setOnItemLongClickListener(itemLongClickListener)

            binding.calenderView.calender.adapter = adapter

        }
    }
}