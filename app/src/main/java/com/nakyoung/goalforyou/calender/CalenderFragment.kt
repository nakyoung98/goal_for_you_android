package com.nakyoung.goalforyou.calender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.FragmentCalenderBinding

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CalenderViewModel::class.java]
        // TODO: Use the ViewModel
    }

}