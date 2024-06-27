package dev.gdsc_dut.android_xml_base_project.utils.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

fun View.hideSoftKeyboard() {
    val imm = getSystemService(context, InputMethodManager::class.java)
    imm?.hideSoftInputFromWindow(this.windowToken, 0)
}