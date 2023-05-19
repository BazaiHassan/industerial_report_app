package com.hbazai.industreport.pages.report_page.create_forms

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.viewModel.daily.CreateDailyReportViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.ShowUserInfoViewModel
import com.hbazai.industreport.utils.SendToken
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class DailyReportFragment : Fragment() {

    private lateinit var btnClose: ImageView
    private lateinit var btnSubmitDailyReport: Button
    private lateinit var etUnitDailyReport: EditText
    private lateinit var etDescriptionDailyReport: EditText
    private lateinit var etInstrumentDailyReport: EditText
    private lateinit var etUserDailyReport: TextView
    private lateinit var etTitleDailyReport: EditText
    private lateinit var tvTypeDailyReport: TextView
    private lateinit var tvDateTime: TextView

    private lateinit var pbSubmitReport:ProgressBar

    private val createDailyReportViewModel: CreateDailyReportViewModel by viewModel()
    private val showUserInfoViewModel: ShowUserInfoViewModel by viewModel()

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

        pbSubmitReport = view.findViewById(R.id.pb_submit_form)

        tvDateTime.text = "${LocalDate.now()}"

        // Get the shared preferences file
        val sharedPrefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        // Retrieve the value for the key "token"
        val token = sharedPrefs.getString("token", "")
        val sendToken = SendToken(token)
        showUserInfoViewModel.showUserInfo(sendToken)
        showUserInfoViewModel.showUserInfoLiveData.observe(viewLifecycleOwner){
            if (it != null){
                etUserDailyReport.text = "${it.firstName} ${it.lastName}"
            }else{
                Toast.makeText(requireContext(),"اشکال در دریافت اطلاعات",Toast.LENGTH_SHORT).show()
            }
        }

        btnClose.setOnClickListener {
            val replaceFragment = ReportFragment()
            replaceFragment(replaceFragment)
        }

        btnSubmitDailyReport.setOnClickListener {

            btnSubmitDailyReport.visibility = View.GONE
            pbSubmitReport.visibility = View.VISIBLE

            val dailyReportBody = RequestCreateDailyReport(
                date = LocalDate.now().toString(),
                image = "Link to image",
                unit = etUnitDailyReport.text.toString(),
                description = etDescriptionDailyReport.text.toString(),
                instrumentTag = etInstrumentDailyReport.text.toString(),
                userToken = token,
                time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString(),
                title = etTitleDailyReport.text.toString(),
                type = tvTypeDailyReport.text.toString(),
                userId = etUserDailyReport.text.toString(),
                reportType = "Daily"
            )

            createDailyReportViewModel.createDailyReport(dailyReportBody)

            createDailyReportViewModel.createReportLiveData.observe(viewLifecycleOwner){
                if (it != null){
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    btnSubmitDailyReport.visibility = View.VISIBLE
                    pbSubmitReport.visibility = View.GONE
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