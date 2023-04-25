package com.hbazai.industreport.pages.report_page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem

class RiskReportAdapter :
    RecyclerView.Adapter<RiskReportAdapter.ReportViewHolder>() {

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ResponseShowRiskReportItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseShowRiskReportItem,
            newItem: ResponseShowRiskReportItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseShowRiskReportItem,
            newItem: ResponseShowRiskReportItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_reports,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = differ.currentList[position]
        val title: TextView = holder.itemView.findViewById(R.id.title_text_view)
        val describe: TextView = holder.itemView.findViewById(R.id.description_text_view)
        val user: TextView = holder.itemView.findViewById(R.id.user_report)
        val date: TextView = holder.itemView.findViewById(R.id.date_report)
        holder.itemView.apply {
            title.text = report.title
            describe.text = report.description
            user.text = report.userId
            date.text = report.date

            setOnClickListener {
                onItemClickListener?.let {
                    it(report)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((ResponseShowRiskReportItem) -> Unit)? = null

    private fun setOnItemClickListener(listener: (ResponseShowRiskReportItem) -> Unit) {
        onItemClickListener = listener
    }

}