package com.hbazai.industreport.pages.report_page.create_forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.viewModel.CreateDailyReportViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalTime


class DailyReportFragment : Fragment() {

    private lateinit var btnClose: ImageView
    private lateinit var btnSubmitDailyReport: Button
    private lateinit var etUnitDailyReport: EditText
    private lateinit var etDescriptionDailyReport: EditText
    private lateinit var etInstrumentDailyReport: EditText
    private lateinit var etUserDailyReport: EditText
    private lateinit var etTitleDailyReport: EditText
    private lateinit var tvTypeDailyReport: TextView
    private lateinit var tvDateTime: TextView

    private val createDailyReportViewModel: CreateDailyReportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClose = view.findViewById(R.id.btn_close_daily)
        btnSubmitDailyReport = view.findViewById(R.id.btn_create_daily_report)
        etUnitDailyReport = view.findViewById(R.id.et_unit_daily_report)
        etDescriptionDailyReport = view.findViewById(R.id.et_description_daily_report)
        etInstrumentDailyReport = view.findViewById(R.id.et_instrument_daily_report)
        etTitleDailyReport = view.findViewById(R.id.et_title_daily_report)
        tvTypeDailyReport = view.findViewById(R.id.daily_report_type)
        tvDateTime = view.findViewById(R.id.tv_date_time)
        etUserDailyReport = view.findViewById(R.id.et_user_daily_report)

        tvDateTime.text = "${LocalDate.now()} ${LocalTime.now()}"

        btnClose.setOnClickListener {
            val replaceFragment = ReportFragment()
            replaceFragment(replaceFragment)
        }

        btnSubmitDailyReport.setOnClickListener {
            val dailyReportBody = RequestCreateDailyReport(
                date = LocalDate.now().toString(),
                image = "Link to image",
                unit = etUnitDailyReport.text.toString(),
                description = etDescriptionDailyReport.text.toString(),
                instrumentTag = etInstrumentDailyReport.text.toString(),
                userToken = "lsdbvcansvhbsicshaknmavesvdsv",
                time = LocalTime.now().toString(),
                title = etTitleDailyReport.text.toString(),
                type = tvTypeDailyReport.text.toString(),
                userId = etUserDailyReport.text.toString()
            )

            createDailyReportViewModel.createReport(dailyReportBody)

            createDailyReportViewModel.createReportLiveData.observe(viewLifecycleOwner){
                if (it != null){
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        view.visibility = View.GONE
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