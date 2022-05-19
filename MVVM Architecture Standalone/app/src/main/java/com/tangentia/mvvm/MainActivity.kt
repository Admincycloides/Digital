package com.tangentia.mvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.tangentia.mvvm.databinding.ActivityMainBinding

private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    //to enable drawer button
    private lateinit var drawerLayout: DrawerLayout
    var lastActiveItemId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(" test ", " activity event create ")
        //DATABINDING
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navView = binding.navView

        //FragmentContainerView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //fragment
//        navController = findNavController(R.id.nav_host_fragment)

        navController = navHostFragment.navController

        //enabling hamburger icon
        drawerLayout = binding.drawerLayout

//        lp = binding.bottomNavigationHome.layoutParams as ConstraintLayout.LayoutParams


//        setSupportActionBar(binding.searchBar.toolbar)

        /**
         * Navigation drawer setup
         */
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
//                R.id.nav_delivery_menu,
//                R.id.nav_scheduled_delivery,
//                R.id.nav_login_signup,
//                R.id.nav_referral,
//                R.id.nav_drive_grassdoor,
//                R.id.nav_about_us,
//                R.id.nav_faq,
//                R.id.nav_blog,
//                R.id.nav_settings,
//                R.id.nav_contact,
//                R.id.checkoutFragment,
//                R.id.profileFragment,
//                R.id.searchProductFragment,
//                R.id.rewardsFragment,
            ), drawerLayout
        )
        //display hamburgericon + upicon when in detailfragment + update title based on destination label
        setupActionBarWithNavController(navController, appBarConfiguration)

        //navigation menu item click handle
        navView.setupWithNavController(navController)

        //Bottom Navigation
        setBottomNavigation()

//        binding.searchBar.appBarHeader.appBarAddressField.setOnClickListener {
////            val navHostFragment: Fragment? = supportFragmentManager.primaryNavigationFragment
////            val fragment =
////                navHostFragment!!.childFragmentManager.fragments[0]
////
////            if (fragment is HomeFragment) {
////                fragment.showSetLocationSheet()
////            }
//            showLocationSheet()
//        }

//        binding.filterOption.showFilter.setOnClickListener {
//            binding.filterOption.showFilter.extend()
//            showFilters()
//        }

//        binding.filterOption.filterLayout.setOnClickListener {
////            binding.filterOption.showFilter.visibility = View.VISIBLE
//            binding.filterOption.showFilter.extend()
//        }

//        binding.searchBar.appBarHeader.notificationImage.setOnClickListener {
//            //navigate here
//            val navOptions =
//                NavOptions.Builder().setPopUpTo(R.id.notificationsFragment, true).build()
//            navController.navigate(
//                R.id.notificationsFragment,
//                null,
////                            navOptions
//            )
//        }

//        vm.apiGetCartItems()

        showHideBottomNavigation()
    }

    /**
     * show bottom navigation only for
     * home, search, art and profile
     */
    private fun showHideBottomNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_home
            ) {
                binding.bottomNavigationHome.visibility = View.VISIBLE
            }
        }
    }

    private fun setBottomNavigation() {
//        NavigationUI.setupWithNavController(binding.bottomNavigationHome,navController)
        //Set bottom nav
        binding.bottomNavigationHome.setupWithNavController(navController)
        lastActiveItemId = binding.bottomNavigationHome.selectedItemId

        binding.bottomNavigationHome.setOnItemSelectedListener {

            if (lastActiveItemId != it.itemId) {
//                lastActiveItemId = it.itemId
//                animateBottomView(it.itemId)
            }

            when (it.itemId) {
                R.id.nav_home -> {

//                    navigate here
                    val navOptions = NavOptions.Builder().setPopUpTo(R.id.nav_home, true).build()
                    navController.navigate(
                        R.id.nav_home,
                        null,
//                        navOptions
                    )
//                    moveToHome()

                    return@setOnItemSelectedListener true
                }
            }
            true
        }
    }

    /**
     * Handle NavigationDrawer hamburger + back click
     * + open drawer when drawer icon clicked and back btn presse
     *
     */
    override fun onSupportNavigateUp(): Boolean {
//        return  NavigationUI.navigateUp(navController, drawerLayout) // for drawer hamburger tap + normal back tap
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * bottom navigation
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

//    override fun onResume() {
//        super.onResume()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.clear()
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        println("$TAG, onDestroy() called")
//    }

}