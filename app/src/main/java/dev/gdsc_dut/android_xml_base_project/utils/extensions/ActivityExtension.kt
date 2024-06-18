package dev.gdsc_dut.android_xml_base_project.utils.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

// Copy from https://github.com/conena/nanokt/blob/0f3315d5a2d9ba602f6a4ea9757d859bdb040a29/nanokt-android/src/main/java/com/conena/nanokt/android/app/ActivityUtils.kt#L54
@Throws(ActivityNotFoundException::class)
inline fun <reified T : Activity> Activity.startActivity(
    options: Bundle? = null,
    intentEditor: Intent.() -> Unit = {},
) {
    startActivity(
        Intent(this, T::class.java).also(intentEditor),
        options,
    )
}

@Throws(ActivityNotFoundException::class)
inline fun <reified T : Activity> Fragment.startActivity(
    options: Bundle? = null,
    intentEditor: Intent.() -> Unit = {},
) {
    startActivity(
        Intent(this.context, T::class.java).also(intentEditor),
        options,
    )
}