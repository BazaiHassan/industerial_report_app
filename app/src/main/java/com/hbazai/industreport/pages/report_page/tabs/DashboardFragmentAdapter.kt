package com.hbazai.industreport.pages.report_page.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DashboardFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabDailyFragment()
            1 -> TabChemicalFragment()
            2 -> TabRiskFragment()
            else -> TabPermitFragment()
        }
    }
}