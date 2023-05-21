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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ReportFragment
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import com.hbazai.industreport.pages.report_page.viewModel.UploadReportImageViewModel
import com.hbazai.industreport.pages.report_page.viewModel.chemical.CreateChemicalReportViewModel
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


class ChemicalReportFragment : Fragment() {

    private lateinit var btnClose: ImageView
    private lateinit var btnSubmitChemicalReport: Button
    private lateinit var etUnitChemicalReport: EditText
    private lateinit var etDescriptionChemicalReport: EditText
    private lateinit var etMaterialChemicalReport: EditText
    private lateinit var etUserChemicalReport: TextView
    private lateinit var etTitleChemicalReport: EditText
    private lateinit var tvTypeChemicalReport: TextView
    private lateinit var tvChemicalDateTime: TextView

    private lateinit var imgImageUploaded: ImageView
    private lateinit var btnUploadImage: Button
    private lateinit var btnUploadReportImage: Button
    private lateinit var pbUploadImagePermit: ProgressBar
    private var selectedImageUri: Uri? = null
    private var imageLink: String = "No Link"
    private val uploadReportImageViewModel: UploadReportImageViewModel by viewModel()

    private lateinit var pbSubmitReport: ProgressBar

    private val createChemicalReportViewModel: CreateChemicalReportViewModel by viewModel()
    private val showUserInfoViewModel: ShowUserInfoViewModel by viewModel()

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

        imgImageUploaded = view.findViewById(R.id.img_image_uploaded)
        btnUploadImage = view.findViewById(R.id.btn_upload_image)
        btnUploadReportImage = view.findViewById(R.id.btn_image_permit_upload)
        pbUploadImagePermit = view.findViewById(R.id.pb_upload_image_permit)

        tvChemicalDateTime.text = "${LocalDate.now()}"

        pbSubmitReport = view.findViewById(R.id.pb_submit_form)

        // Get the shared preferences file
        val sharedPrefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        // Retrieve the value for the key "token"
        val token = sharedPrefs.getString("token", "")
        val sendToken = SendToken(token)
        showUserInfoViewModel.showUserInfo(sendToken)
        showUserInfoViewModel.showUserInfoLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                etUserChemicalReport.text = "${it.firstName} ${it.lastName}"
            } else {
                Toast.makeText(requireContext(), "اشکال در دریافت اطلاعات", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        tvChemicalDateTime.text = "${LocalDate.now()}"

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

        btnSubmitChemicalReport.setOnClickListener {
            val validator = EditTextValidator(
                etMaterialChemicalReport,
                etTitleChemicalReport,
                etUnitChemicalReport,
                etDescriptionChemicalReport
            )
            val isValid = validator.validate()

            if (isValid){
                btnSubmitChemicalReport.visibility = View.GONE
                pbSubmitReport.visibility = View.VISIBLE
                val chemicalReportBody = RequestCreateChemicalReport(
                    date = LocalDate.now().toString(),
                    image = imageLink,
                    unit = etUnitChemicalReport.text.toString(),
                    description = etDescriptionChemicalReport.text.toString(),
                    materialName = etMaterialChemicalReport.text.toString(),
                    userToken = token,
                    time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString(),
                    title = etTitleChemicalReport.text.toString(),
                    type = tvTypeChemicalReport.text.toString(),
                    userId = etUserChemicalReport.text.toString(),
                    reportType = "Chemical"
                )
                createChemicalReportViewModel.createChemicalReport(chemicalReportBody)

                createChemicalReportViewModel.createChemicalReportLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        btnSubmitChemicalReport.visibility = View.VISIBLE
                        pbSubmitReport.visibility = View.GONE
                    }
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