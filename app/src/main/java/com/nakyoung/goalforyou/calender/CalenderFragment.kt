package com.nakyoung.goalforyou.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nakyoung.goalforyou.databinding.FragmentCalenderBinding

class CalenderFragment: Fragment(){

    private var _binding: FragmentCalenderBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalenderBinding.inflate(inflater,container,false)
        return binding.root
    }

}