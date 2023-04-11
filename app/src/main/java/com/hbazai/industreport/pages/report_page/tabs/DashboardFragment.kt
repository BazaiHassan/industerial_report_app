package com.hbazai.industreport.pages.report_page.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hbazai.industreport.R

class DashboardFragment : Fragment() {

    private val tabTitles = arrayListOf("روزانه", "مواد شیمیایی", "ریسک", "مجوزکار")
    private lateinit var tabViewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabViewPager = view.findViewById(R.id.tab_view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
        setupTabLayoutWithViewPager()
    }

    private fun setupTabLayoutWithViewPager() {
        tabViewPager.adapter = DashboardFragmentAdapter(this)
        TabLayoutMediator(tabLayout, tabViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0..3) {
            val textView =
                LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as TextView
            tabLayout.getTabAt(i)?.customView = textView
        }
    }

}