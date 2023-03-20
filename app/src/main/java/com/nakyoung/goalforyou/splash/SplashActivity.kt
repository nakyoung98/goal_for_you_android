package com.nakyoung.goalforyou.splash

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {


    private val binding
        get() = _binding!!
    private var _binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        animatingTitle()
    }

    fun animatingTitle() {
        binding.title.animation = AnimationUtils.loadAnimation(this, R.anim.anim_text_fadein)
        binding.title.isVisible = true
        binding.title.startAnimation(binding.title.animation)
    }
}