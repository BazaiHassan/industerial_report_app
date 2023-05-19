package com.hbazai.industreport.pages.report_page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.dataModel.comments.ResponseComments

class AdapterComments : RecyclerView.Adapter<AdapterComments.CommentsViewHolder>() {

    inner class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ResponseComments>() {
        override fun areItemsTheSame(
            oldItem: ResponseComments,
            newItem: ResponseComments
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseComments,
            newItem: ResponseComments
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_report_comments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = differ.currentList[position]
        val user: TextView = holder.itemView.findViewById(R.id.tv_comment_user)
        val commentText: TextView = holder.itemView.findViewById(R.id.tv_comment)
        val commentDate: TextView = holder.itemView.findViewById(R.id.tv_comment_date)

        holder.itemView.apply {
            user.text = comment.userId
            commentText.text = comment.comment
            commentDate.text = comment.date
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}