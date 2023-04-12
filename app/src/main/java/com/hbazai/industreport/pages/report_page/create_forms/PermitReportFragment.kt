package com.hbazai.industreport.pages.report_page.create_forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import com.hbazai.industreport.pages.report_page.viewModel.permit.CreatePermitReportViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalTime

class PermitReportFragment : Fragment() {

    private lateinit var btnClose: ImageView
    private lateinit var btnSubmitPermitReport: Button
    private lateinit var etUnitPermitReport: EditText
    private lateinit var etDescriptionPermitReport: EditText
    private lateinit var etPermitNumberReport: EditText
    private lateinit var etTitlePermitReport: EditText
    private lateinit var tvTypePermitReport: TextView
    private lateinit var etUserPermitReport: EditText
    private lateinit var tvPermitDateTime: TextView
    private lateinit var rbPermitClosed:RadioButton
    private lateinit var rbPermitContinued:RadioButton
    private lateinit var rbPermitExpired:RadioButton

    private lateinit var pbSubmitForm:ProgressBar

    private var permitStatus:String = "بسته شد"

    private val createPermitReportViewModel:CreatePermitReportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClose = view.findViewById(R.id.btn_close_permit)
        btnSubmitPermitReport = view.findViewById(R.id.btn_create_permit_report)
        etUnitPermitReport = view.findViewById(R.id.et_unit_permit_report)
        etDescriptionPermitReport = view.findViewById(R.id.et_description_permit_report)
        etPermitNumberReport = view.findViewById(R.id.et_number_permit_report)
        etTitlePermitReport = view.findViewById(R.id.et_title_permit_report)
        tvTypePermitReport = view.findViewById(R.id.permit_report_type)
        tvPermitDateTime = view.findViewById(R.id.tv_permit_date_time)
        etUserPermitReport = view.findViewById(R.id.et_user_permit_report)
        rbPermitClosed = view.findViewById(R.id.permit_closed)
        rbPermitContinued = view.findViewById(R.id.permit_continued)
        rbPermitExpired = view.findViewById(R.id.permit_expired)

        pbSubmitForm = view.findViewById(R.id.pb_submit_form)

        btnClose.setOnClickListener {
            val replaceFragment = ReportFragment()
            replaceFragment(replaceFragment)
        }

        rbPermitClosed.setOnClickListener {
            permitStatus = "بسته شد"
        }

        rbPermitExpired.setOnClickListener {
            permitStatus = "باطل شد"
        }

        rbPermitContinued.setOnClickListener {
            permitStatus = "ادامه دارد"
        }

        btnSubmitPermitReport.setOnClickListener {

            btnSubmitPermitReport.visibility = View.GONE
            pbSubmitForm.visibility = View.VISIBLE

            val permitBody = RequestCreatePermitReport(
                date = LocalDate.now().toString(),
                image = "Link to image",
                unit = etUnitPermitReport.text.toString(),
                description = etDescriptionPermitReport.text.toString(),
                instrumentTag = etPermitNumberReport.text.toString(),
                userToken = "lsdbvcansvhbsicshaknmavesvdsv",
                time = LocalTime.now().toString(),
                title = etTitlePermitReport.text.toString(),
                type = tvTypePermitReport.text.toString(),
                userId = etUserPermitReport.text.toString(),
                status = permitStatus
            )

            createPermitReportViewModel.createPermitReport(permitBody)

            createPermitReportViewModel.createPermitReportLiveData.observe(viewLifecycleOwner){
                if (it != null){
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                    pbSubmitForm.visibility = View.GONE
                    btnSubmitPermitReport.visibility = View.VISIBLE
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