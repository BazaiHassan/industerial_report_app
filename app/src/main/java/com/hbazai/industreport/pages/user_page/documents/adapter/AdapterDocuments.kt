package com.hbazai.industreport.pages.user_page.documents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.user_page.documents.data.ResponseShowDocs

class AdapterDocuments : RecyclerView.Adapter<AdapterDocuments.DocumentsViewHolder>() {

    inner class DocumentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ResponseShowDocs>() {
        override fun areItemsTheSame(
            oldItem: ResponseShowDocs,
            newItem: ResponseShowDocs
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseShowDocs,
            newItem: ResponseShowDocs
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        return DocumentsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_documents,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        val document = differ.currentList[position]
        val tvTitleDoc = holder.itemView.findViewById<TextView>(R.id.tv_title_doc)
        val tvUnitDoc = holder.itemView.findViewById<TextView>(R.id.tv_unit_doc)
        val tvDescribeDoc = holder.itemView.findViewById<TextView>(R.id.tv_describe_doc)

        holder.itemView.apply {
            tvTitleDoc.text = document.docTitle
            tvDescribeDoc.text = document.docDescribe
            tvUnitDoc.text = document.docUnit

            holder.itemView.setOnClickListener {
                onBtnReadClickListener?.invoke(document)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onBtnReadClickListener: ((ResponseShowDocs) -> Unit)? = null


    fun btnReadSetOnClickListener(readListener: (ResponseShowDocs) -> Unit) {
        onBtnReadClickListener = readListener
    }
}