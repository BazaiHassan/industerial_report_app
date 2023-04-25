package com.hbazai.industreport.pages.user_page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.SplashActivity
import com.hbazai.industreport.pages.user_page.auth.viewModel.ShowUserInfoViewModel
import com.hbazai.industreport.utils.SendToken
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private lateinit var userProfileFullName:TextView
    private lateinit var userProfilePersonalNumber:TextView
    private lateinit var userProfileShift:TextView
    private lateinit var pbLoadProfile:ProgressBar
    private lateinit var svProfile:ScrollView
    private lateinit var btnExitProfile:LinearLayout

    private val showUserInfoViewModel:ShowUserInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userProfileFullName = view.findViewById(R.id.user_profile_full_name)
        userProfilePersonalNumber = view.findViewById(R.id.user_profile_personal_number)
        userProfileShift = view.findViewById(R.id.user_profile_shift)
        pbLoadProfile = view.findViewById(R.id.pb_load_profile)
        svProfile = view.findViewById(R.id.sv_profile)
        btnExitProfile = view.findViewById(R.id.btn_exit_profile)
        svProfile.visibility = View.GONE
        pbLoadProfile.visibility = View.VISIBLE
        // Get the shared preferences file
        val sharedPrefs = requireActivity().getSharedPreferences("myPrefs",Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        // Retrieve the value for the key "token"
        val token = sharedPrefs.getString("token", "")
        val sendToken = SendToken(token)
        showUserInfoViewModel.showUserInfo(sendToken)
        showUserInfoViewModel.showUserInfoLiveData.observe(viewLifecycleOwner){
            if (it != null){
                userProfileFullName.text = "${it.firstName} ${it.lastName}"
                userProfilePersonalNumber.text = it.personalNumber
                userProfileShift.text = it.shift
                pbLoadProfile.visibility = View.GONE
                svProfile.visibility = View.VISIBLE
            }else{
                Toast.makeText(requireContext(),"اشکال در دریافت اطلاعات",Toast.LENGTH_SHORT).show()
            }
        }

        btnExitProfile.setOnClickListener {
            editor.clear()
            editor.apply()
            val intent = Intent(requireActivity(),SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}