package com.hbazai.industreport.pages.user_page.documents

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.user_page.documents.adapter.AdapterDocuments
import com.hbazai.industreport.pages.user_page.documents.data.RequestUploadDocument
import com.hbazai.industreport.pages.user_page.documents.viewModel.UploadDocViewModel
import com.hbazai.industreport.utils.AppPreferences
import com.hbazai.industreport.utils.ComponentView
import com.hbazai.industreport.utils.Constants.Companion.REQUEST_PDF_SELECTION
import com.hbazai.industreport.utils.EditTextValidator
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.rajat.pdfviewer.PdfViewerActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.time.LocalDate

class DocumentsActivity : AppCompatActivity() {

    private lateinit var btnCreateDoc: Button
    private lateinit var rvDocuments: RecyclerView
    private var documentLink: String? = null

    // Dialog ui items
    private lateinit var btnSubmitDoc:Button
    private lateinit var btnUploadDoc:Button
    private lateinit var titleDoc:EditText
    private lateinit var describeDoc:EditText
    private lateinit var unitDoc:EditText
    private lateinit var uploadingText:TextView
    private lateinit var uploadingProgress:ProgressBar

    private var selectedPdfUri: Uri? = null
    private var startUploading:Boolean = false

    private val uploadDocViewModel: UploadDocViewModel by viewModel()
    private val adapterDocuments: AdapterDocuments by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)
        initLayer()

        btnCreateDoc.setOnClickListener {
            showCustomDialog()
        }

        uploadDocViewModel.showDocs()
        uploadDocViewModel.showDocsLiveData.observe(this) { listOfDocs ->
            if (listOfDocs != null) {
                adapterDocuments.differ.submitList(listOfDocs)
                rvDocuments.apply {
                    layoutManager = LinearLayoutManager(
                        this@DocumentsActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    adapter = adapterDocuments
                }

                adapterDocuments.btnReadSetOnClickListener {
                    startActivity(
                        // Use 'launchPdfFromPath' if you want to use assets file (enable "fromAssets" flag) / internal directory
                        PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                            this,
                            it.docLink,                                // PDF URL in String format
                            it.docTitle,                        // PDF Name/Title in String format
                            "",                  // If nothing specific, Put "" it will save to Downloads
                            enableDownload = true                    // This param is true by defualt.
                        )
                    )

                }
            }
        }

    }

    private fun initLayer() {
        btnCreateDoc = findViewById(R.id.btn_create_doc)
        rvDocuments = findViewById(R.id.rv_documents)
    }


    private fun showCustomDialog() {
        val dialog = Dialog(this)
        val view = layoutInflater.inflate(R.layout.document_upload_form, null)

        dialog.setContentView(view)

        btnSubmitDoc = view.findViewById(R.id.btn_submit_doc)
        btnUploadDoc = view.findViewById(R.id.btn_upload_doc)
        titleDoc = view.findViewById(R.id.et_title_doc)
        describeDoc = view.findViewById(R.id.et_describe_doc)
        unitDoc = view.findViewById(R.id.et_unit_doc)
        uploadingText = view.findViewById(R.id.tv_loading)
        uploadingProgress = view.findViewById(R.id.pb_uploading_doc)

        btnUploadDoc.setOnClickListener {
            // Permission
            // Request both READ_EXTERNAL_STORAGE
            Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        // Check if all permissions are granted
                        if (report?.areAllPermissionsGranted() == true) {
                            // Permissions are granted, do something here
                            openFileManager()

                        } else {
                            // Permissions are not granted, handle the case here
                            Toast.makeText(
                                this@DocumentsActivity,
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
            // Open Files
        }

        btnSubmitDoc.setOnClickListener {
            val requestUploadDocument = RequestUploadDocument(
                docUnit = unitDoc.text.toString(),
                docTitle = titleDoc.text.toString(),
                docLink = documentLink,
                docDescribe = describeDoc.text.toString(),
                userToken = AppPreferences(this).returnUserToken()
            )
            val validator = EditTextValidator(titleDoc, describeDoc, unitDoc)
            val isValid = validator.validate()
            if (isValid && documentLink != null) {
                uploadDocViewModel.createDocument(requestUploadDocument)
                uploadDocViewModel.createDocLiveData.observe(this) {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            } else {
                Toast.makeText(this, "از اپلود شدن فایل اطمینان حاصل کنید", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        dialog.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setGravity(Gravity.CENTER)

        }
        dialog.show()

    }

    private fun openFileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        startActivityForResult(intent, REQUEST_PDF_SELECTION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PDF_SELECTION && resultCode == Activity.RESULT_OK) {
            selectedPdfUri = data?.data
            uploadPdf(this)
        }
    }

    private fun uploadPdf(context: Context) {
        val inputStream = context.contentResolver.openInputStream(selectedPdfUri!!)
        val fileName = getFileName(context, selectedPdfUri!!)
        val requestBody = inputStream?.use { stream ->
            stream.readBytes().toRequestBody("application/pdf".toMediaTypeOrNull())
        }

        val body = MultipartBody.Part.createFormData("file", "$fileName.pdf", requestBody!!)

        // Replace `uploadReportImageViewModel` with your actual ViewModel instance
        uploadDocViewModel.uploadDocument(body)
        val componentUploadStart = ComponentView(uploadingProgress,uploadingText)
        val componentUploadFinish = ComponentView(btnUploadDoc)
        componentUploadStart.makeVisible()
        componentUploadFinish.makeGone()
        uploadDocViewModel.uploadDocLiveData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            documentLink = it.link
            componentUploadStart.makeGone()
            componentUploadFinish.makeVisible()
            uploadingText.setTextColor(ContextCompat.getColor(this,R.color.google_green))
            uploadingText.text = "بارگذاری فایل با موفقت انجام شد"
            uploadingText.visibility = View.VISIBLE

        }
    }


    private fun getFileName(context: Context, uri: Uri): String? {
        val file = File(uri.path!!)
        return file.name
    }
}