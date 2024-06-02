package dev.gdsc_dut.android_xml_base_project.core.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle

@Throws(ActivityNotFoundException::class)
inline fun <reified T : Activity> Activity.startActivity(
    options: Bundle? = null,
    intentEditor: Intent.() -> Unit = {}
) {
    startActivity(Intent(this, T::class.java).also(intentEditor), options)
}