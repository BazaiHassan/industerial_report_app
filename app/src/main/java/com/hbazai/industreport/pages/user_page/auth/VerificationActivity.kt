package com.hbazai.industreport.pages.user_page.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.hbazai.industreport.MainActivity
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.viewModel.GetTokenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerificationActivity : AppCompatActivity() {

    private lateinit var btnCheckCode: Button
    private lateinit var etVerifyCode: EditText
    private lateinit var pbCheckCode: ProgressBar
    private lateinit var btnBackVerify:ImageView
    private var codeServer:String? = null
    private var phoneNumber:String? = null
    private val getTokenViewModel: GetTokenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        codeServer = intent.getStringExtra("codeServer")
        phoneNumber = intent.getStringExtra("phoneNumber")

        btnCheckCode = findViewById(R.id.btn_verify_code)
        etVerifyCode = findViewById(R.id.et_verify_code)
        pbCheckCode = findViewById(R.id.pb_verify_code)
        btnBackVerify = findViewById(R.id.btn_back_verify)

        btnBackVerify.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCheckCode.setOnClickListener {
            if (etVerifyCode.text.isEmpty()){
                etVerifyCode.error = "شماره شمراه را وارد کنید"
            }else{
                btnCheckCode.visibility = View.GONE
                pbCheckCode.visibility = View.VISIBLE
                val requestPhoneNumber = RequestPhoneNumber(phoneNumber)
                if (etVerifyCode.text.toString() == codeServer){
                    getTokenViewModel.getToken(requestPhoneNumber)
                    getTokenViewModel.getTokenLiveData.observe(this){
                        if (it.status == "true"){
                            // Save Token To Shared Pref
                            editor.putString("token", it.message)
                            editor.apply()

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else if(it.status == "false") {
                            Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                            btnCheckCode.visibility = View.VISIBLE
                            pbCheckCode.visibility = View.GONE

                        }else{
                            Toast.makeText(this,"خطایی رخ داده است", Toast.LENGTH_SHORT).show()
                            btnCheckCode.visibility = View.VISIBLE
                            pbCheckCode.visibility = View.GONE
                        }
                    }
                }else{
                    btnCheckCode.visibility = View.VISIBLE
                    pbCheckCode.visibility = View.GONE
                    Toast.makeText(this,"کد وارد شده اشتباه می باشد", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}