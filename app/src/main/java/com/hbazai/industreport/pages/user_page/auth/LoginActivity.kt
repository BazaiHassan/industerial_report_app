package com.hbazai.industreport.pages.user_page.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.viewModel.CheckPhoneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var btnCheckPhone:Button
    private lateinit var etPhoneNumber:EditText
    private lateinit var pbCheckPhone:ProgressBar
    private val checkPhoneViewModel:CheckPhoneViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnCheckPhone = findViewById(R.id.btn_phone_check)
        etPhoneNumber = findViewById(R.id.et_phone_number)
        pbCheckPhone = findViewById(R.id.pb_check_phone)

        btnCheckPhone.setOnClickListener {
            if (etPhoneNumber.text.isEmpty()){
                etPhoneNumber.error = "شماره شمراه را وارد کنید"
            }else{
                btnCheckPhone.visibility = View.GONE
                pbCheckPhone.visibility = View.VISIBLE
                val requestPhoneNumber = RequestPhoneNumber(etPhoneNumber.text.toString())
                checkPhoneViewModel.checkPhoneNumber(requestPhoneNumber)
                checkPhoneViewModel.checkPhoneLiveData.observe(this){
                    if (it.status == "true"){

                        val intent = Intent(this, VerificationActivity::class.java).apply {
                            putExtra("codeServer",it.message)
                            putExtra("phoneNumber", etPhoneNumber.text.toString())
                        }
                        startActivity(intent)
                        finish()
                    }else if(it.status == "false") {
                        Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                        btnCheckPhone.visibility = View.VISIBLE
                        pbCheckPhone.visibility = View.GONE

                    }else{
                        Toast.makeText(this,"خطایی رخ داده است",Toast.LENGTH_SHORT).show()
                        btnCheckPhone.visibility = View.VISIBLE
                        pbCheckPhone.visibility = View.GONE
                    }
                }
            }
        }

    }
}