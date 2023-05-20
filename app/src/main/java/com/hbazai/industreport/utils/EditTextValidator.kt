package com.hbazai.industreport.utils

import android.widget.EditText

class EditTextValidator(vararg editTexts: EditText) {
    private val editTextList: List<EditText> = editTexts.toList()

    fun validate(): Boolean {
        var isValid = true

        for (editText in editTextList) {
            if (editText.text.toString().trim().isEmpty()) {
                editText.error = "این فیلد باید مقدار داشته باشد"
                isValid = false
            }
        }

        return isValid
    }
}
