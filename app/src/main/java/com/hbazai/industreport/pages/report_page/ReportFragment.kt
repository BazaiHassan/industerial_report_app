package com.hbazai.industreport.pages.report_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.create_forms.ChemicalReportFragment
import com.hbazai.industreport.pages.report_page.create_forms.DailyReportFragment
import com.hbazai.industreport.pages.report_page.create_forms.DangerReportFragment

class ReportFragment : Fragment() {

    private lateinit var fabDailyReport:FloatingActionButton
    private lateinit var fabChemicalReport:FloatingActionButton
    private lateinit var fabRiskReport:FloatingActionButton
    private lateinit var fabPermitReport:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // initialize --------------------------------------
        fabDailyReport = view.findViewById(R.id.fab_daily_report)
        fabChemicalReport = view.findViewById(R.id.fab_chemical_report)
        fabRiskReport = view.findViewById(R.id.fab_risk_report)
        fabPermitReport = view.findViewById(R.id.fab_permit_report)



        fabDailyReport.setOnClickListener {
            val dailyReportFragment = DailyReportFragment()
            replaceFragment(dailyReportFragment)
        }

        fabChemicalReport.setOnClickListener {
            val chemicalReportFragment = ChemicalReportFragment()
            replaceFragment(chemicalReportFragment)
        }

        fabRiskReport.setOnClickListener {
            val riskReportFragment = DangerReportFragment()
            replaceFragment(riskReportFragment)
        }

        fabPermitReport.setOnClickListener {

        }



    }

    override fun onStart() {
        super.onStart()
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        view.visibility = View.VISIBLE
    }





    private fun hideProgressBar(){

    }

    private fun showProgressBar(){

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // set custom animation for the fragment transition
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.slide_out,
            R.anim.slide_in,
            R.anim.slide_out,
        )
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }

}