package com.hbazai.industreport.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class SnackBarNotifier(private val context: Context, private val rootView: View) {

    fun showSuccess(message: String) {
        showSnackBar(message, android.R.color.holo_green_light)
    }

    fun showError(message: String) {
        showSnackBar(message, android.R.color.holo_red_light)
    }

    private fun showSnackBar(message: String, backgroundColor: Int) {
        val snackBar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))

        // Set the anchor view to the root view to show the Snackbar at the top
        val params = snackBar.view.layoutParams as CoordinatorLayout.LayoutParams
        params.anchorId = View.NO_ID
        params.gravity = Gravity.TOP
        snackBar.view.layoutParams = params

        snackBar.show()
    }
}

