package com.example.lzycrazy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lzycrazy.withlogin.reels.ReelsFragment
import com.example.lzycrazy.withoutlogin.marketplace.MarketplacePropertyAgentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val bottomView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        replaceWithFragment(HomeFragment())
        bottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> replaceWithFragment(HomeFragment())
                R.id.nav_media -> replaceWithFragment(ReelsFragment())

                // changed it to test the property listing for now
//                R.id.nav_shop -> replaceWithFragment(MarketplacePropertyAgentFragment())
                R.id.nav_shop -> replaceWithFragment(MarketFragment())
                R.id.nav_notification -> replaceWithFragment(AlertsFragment())
                R.id.nav_profile -> replaceWithFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}

