package com.nakyoung.goalforyou.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.ActivitySplashBinding
import com.nakyoung.goalforyou.main.MainActivity
import com.nakyoung.goalforyou.view.GoalIndex

class SplashActivity : AppCompatActivity() {


    private val binding
        get() = _binding!!
    private var _binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)

        binding.view.addGoal(GoalIndex.SECOND,"이게 진짜지dfqdfadfadf")
        setContentView(binding.root)
        login()
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

    fun login() {
//TODO 자동 login 기능 구현
        //임시 로그인 코드
//        startActivity(Intent(this,MainActivity::class.java).apply {
//            putExtra("user","test1@goalforyou.com")
//        })
    }
}