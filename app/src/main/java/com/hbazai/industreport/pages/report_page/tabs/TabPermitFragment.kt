package com.hbazai.industreport.pages.report_page.tabs

import android.content.Intent
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
import com.hbazai.industreport.pages.report_page.ShowDetailReportActivity
import com.hbazai.industreport.pages.report_page.adapter.PermitReportAdapter
import com.hbazai.industreport.pages.report_page.adapter.RiskReportAdapter
import com.hbazai.industreport.pages.report_page.viewModel.permit.ShowPermitReportsViewModel
import com.hbazai.industreport.pages.report_page.viewModel.risk.ShowRiskReportsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabPermitFragment : Fragment() {

    private val showReportsViewModel: ShowPermitReportsViewModel by viewModel()
    private val reportAdapter : PermitReportAdapter by inject()
    private lateinit var rvReports: RecyclerView
    private lateinit var pbShowReports: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_permit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvReports = view.findViewById(R.id.rv_permit_reports)
        pbShowReports = view.findViewById(R.id.pb_show_reports)

        rvReports.visibility = View.GONE
        pbShowReports.visibility = View.VISIBLE

        showReportsViewModel.showPermitReportLiveData.observe(viewLifecycleOwner){
            if (it !=null){
                reportAdapter.differ.submitList(it)
                rvReports.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = reportAdapter
                    Log.d("TAG_FRAGMENT_REPORT", "onViewCreated: $it")
                }
                rvReports.visibility = View.VISIBLE
                pbShowReports.visibility = View.GONE

                reportAdapter.setOnItemClickListener {
                    val intent = Intent(requireActivity(), ShowDetailReportActivity::class.java)
                    intent.apply {
                        putExtra("id",it.id.toString())
                        putExtra("date",it.date.toString())
                        putExtra("image",it.image.toString())
                        putExtra("description",it.description.toString())
                        putExtra("time",it.time.toString())
                        putExtra("instrumentTag",it.instrumentTag.toString())
                        putExtra("title",it.title.toString())
                        putExtra("unit",it.unit.toString())
                        putExtra("userId",it.userId.toString())
                        putExtra("type",it.type.toString())
                        putExtra("reportToken",it.reportToken)
                        putExtra("reportType",it.reportType.toString())
                    }
                    startActivity(intent)
                }
            }else{
                Toast.makeText(requireContext(),"اشکال در ارتباط با سرور", Toast.LENGTH_SHORT).show()
            }

        }

    }

}