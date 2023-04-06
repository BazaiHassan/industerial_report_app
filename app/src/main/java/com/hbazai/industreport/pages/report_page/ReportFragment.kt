package com.hbazai.industreport.pages.report_page

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hbazai.industreport.R
import com.hbazai.industreport.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabDailyReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_dailyReportFragment)
        }

        binding.fabChemicalReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_chemicalReportFragment)
        }

        binding.fabRiskReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_dangerReportFragment)
        }

        binding.fabPermitReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportFragment_to_permitFragment)
        }

    }

    override fun onStart() {
        super.onStart()
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        view.visibility = View.VISIBLE
    }


}