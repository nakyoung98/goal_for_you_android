package com.nakyoung.goalforyou.ongoing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nakyoung.goalforyou.databinding.FragmentOngoingBinding


class OngoingFragment: Fragment() {

    private var _binding: FragmentOngoingBinding
? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOngoingBinding
.inflate(inflater,container,false)
        return binding.root
    }

}