package com.hbazai.industreport.utils

import android.view.View

class ComponentView<T : View>(vararg views: T) {
    private val viewList: List<T> = views.toList()

    fun makeVisible() {
        for (view in viewList) {
            view.visibility = View.VISIBLE
        }
    }

    fun makeGone() {
        for (view in viewList) {
            view.visibility = View.GONE
        }
    }
}

