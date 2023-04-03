package com.nakyoung.goalforyou.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.nakyoung.goalforyou.R
import com.nakyoung.goalforyou.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navHostFragment:NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavHost()
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
    }

    private fun setNavHost() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigationRailView.setupWithNavController(navController)
        navController.setGraph(R.navigation.nav_graph_main)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
    }
}