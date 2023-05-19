package com.hbazai.industreport.pages.report_page

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.report_page.adapter.AdapterComments
import com.hbazai.industreport.pages.report_page.adapter.DailyReportAdapter
import com.hbazai.industreport.pages.report_page.dataModel.comments.CreateCommentModel
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

    private lateinit var tvUserReportDetail: TextView
    private lateinit var tvTimeReportDetail: TextView
    private lateinit var tvDateReportDetail: TextView
    private lateinit var tvTypeReportDetail: TextView
    private lateinit var tvTitleReportDetail: TextView
    private lateinit var tvDescriptionReportDetail: TextView
    private lateinit var tvInstrumentReportDetail: TextView
    private lateinit var tvUnitReportDetail: TextView
    private lateinit var btnCreateComment: ImageButton

    private lateinit var rvReportComments: RecyclerView

    private val commentViewModel: CommentViewModel by viewModel()
    private val adapterComments: AdapterComments by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail_report)

        // Get Intents and set values
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

        tvUserReportDetail.text = userId
        tvTimeReportDetail.text = time
        tvDateReportDetail.text = date
        tvTypeReportDetail.text = type
        tvTitleReportDetail.text = title
        tvDescriptionReportDetail.text = description
        tvInstrumentReportDetail.text = instrumentTag
        tvUnitReportDetail.text = unit

        val sendReportToken = SendReportToken(reportToken)
        Log.d("TAG_SEND_REPORT_TOKEN", "onCreate:$sendReportToken ")
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
}
