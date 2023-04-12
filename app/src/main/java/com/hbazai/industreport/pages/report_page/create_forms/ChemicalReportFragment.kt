package com.hbazai.industreport.pages.report_page.create_forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import com.hbazai.industreport.pages.report_page.viewModel.chemical.CreateChemicalReportViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalTime


class ChemicalReportFragment : Fragment() {

    private lateinit var btnClose: ImageView
    private lateinit var btnSubmitChemicalReport: Button
    private lateinit var etUnitChemicalReport: EditText
    private lateinit var etDescriptionChemicalReport: EditText
    private lateinit var etMaterialChemicalReport: EditText
    private lateinit var etUserChemicalReport: EditText
    private lateinit var etTitleChemicalReport: EditText
    private lateinit var tvTypeChemicalReport: TextView
    private lateinit var tvChemicalDateTime: TextView

    private lateinit var pbSubmitReport:ProgressBar

    private val createChemicalReportViewModel:CreateChemicalReportViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_chemical_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClose = view.findViewById(R.id.btn_close_chemical)

        btnSubmitChemicalReport = view.findViewById(R.id.btn_create_chemical_report)
        etUnitChemicalReport = view.findViewById(R.id.et_unit_chemical_report)
        etDescriptionChemicalReport = view.findViewById(R.id.et_description_chemical_report)
        etMaterialChemicalReport = view.findViewById(R.id.et_material_chemical_report)
        etTitleChemicalReport = view.findViewById(R.id.et_title_chemical_report)
        tvTypeChemicalReport = view.findViewById(R.id.chemical_report_type)
        tvChemicalDateTime = view.findViewById(R.id.tv_chemical_date_time)
        etUserChemicalReport = view.findViewById(R.id.et_user_chemical_report)

        tvChemicalDateTime.text = "${LocalDate.now()} ${LocalTime.now()}"

        pbSubmitReport = view.findViewById(R.id.pb_submit_form)

        btnSubmitChemicalReport.setOnClickListener {

            btnSubmitChemicalReport.visibility = View.GONE
            pbSubmitReport.visibility = View.VISIBLE

            val chemicalReportBody = RequestCreateChemicalReport(
                date = LocalDate.now().toString(),
                image = "Link to image",
                unit = etUnitChemicalReport.text.toString(),
                description = etDescriptionChemicalReport.text.toString(),
                materialName = etMaterialChemicalReport.text.toString(),
                userToken = "lsdbvcansvhbsicshaknmavesvdsv",
                time = LocalTime.now().toString(),
                title = etTitleChemicalReport.text.toString(),
                type = tvTypeChemicalReport.text.toString(),
                userId = etUserChemicalReport.text.toString()
            )

            createChemicalReportViewModel.createChemicalReport(chemicalReportBody)

            createChemicalReportViewModel.createChemicalReportLiveData.observe(viewLifecycleOwner){
                if (it != null){
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                    btnSubmitChemicalReport.visibility = View.VISIBLE
                    pbSubmitReport.visibility = View.GONE
                }
            }
        }


        btnClose.setOnClickListener {
            val replaceFragment = ReportFragment()
            replaceFragment(replaceFragment)
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