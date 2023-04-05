package com.nakyoung.goalforyou.main.goal.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nakyoung.goalforyou.databinding.FragmentCompleteBinding


class CompleteFragment: Fragment() {

    private var _binding: FragmentCompleteBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompleteBinding.inflate(inflater,container,false)
        return binding.root
    }

}