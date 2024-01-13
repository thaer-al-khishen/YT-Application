package com.example.ytapplication

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.ytapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = when (position) {
//                0 -> "Home"
//                1 -> "Destinations"
//                else -> "Tips"
//            }

            val tabView = LayoutInflater.from(this).inflate(R.layout.tab_custom_layout, null)
            tab.customView = tabView

            val tabIcon = tabView.findViewById<ImageView>(R.id.tab_image)
            val tabText = tabView.findViewById<TextView>(R.id.tab_title)

            // Set icon and text based on position
            tabIcon.setImageResource(getTabIconId(position))
            tabText.text = getTabTitle(position)

        }.attach()

    }

    private fun getTabIconId(position: Int): Int {
        return when (position) {
            0 -> R.drawable.ic_dynamic_home
            1 -> R.drawable.ic_destination
            else -> R.drawable.ic_tips
        }
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Home"
            1 -> "Destinations"
            else -> "Tips"
        }
    }

}
