package com.hbazai.industreport.pages.report_page.create_forms

import android.Manifest
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import com.hbazai.industreport.pages.report_page.viewModel.UploadReportImageViewModel
import com.hbazai.industreport.pages.report_page.viewModel.risk.CreateRiskReportViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.ShowUserInfoViewModel
import com.hbazai.industreport.utils.EditTextValidator
import com.hbazai.industreport.utils.SendToken
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
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
    private lateinit var pbSubmitForm: ProgressBar

    private lateinit var imgImageUploaded: ImageView
    private lateinit var btnUploadImage: Button
    private lateinit var btnUploadReportImage: Button
    private lateinit var pbUploadImagePermit: ProgressBar
    private var selectedImageUri: Uri? = null
    private var imageLink: String = "No Link"
    private val uploadReportImageViewModel: UploadReportImageViewModel by viewModel()

    private val createRiskReportViewModel: CreateRiskReportViewModel by viewModel()
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

        imgImageUploaded = view.findViewById(R.id.img_image_uploaded)
        btnUploadImage = view.findViewById(R.id.btn_upload_image)
        btnUploadReportImage = view.findViewById(R.id.btn_image_permit_upload)
        pbUploadImagePermit = view.findViewById(R.id.pb_upload_image_permit)

        pbSubmitForm = view.findViewById(R.id.pb_submit_form)

        // Get the shared preferences file
        val sharedPrefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        // Retrieve the value for the key "token"
        val token = sharedPrefs.getString("token", "")
        val sendToken = SendToken(token)
        showUserInfoViewModel.showUserInfo(sendToken)
        showUserInfoViewModel.showUserInfoLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                etUserRiskReport.text = "${it.firstName} ${it.lastName}"
            } else {
                Toast.makeText(requireContext(), "اشکال در دریافت اطلاعات", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        tvRiskDateTime.text = "${LocalDate.now()}"

        btnUploadImage.setOnClickListener {
            // Request both READ_EXTERNAL_STORAGE and CAMERA permissions
            Dexter.withContext(context)
                .withPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        // Check if all permissions are granted
                        if (report?.areAllPermissionsGranted() == true) {
                            // Permissions are granted, do something here
                            ImagePicker.with(requireActivity())
                                .crop()
                                .galleryMimeTypes(  //Exclude gif images
                                    mimeTypes = arrayOf(
                                        "image/png",
                                        "image/jpg",
                                        "image/jpeg"
                                    )
                                )
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .createIntent { intent ->
                                    startForProfileImageResult.launch(intent)
                                }
                        } else {
                            // Permissions are not granted, handle the case here
                            Toast.makeText(
                                requireContext(),
                                "مجوز دسترسی را باید تایید کنید",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        // Show a rationale message to the user if needed
                        token?.continuePermissionRequest()
                    }
                })
                .check()
        }

        btnUploadReportImage.setOnClickListener {
            btnUploadReportImage.visibility = View.GONE
            pbUploadImagePermit.visibility = View.VISIBLE
            uploadImage(requireView().context)

            uploadReportImageViewModel.uploadReportImageLiveData.observe(viewLifecycleOwner) { responseImage ->
                if (responseImage.status.toString() == "true") {
                    Toast.makeText(requireContext(), responseImage.message, Toast.LENGTH_SHORT)
                        .show()
                    pbUploadImagePermit.visibility = View.GONE
                    imageLink = responseImage.link.toString()
                    Glide.with(requireContext()).load(responseImage.link).into(imgImageUploaded)
                    btnUploadImage.visibility = View.GONE
                    imgImageUploaded.visibility = View.VISIBLE
                } else if (responseImage.status.toString() == "false") {
                    Toast.makeText(requireContext(), "اشکال در بارگذاری", Toast.LENGTH_SHORT)
                        .show()
                    pbUploadImagePermit.visibility = View.GONE
                    btnUploadReportImage.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "خطای ناشناس", Toast.LENGTH_SHORT)
                        .show()
                    pbUploadImagePermit.visibility = View.GONE
                    btnUploadReportImage.visibility = View.VISIBLE
                }
            }

        }

        btnClose.setOnClickListener {
            val replaceFragment = ReportFragment()
            replaceFragment(replaceFragment)
        }

        btnSubmitRiskReport.setOnClickListener {

            val validator = EditTextValidator(
                etDescriptionRiskReport,
                etInstrumentRiskReport,
                etTitleRiskReport,
                etUnitRiskReport
            )

            val isValid = validator.validate()

            if (isValid) {
                btnSubmitRiskReport.visibility = View.GONE
                pbSubmitForm.visibility = View.VISIBLE

                val riskReportBody = RequestCreateRiskReport(
                    date = LocalDate.now().toString(),
                    image = imageLink,
                    unit = etUnitRiskReport.text.toString(),
                    description = etDescriptionRiskReport.text.toString(),
                    instrumentTag = etInstrumentRiskReport.text.toString(),
                    userToken = token,
                    time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                        .toString(),
                    title = etTitleRiskReport.text.toString(),
                    type = tvTypeRiskReport.text.toString(),
                    userId = etUserRiskReport.text.toString(),
                    reportType = "Risk"
                )

                createRiskReportViewModel.createRiskReport(riskReportBody)

                createRiskReportViewModel.createRiskReportLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        btnSubmitRiskReport.visibility = View.VISIBLE
                        pbSubmitForm.visibility = View.GONE
                    }
                }
            }


        }


    }

    override fun onStart() {
        super.onStart()
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        view.visibility = View.GONE
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val imageUri = data?.data
                selectedImageUri = imageUri
                btnUploadReportImage.visibility = View.VISIBLE
                btnUploadImage.text = "آماده بارگذاری"
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "بیخیالش شدم", Toast.LENGTH_SHORT).show()
            }
        }

    private fun uploadImage(context: Context) {

        val inputStream = context.contentResolver.openInputStream(selectedImageUri!!)
        val fileName = getFileName(context, selectedImageUri!!)
        val requestBody = inputStream?.use { stream ->
            stream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
        }

        val body = MultipartBody.Part.createFormData("image", fileName, requestBody!!)
        pbUploadImagePermit.progress = 0

        uploadReportImageViewModel.uploadReportImage(body)

    }

    private fun getFileName(context: Context, uri: Uri): String? {
        val file = File(uri.path!!)
        return file.name
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