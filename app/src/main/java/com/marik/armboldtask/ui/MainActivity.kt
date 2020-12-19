package com.marik.armboldtask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
import com.marik.armboldtask.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav_view.setupWithNavController(navController)
        bottom_nav_view.labelVisibilityMode = LABEL_VISIBILITY_UNLABELED

        //Creating badge
        /*val badge = bottom_nav_view.getOrCreateBadge(R.id.menu_bell)
        badge.backgroundColor = ResourcesCompat.getColor(resources, R.color.color_primary, null)
        badge.isVisible = true
        // An icon only badge will be displayed unless a number is set:
        badge.number = 3*/

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
             when(item.itemId) {
                 R.id.home -> {
                     // Respond to navigation item 1 click
                     val homeFragment = supportFragmentManager.findFragmentById(R.id.homeFragment) as HomeFragment
                     val x = constraint_home[3].x
                     val y = constraint_home[3].y
                     scrollView.post{constraint_home.scrollTo(x.toInt(), y.toInt())}
                     true
                 }
                 // ...
                 else -> false
             }
         }
    }
}


