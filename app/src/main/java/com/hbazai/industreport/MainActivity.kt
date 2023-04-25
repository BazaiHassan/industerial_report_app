package com.hbazai.industreport


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hbazai.industreport.pages.notify_page.NotifyFragment
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.adapter.DailyReportAdapter
import com.hbazai.industreport.pages.report_page.create_forms.DailyReportFragment
import com.hbazai.industreport.pages.search_page.SearchFragment
import com.hbazai.industreport.pages.user_page.UserFragment
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private lateinit var btNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNav = findViewById(R.id.bottomNavigationView)
        replaceFragment(ReportFragment())
        btNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.reportFragment -> replaceFragment(ReportFragment())
                R.id.searchFragment -> replaceFragment(SearchFragment())
                R.id.notifyFragment -> replaceFragment(NotifyFragment())
                R.id.userFragment -> replaceFragment(UserFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }
}