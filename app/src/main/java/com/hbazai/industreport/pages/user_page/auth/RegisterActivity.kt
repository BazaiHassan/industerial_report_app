package com.hbazai.industreport.pages.user_page.auth

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.hbazai.industreport.databinding.ActivityRegisterBinding
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.viewModel.AuthViewModel
import com.hbazai.industreport.utils.EditTextValidator
import com.hbazai.industreport.utils.SnackBarNotifier
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity:AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAlphabeticInputFilter(binding.etUserName)
        setAlphabeticInputFilter(binding.etEmail)
        val snackBarNotifier = SnackBarNotifier(this, binding.root)
        binding.btnPhoneCheckForCreatingGroup.setOnClickListener {
            val validationFrm = EditTextValidator(
                binding.etUserName,
                binding.etPassword,
                binding.etPhoneNumber,
                binding.etEmail
            )
            if (validationFrm.validate()) {
                val intent = Intent(this, VerificationActivity::class.java).apply {
                    putExtra("username", binding.etUserName.text.toString())
                    putExtra("password", binding.etPassword.text.toString())
                    putExtra("phone_number", binding.etPhoneNumber.text.toString())
                    putExtra("email", binding.etEmail.text.toString())
                }

                /***
                 * Sending request to server
                 * if the response was successful then go to the next activity
                 */
                val phoneNumber = RequestPhoneNumber(binding.etPhoneNumber.text.toString())
                authViewModel.checkPhoneNumber(phoneNumber)
                authViewModel.checkPhoneLiveData.observe(this) { otpRes ->
                    if (otpRes.status!! == "true") {
                        intent.putExtra("codeServer", otpRes.message)
                        startActivity(intent)
                    } else {
                        snackBarNotifier.showError("خطایی رخ داده است، لطفا دوباره سعی کنید")
                    }
                }

            } else {
                snackBarNotifier.showError("لطفا همه فیلد ها را تکمیل کنید")

            }
        }

    }

    private fun setAlphabeticInputFilter(editText: EditText) {
        val filter = InputFilter { source, _, _, dest, _, _ ->
            val inputText =
                SpannableStringBuilder(dest).replace(0, dest.length, source, 0, source.length)
            val validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

            for (i in inputText.length - 1 downTo 0) {
                if (!validChars.contains(inputText[i].toString())) {
                    inputText.delete(i, i + 1)
                }
            }

            if (inputText.length == source.length) null else inputText
        }

        val existingFilters = editText.filters
        val newFilters = if (existingFilters != null) {
            existingFilters + filter
        } else {
            arrayOf(filter)
        }

        editText.filters = newFilters
    }
}