package com.nakyoung.goalforyou

import android.app.Application
import com.nakyoung.goalforyou.main.database.domain.fillGoals

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        fillGoals()
    }
}