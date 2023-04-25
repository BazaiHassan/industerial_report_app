package com.hbazai.industreport.pages.notify_page

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.notify_page.adapter.NotificationAdapter
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.notify_page.viewModel.CreateNotificationViewModel
import com.hbazai.industreport.pages.notify_page.viewModel.ShowNotificationViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.ShowUserInfoViewModel
import com.hbazai.industreport.utils.SendToken
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class NotifyFragment : Fragment() {

    private lateinit var btnCreateAlert: Button
    private lateinit var rvNotifications: RecyclerView
    private val createNotificationViewModel: CreateNotificationViewModel by viewModel()
    private val showNotificationViewModel: ShowNotificationViewModel by viewModel()
    private val notificationAdapter: NotificationAdapter by inject()
    private val showUserInfoViewModel: ShowUserInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCreateAlert = view.findViewById(R.id.btn_create_alert)
        rvNotifications = view.findViewById(R.id.rv_notifications)

        // Get the shared preferences file
        val sharedPrefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        // Retrieve the value for the key "token"
        val token = sharedPrefs.getString("token", "")
        val sendToken = SendToken(token)
        showUserInfoViewModel.showUserInfo(sendToken)

        showNotificationViewModel.showNotificationLiveData.observe(viewLifecycleOwner){
            if (it != null){
                notificationAdapter.differ.submitList(it)
                rvNotifications.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = notificationAdapter
                }
            }
        }

        btnCreateAlert.setOnClickListener {
            showCustomDialog()
        }

    }

    private fun showCustomDialog() {
        val dialog = Dialog(requireContext())
        val view = layoutInflater.inflate(R.layout.notification_dialog_form, null)

        dialog.setContentView(view)

        val btnSubmitAlert = view.findViewById<Button>(R.id.btn_submit_alert)
        val title = view.findViewById<EditText>(R.id.et_title_notification)
        val describe = view.findViewById<EditText>(R.id.et_describe_notification)
        val rbCommon = view.findViewById<RadioButton>(R.id.common_notification)
        val rbImportant = view.findViewById<RadioButton>(R.id.important_notification)
        val rbVeryImportant = view.findViewById<RadioButton>(R.id.very_important_notification)
        val pbCreateNotification = view.findViewById<ProgressBar>(R.id.pb_submit_alert)
        var alertType: String = "عمومی"
        var userIdAlert:String? = null
        // Set Notification Type
        rbCommon.setOnClickListener {
            alertType = "عمومی"
        }

        rbImportant.setOnClickListener {
            alertType = "مهم"
        }

        rbVeryImportant.setOnClickListener {
            alertType = "خیلی مهم"
        }

        showUserInfoViewModel.showUserInfoLiveData.observe(viewLifecycleOwner){
            if (it != null){
                userIdAlert = "${it.firstName} ${it.lastName}"
            }else{
                Toast.makeText(requireContext(),"اشکال در دریافت اطلاعات",Toast.LENGTH_SHORT).show()
            }
        }


        btnSubmitAlert.setOnClickListener {

            if (title.text.trim().toString().isEmpty()) {
                title.error = "لطفا این فیلد را تکمیل کنید"
            } else if (describe.text.trim().toString().isEmpty()) {
                describe.error = "لطفا این فیلد را تکمیل کنید"
            } else {

                val requestCreateNotification = RequestCreateNotification(
                    date = LocalDate.now().toString(),
                    importanceLvl = alertType,
                    userId = userIdAlert,
                    description = describe.text.toString(),
                    title = title.text.toString()
                )

                btnSubmitAlert.visibility = View.GONE
                pbCreateNotification.visibility = View.VISIBLE
                createNotificationViewModel.createNotification(requestCreateNotification)
                createNotificationViewModel.createNotificationLiveData.observe(viewLifecycleOwner) {
                    if (it.message == "اعلان با موفقیت ثبت گردید") {
                        pbCreateNotification.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        pbCreateNotification.visibility = View.GONE
                        btnSubmitAlert.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
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