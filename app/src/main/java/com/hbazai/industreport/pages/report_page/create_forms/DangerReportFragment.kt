package com.hbazai.industreport.pages.report_page.create_forms

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import com.hbazai.industreport.pages.report_page.viewModel.risk.CreateRiskReportViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.ShowUserInfoViewModel
import com.hbazai.industreport.utils.SendToken
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class DangerReportFragment : Fragment() {

    private lateinit var btnClose: ImageView
    private lateinit var btnSubmitRiskReport: Button
    private lateinit var etUnitRiskReport: EditText
    private lateinit var etDescriptionRiskReport: EditText
    private lateinit var etInstrumentRiskReport: EditText
    private lateinit var etTitleRiskReport: EditText
    private lateinit var tvTypeRiskReport: TextView
    private lateinit var etUserRiskReport: TextView
    private lateinit var tvRiskDateTime: TextView

    private lateinit var pbSubmitForm:ProgressBar


    private val createRiskReportViewModel:CreateRiskReportViewModel by viewModel()
    private val showUserInfoViewModel: ShowUserInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danger_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClose = view.findViewById(R.id.btn_close_risk)
        btnSubmitRiskReport = view.findViewById(R.id.btn_create_risk_report)
        etUnitRiskReport = view.findViewById(R.id.et_unit_risk_report)
        etDescriptionRiskReport = view.findViewById(R.id.et_description_risk_report)
        etInstrumentRiskReport = view.findViewById(R.id.et_instrument_risk_report)
        etTitleRiskReport = view.findViewById(R.id.et_title_risk_report)
        tvTypeRiskReport = view.findViewById(R.id.risk_report_type)
        tvRiskDateTime = view.findViewById(R.id.tv_risk_date_time)
        etUserRiskReport = view.findViewById(R.id.et_user_risk_report)

        pbSubmitForm = view.findViewById(R.id.pb_submit_form)

        // Get the shared preferences file
        val sharedPrefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        // Retrieve the value for the key "token"
        val token = sharedPrefs.getString("token", "")
        val sendToken = SendToken(token)
        showUserInfoViewModel.showUserInfo(sendToken)
        showUserInfoViewModel.showUserInfoLiveData.observe(viewLifecycleOwner){
            if (it != null){
                etUserRiskReport.text = "${it.firstName} ${it.lastName}"
            }else{
                Toast.makeText(requireContext(),"اشکال در دریافت اطلاعات",Toast.LENGTH_SHORT).show()
            }
        }


        tvRiskDateTime.text = "${LocalDate.now()}"

        btnClose.setOnClickListener {
            val replaceFragment = ReportFragment()
            replaceFragment(replaceFragment)
        }

        btnSubmitRiskReport.setOnClickListener {

            btnSubmitRiskReport.visibility = View.GONE
            pbSubmitForm.visibility = View.VISIBLE

            val riskReportBody = RequestCreateRiskReport(
                date = LocalDate.now().toString(),
                image = "Link to image",
                unit = etUnitRiskReport.text.toString(),
                description = etDescriptionRiskReport.text.toString(),
                instrumentTag = etInstrumentRiskReport.text.toString(),
                userToken = token,
                time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString(),
                title = etTitleRiskReport.text.toString(),
                type = tvTypeRiskReport.text.toString(),
                userId = etUserRiskReport.text.toString(),
                reportType = "Risk"
            )

            createRiskReportViewModel.createRiskReport(riskReportBody)

            createRiskReportViewModel.createRiskReportLiveData.observe(viewLifecycleOwner){
                if (it != null){
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                    btnSubmitRiskReport.visibility = View.VISIBLE
                    pbSubmitForm.visibility = View.GONE
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