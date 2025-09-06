package com.example.lzycrazy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lzycrazy.withlogin.marketplace.MarketplaceActivity
import com.example.lzycrazy.withlogin.marketpost.kuldeep.electro_store.PCAccessoriesFragment
import com.example.lzycrazy.withlogin.reels.ReelsFragment
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
                R.id.nav_ads -> replaceWithFragment(ReelsFragment())
                R.id.nav_post -> replaceWithFragment(MarketFragment())
<<<<<<< HEAD
                R.id.nav_market -> replaceWithFragment(AlertsFragment())
                R.id.nav_business -> replaceWithFragment(ProfileFragment())
=======
                R.id.nav_market -> {
                    val intent = Intent(this, MarketplaceActivity::class.java)
                    startActivity(intent)
                    false
                }
                R.id.nav_business -> replaceWithFragment(PCAccessoriesFragment())
                else -> false
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b
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

