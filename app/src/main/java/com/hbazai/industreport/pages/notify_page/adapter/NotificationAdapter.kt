package com.hbazai.industreport.pages.notify_page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem

class NotificationAdapter :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ResponseShowNotificationItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseShowNotificationItem,
            newItem: ResponseShowNotificationItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseShowNotificationItem,
            newItem: ResponseShowNotificationItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notification,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = differ.currentList[position]
        val title: TextView = holder.itemView.findViewById(R.id.title_notification)
        val describe: TextView = holder.itemView.findViewById(R.id.description_notification)
        val user: TextView = holder.itemView.findViewById(R.id.user_notification)
        val date: TextView = holder.itemView.findViewById(R.id.date_notification)
        holder.itemView.apply {
            title.text = notification.title
            describe.text = notification.description
            user.text = notification.userId
            date.text = notification.date

            setOnItemClickListener {
                onItemClickListener?.let {
                    it(notification)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((ResponseShowNotificationItem) -> Unit)? = null

    private fun setOnItemClickListener(listener: (ResponseShowNotificationItem) -> Unit) {
        onItemClickListener = listener
    }

}