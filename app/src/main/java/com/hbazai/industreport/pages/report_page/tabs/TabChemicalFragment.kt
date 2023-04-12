package com.hbazai.industreport.pages.report_page.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.adapter.ChemicalReportAdapter
import com.hbazai.industreport.pages.report_page.viewModel.chemical.ShowChemicalReportsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabChemicalFragment : Fragment() {

    private val showChemicalReportsViewModel: ShowChemicalReportsViewModel by viewModel()
    private val reportAdapter : ChemicalReportAdapter by inject()
    private lateinit var rvReports: RecyclerView
    private lateinit var pbShowReports:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_chemical, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvReports = view.findViewById(R.id.rv_chemical_reports)
        pbShowReports = view.findViewById(R.id.pb_show_reports)

        rvReports.visibility = View.GONE
        pbShowReports.visibility = View.VISIBLE

        showChemicalReportsViewModel.showChemicalReportsLiveData.observe(viewLifecycleOwner){
            if (it != null){
                reportAdapter.differ.submitList(it)
                rvReports.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = reportAdapter
                    Log.d("TAG_FRAGMENT_REPORT", "onViewCreated: $it")
                }
                rvReports.visibility = View.VISIBLE
                pbShowReports.visibility = View.GONE
            }else{
                Toast.makeText(requireContext(),"اشکال در ارتباط با سرور",Toast.LENGTH_SHORT).show()
            }

        }

    }
}