package com.hbazai.industreport.pages.report_page

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.report_page.adapter.DailyReportAdapter
import com.hbazai.industreport.pages.report_page.viewModel.daily.ShowDailyReportsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class ShowDetailReportActivity : AppCompatActivity() {

    private lateinit var date:String
    private lateinit var image:String
    private lateinit var description:String
    private lateinit var time:String
    private lateinit var instrumentTag:String
    private lateinit var title:String
    private lateinit var unit:String
    private lateinit var userId:String
    private lateinit var type:String

    private lateinit var tvUserReportDetail:TextView
    private lateinit var tvTimeReportDetail:TextView
    private lateinit var tvDateReportDetail:TextView
    private lateinit var tvTypeReportDetail:TextView
    private lateinit var tvTitleReportDetail:TextView
    private lateinit var tvDescriptionReportDetail:TextView
    private lateinit var tvInstrumentReportDetail:TextView
    private lateinit var tvUnitReportDetail:TextView
    private lateinit var btnCreateComment:ImageButton

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

        tvUserReportDetail = findViewById(R.id.tv_user_report_detail)
        tvTimeReportDetail = findViewById(R.id.tv_time_report_detail)
        tvDateReportDetail = findViewById(R.id.tv_date_report_detail)
        tvTypeReportDetail = findViewById(R.id.tv_type_report_detail)
        tvDescriptionReportDetail = findViewById(R.id.tv_description_report_detail)
        tvTitleReportDetail = findViewById(R.id.tv_title_report_detail)
        tvInstrumentReportDetail = findViewById(R.id.tv_instrument_report_detail)
        tvUnitReportDetail = findViewById(R.id.tv_unit_report_detail)
        btnCreateComment = findViewById(R.id.btn_create_comment)

        tvUserReportDetail.text = userId
        tvTimeReportDetail.text = time
        tvDateReportDetail.text = date
        tvTypeReportDetail.text = type
        tvTitleReportDetail.text = title
        tvDescriptionReportDetail.text = description
        tvInstrumentReportDetail.text = instrumentTag
        tvUnitReportDetail.text = unit

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
