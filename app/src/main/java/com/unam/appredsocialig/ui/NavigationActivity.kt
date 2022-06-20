package com.unam.appredsocialig.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    lateinit var binding : ActivityNavigationBinding
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    private val navHost : NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setTheme(R.style.Theme_AppRedSocialIG)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        initUI()
    }

    private fun initUI() {
        navController = navHost.navController
        bottomNav = findViewById(R.id.nav_view)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{
            _: NavController?, _: NavDestination?, arguments: Bundle? ->

            var showAppBar = false
            if (arguments != null) {
                showAppBar = arguments.getBoolean("ShowAppBar", false)
            }
            if (showAppBar) {
                binding.appBarLayout.visibility = View.VISIBLE
            } else {
                binding.appBarLayout.visibility = View.GONE
            }
        }
    }

}