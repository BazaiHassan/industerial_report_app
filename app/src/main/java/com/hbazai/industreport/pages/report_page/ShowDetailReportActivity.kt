package com.hbazai.industreport.pages.report_page

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hbazai.industreport.MainActivity
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.report_page.adapter.AdapterComments
import com.hbazai.industreport.pages.report_page.adapter.DailyReportAdapter
import com.hbazai.industreport.pages.report_page.dataModel.RequestDeleteReport
import com.hbazai.industreport.pages.report_page.dataModel.comments.CreateCommentModel
import com.hbazai.industreport.pages.report_page.viewModel.DeleteReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.comment.CommentViewModel
import com.hbazai.industreport.pages.report_page.viewModel.daily.ShowDailyReportsViewModel
import com.hbazai.industreport.utils.AppPreferences
import com.hbazai.industreport.utils.SendReportToken
import com.hbazai.industreport.utils.SendToken
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class ShowDetailReportActivity : AppCompatActivity() {

    private lateinit var date: String
    private lateinit var image: String
    private lateinit var description: String
    private lateinit var time: String
    private lateinit var instrumentTag: String
    private lateinit var title: String
    private lateinit var unit: String
    private lateinit var userId: String
    private lateinit var type: String
    private lateinit var reportToken: String
    private lateinit var reportType: String

    private lateinit var tvUserReportDetail: TextView
    private lateinit var tvTimeReportDetail: TextView
    private lateinit var tvDateReportDetail: TextView
    private lateinit var tvTypeReportDetail: TextView
    private lateinit var tvTitleReportDetail: TextView
    private lateinit var tvDescriptionReportDetail: TextView
    private lateinit var tvInstrumentReportDetail: TextView
    private lateinit var tvUnitReportDetail: TextView
    private lateinit var btnCreateComment: ImageButton
    private lateinit var imgReportImage:ImageView
    private lateinit var btnRemoveReport:ImageView

    private lateinit var rvReportComments: RecyclerView

    private val commentViewModel: CommentViewModel by viewModel()
    private val adapterComments: AdapterComments by inject()
    private val deleteReportViewModel:DeleteReportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail_report)

        // Get Intents and set values
        val userToken = AppPreferences(this).returnUserToken()
        date = intent.getStringExtra("date").toString()
        image = intent.getStringExtra("image").toString()
        description = intent.getStringExtra("description").toString()
        time = intent.getStringExtra("time").toString()
        instrumentTag = intent.getStringExtra("instrumentTag").toString()
        title = intent.getStringExtra("title").toString()
        unit = intent.getStringExtra("unit").toString()
        userId = intent.getStringExtra("userId").toString()
        type = intent.getStringExtra("type").toString()
        reportToken = intent.getStringExtra("reportToken").toString()
        reportType = intent.getStringExtra("reportType").toString()

        tvUserReportDetail = findViewById(R.id.tv_user_report_detail)
        tvTimeReportDetail = findViewById(R.id.tv_time_report_detail)
        tvDateReportDetail = findViewById(R.id.tv_date_report_detail)
        tvTypeReportDetail = findViewById(R.id.tv_type_report_detail)
        tvDescriptionReportDetail = findViewById(R.id.tv_description_report_detail)
        tvTitleReportDetail = findViewById(R.id.tv_title_report_detail)
        tvInstrumentReportDetail = findViewById(R.id.tv_instrument_report_detail)
        tvUnitReportDetail = findViewById(R.id.tv_unit_report_detail)
        btnCreateComment = findViewById(R.id.btn_create_comment)
        rvReportComments = findViewById(R.id.rv_report_comments)
        imgReportImage = findViewById(R.id.img_report_images)
        btnRemoveReport = findViewById(R.id.btn_remove_report)

        tvUserReportDetail.text = userId
        tvTimeReportDetail.text = time
        tvDateReportDetail.text = date
        tvTypeReportDetail.text = type
        tvTitleReportDetail.text = title
        tvDescriptionReportDetail.text = description
        tvInstrumentReportDetail.text = instrumentTag
        tvUnitReportDetail.text = unit
        Glide.with(this).load(image).into(imgReportImage)

        imgReportImage.setOnClickListener {
            showFullScreenImage(imgReportImage)
        }

        btnRemoveReport.setOnClickListener {
            val requestDeleteReport = RequestDeleteReport(
                reportType = reportType,
                reportToken = reportToken,
                userToken = userToken
            )
            Log.d("TAG_SEND_REPORT_TOKEN", "onCreate:$requestDeleteReport ")
            showBottomSheetDialog(requestDeleteReport)

        }

        val sendReportToken = SendReportToken(reportToken)

        commentViewModel.showComments(sendReportToken)
        commentViewModel.showCommentsLiveData.observe(this) { listOfComments ->
            Log.d("TAG_LIST_OF_COMMENTS", "onCreate:$listOfComments ")
            if (listOfComments != null) {
                adapterComments.differ.submitList(listOfComments)
                rvReportComments.apply {
                    layoutManager = LinearLayoutManager(this@ShowDetailReportActivity, RecyclerView.VERTICAL, false)
                    adapter = adapterComments
                }
            }
        }

        btnCreateComment.setOnClickListener {
            showCustomDialog()
        }

    }

    private fun showCustomDialog() {
        val dialog = Dialog(this)
        val view = layoutInflater.inflate(R.layout.comment_dialog_form, null)

        dialog.setContentView(view)

        val btnSubmitComment = view.findViewById<Button>(R.id.btn_submit_comment)
        val comment = view.findViewById<EditText>(R.id.et_comment)
        val pbCreateComment = view.findViewById<ProgressBar>(R.id.pb_submit_comment)
        val userComment = view.findViewById<TextView>(R.id.tv_user_comment)
        userComment.text = userId


        btnSubmitComment.setOnClickListener {

            if (comment.text.trim().toString().isEmpty()) {
                comment.error = "لطفا این فیلد را تکمیل کنید"
            } else {
                btnSubmitComment.visibility = View.GONE
                pbCreateComment.visibility = View.VISIBLE
                val createComments = CreateCommentModel(
                    date = "${LocalDate.now().year}-${LocalDate.now().monthValue}-${LocalDate.now().dayOfMonth}",
                    reportToken = reportToken,
                    userId = userId,
                    comment = comment.text.toString(),
                    userToken = AppPreferences(this).returnUserToken()
                )
                commentViewModel.addComment(createComments)
                commentViewModel.addCommentLiveData.observe(this) { response ->
                    btnSubmitComment.visibility = View.VISIBLE
                    pbCreateComment.visibility = View.GONE
                    Toast.makeText(this,response.message,Toast.LENGTH_SHORT).show()
                }
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

    private fun showFullScreenImage(thumbnailImageView: ImageView) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.full_screen_image)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.setCancelable(true)

        val photoView = dialog.findViewById<PhotoView>(R.id.photoView)
        val imageUrl = image // Replace with the actual URL of the full-size image

        Glide.with(this)
            .load(imageUrl)
            .into(photoView)

        dialog.show()
    }

    private fun showBottomSheetDialog(requestDeleteReport: RequestDeleteReport) {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val questionTextView = bottomSheetView.findViewById<TextView>(R.id.questionTextView)
        val confirmButton = bottomSheetView.findViewById<Button>(R.id.confirmButton)
        val cancelButton = bottomSheetView.findViewById<Button>(R.id.cancelButton)

        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        // Set the click listener for the confirm button
        confirmButton.setOnClickListener {
            // Perform the desired action when confirmed
            deleteReportViewModel.deleteReport(requestDeleteReport)
            deleteReportViewModel.deleteReportLiveData.observe(this){
                Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()
            }
            bottomSheetDialog.dismiss() // Dismiss the bottom sheet dialog
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        // Set the click listener for the cancel button
        cancelButton.setOnClickListener {
            bottomSheetDialog.dismiss() // Dismiss the bottom sheet dialog
        }

        bottomSheetDialog.show()
    }


}
