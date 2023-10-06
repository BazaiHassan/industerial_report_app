package com.hbazai.industreport.pages.user_page.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbazai.industreport.databinding.ActivityLoginBinding
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.viewModel.AuthViewModel
import com.hbazai.industreport.utils.SnackBarNotifier
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var userCode:String
    private val authViewModel:AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val snackBarNotifier = SnackBarNotifier(this, binding.root)

        binding.btnInviteCodeCheck.setOnClickListener {
            binding.btnCreateGroup.visibility = View.GONE
            binding.btnCreateGroup.visibility = View.GONE
            binding.btnInviteCodeCheck.visibility = View.GONE
            binding.pbCheckInviteCode.visibility = View.VISIBLE
            if (binding.etInviteCode.text.isNotEmpty()){
                val inviteCode = RequestCheckInviteCode(binding.etInviteCode.text.toString())
                authViewModel.checkInviteCode(inviteCode)
            }else{
                snackBarNotifier.showError("لطفا کد دعوت را وارد کنید")
            }

        }

        authViewModel.checkInviteCodeLiveData.observe(this){chkInviteCode->
            if (chkInviteCode.status=="true"){
                binding.pbCheckInviteCode.visibility = View.GONE
                binding.etInviteCode.visibility = View.GONE
                binding.btnCreateGroup.visibility = View.GONE
                binding.tvGroupName.text = chkInviteCode.groupInfo!!.groupName
                binding.tvGroupAdmin.text = chkInviteCode.groupInfo.username
                binding.tvGroupDesc.text = chkInviteCode.groupInfo.description
                binding.tvGroupMessage.text = chkInviteCode.message
                binding.lnrResponseCheckInviteCode.visibility = View.VISIBLE
                binding.rlPhoneValidation.visibility = View.VISIBLE
                binding.rlCodeValidation.visibility = View.GONE
            }else{
                snackBarNotifier.showError("گروهی با این کد وجود ندارد")
            }
        }

        binding.btnCreateGroup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnValidatePhone.setOnClickListener {
            if (binding.etPhoneNumber.text.isNotEmpty()){
                binding.rlPhoneValidation.visibility = View.GONE
                binding.rlCodeValidation.visibility = View.VISIBLE
                // Send Request
                val userPhoneNumber = RequestPhoneNumber(binding.etPhoneNumber.text.toString())
                authViewModel.checkPhoneNumber(userPhoneNumber)
                authViewModel.checkPhoneLiveData.observe(this){otp->
                    if (otp.status.toString() == "true"){
                        userCode = otp.message.toString()
                    }else{
                        snackBarNotifier.showError("خطایی رخ داده است")
                    }
                }
            }else{
                snackBarNotifier.showError("لطفا شماره همراه خود را وارد کنید")
            }
        }

        binding.btnValidateCode.setOnClickListener {
            if (binding.etCode.text.toString() == userCode){
                // Save in db
                // Intent to the app
                snackBarNotifier.showSuccess("عضویت شما تایید شد")
            }else{
                snackBarNotifier.showError("کد وارد شده صحیح نمی باشد")
            }
        }

    }
}