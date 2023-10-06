package com.hbazai.industreport.pages.user_page.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hbazai.industreport.MainActivity
import com.hbazai.industreport.R
import com.hbazai.industreport.databinding.ActivityVerificationBinding
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.viewModel.AuthViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.GetTokenViewModel
import com.hbazai.industreport.utils.EditTextValidator
import com.hbazai.industreport.utils.SnackBarNotifier
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding
    private var codeServer: String? = null
    private var phoneNumber: String? = null
    private var userName: String? = null
    private var email: String? = null
    private var password: String? = null
    private var groupDescription: String = "توضیحی وجود ندارد"

    private val authViewModel:AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val snackBarNotifier = SnackBarNotifier(this,binding.root)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        codeServer = intent.getStringExtra("codeServer")
        phoneNumber = intent.getStringExtra("phone_number")
        userName = intent.getStringExtra("username")
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")

        binding.lnrGroupCreation.visibility = View.GONE
        binding.lnrInviteCode.visibility = View.GONE
        binding.lnrVerifyPhone.visibility = View.VISIBLE


        binding.btnVerifyCode.setOnClickListener {
            if (binding.etVerifyCode.text.toString() == codeServer){
                snackBarNotifier.showSuccess("شماره تلفن شما تایید شد، لطفا فیلدهای زیر را تکمیل نمایید")
                binding.lnrVerifyPhone.visibility = View.GONE
                binding.lnrGroupCreation.visibility = View.VISIBLE
                binding.lnrInviteCode.visibility = View.GONE
            }else{
                snackBarNotifier.showError("کد وارد شده صحیح نمی باشد")
            }
        }

        binding.btnGroupCreation.setOnClickListener {
            binding.btnGroupCreation.isEnabled = false
            val validationInput = EditTextValidator(binding.etGroupName)
            if (!validationInput.validate()){
                snackBarNotifier.showError("لطفا نام گروه را وارد کنید")
            }
            // Send request to server to create the group
            val createGroupEntryInfo = RequestCreateGroup(
                password = password,
                groupName = binding.etGroupName.text.toString(),
                position = "Admin",
                email = email,
                groupDescription = groupDescription,
                username = userName,
                phoneNumber = phoneNumber
            )
            authViewModel.createGroup(createGroupEntryInfo)
            authViewModel.createGroupLiveData.observe(this){createGroupRes->
                if (createGroupRes.status=="false"){
                    snackBarNotifier.showError("خطا در ایجاد گروه")
                    binding.btnGroupCreation.isEnabled = true
                }else{
                    binding.tvInviteCode.text = createGroupRes.groupInviteCode
                    snackBarNotifier.showSuccess("گروه با موفقیت ایجاد شد")
                    binding.lnrVerifyPhone.visibility = View.GONE
                    binding.lnrGroupCreation.visibility = View.GONE
                    binding.lnrInviteCode.visibility = View.VISIBLE

                }
            }
        }

        binding.btnCopyInviteCode.setOnClickListener {

        }

    }
}