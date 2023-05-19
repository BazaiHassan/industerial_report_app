package com.hbazai.industreport.pages.report_page.tabs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ShowDetailReportActivity
import com.hbazai.industreport.pages.report_page.adapter.DailyReportAdapter
import com.hbazai.industreport.pages.report_page.viewModel.daily.ShowDailyReportsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabDailyFragment : Fragment() {

    private val showReportsViewModel: ShowDailyReportsViewModel by viewModel()
    private val reportAdapter : DailyReportAdapter by inject()
    private lateinit var rvReports:RecyclerView
    private lateinit var pbShowReports: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_daily, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvReports = view.findViewById(R.id.rv_daily_reports)
        pbShowReports = view.findViewById(R.id.pb_show_reports)

        rvReports.visibility = View.GONE
        pbShowReports.visibility = View.VISIBLE

        showReportsViewModel.showReportsLiveData.observe(viewLifecycleOwner){ listOfDailyReports ->
            if (listOfDailyReports != null){
                reportAdapter.differ.submitList(listOfDailyReports)
                rvReports.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = reportAdapter
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
                        putExtra("reportToken",it.reportToken.toString())
                    }
                    startActivity(intent)
                }
            }else{
                Toast.makeText(requireContext(),"اشکال در ارتباط با سرور", Toast.LENGTH_SHORT).show()
            }

        }

    }
}