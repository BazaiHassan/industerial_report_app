package com.hbazai.industreport.pages.report_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.adapter.DailyReportAdapter
import com.hbazai.industreport.pages.report_page.viewModel.daily.ShowDailyReportsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        tvUserReportDetail.text = userId
        tvTimeReportDetail.text = time
        tvDateReportDetail.text = date
        tvTypeReportDetail.text = type
        tvTitleReportDetail.text = title
        tvDescriptionReportDetail.text = description
        tvInstrumentReportDetail.text = instrumentTag
        tvUnitReportDetail.text = unit



    }
}
