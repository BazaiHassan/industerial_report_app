package com.hbazai.industreport.pages.report_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.adapter.ReportAdapter
import com.hbazai.industreport.pages.report_page.create_forms.DailyReportFragment
import com.hbazai.industreport.pages.report_page.viewModel.ShowReportsViewModel
import com.nambimobile.widgets.efab.ExpandableFab
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReportFragment : Fragment() {

    private lateinit var fabDailyReport:FloatingActionButton
    private lateinit var fabChemicalReport:FloatingActionButton
    private lateinit var fabRiskReport:FloatingActionButton
    private lateinit var fabPermitReport:FloatingActionButton
    private lateinit var rvReports:RecyclerView

    private val showReportsViewModel:ShowReportsViewModel by viewModel()
    private val reportAdapter : ReportAdapter by inject()

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
        rvReports = view.findViewById(R.id.rv_reports)


        fabDailyReport.setOnClickListener {
//            findNavController().navigate(R.id.action_reportFragment_to_dailyReportFragment)
            val dailyReportFragment = DailyReportFragment()
            replaceFragment(dailyReportFragment)
        }

        fabChemicalReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_chemicalReportFragment)
        }

        fabRiskReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_dangerReportFragment)
        }

        fabPermitReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_permitFragment)
        }

        showReportsViewModel.showReportsLiveData.observe(viewLifecycleOwner){
            reportAdapter.differ.submitList(it)
            rvReports.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = reportAdapter
                Log.d("TAG_FRAGMENT_REPORT", "onViewCreated: $it")
            }
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