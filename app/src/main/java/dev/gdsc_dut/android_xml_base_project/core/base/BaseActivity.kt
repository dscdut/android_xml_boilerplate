package dev.gdsc_dut.android_xml_base_project.core.base

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

open class BaseActivity(
    @LayoutRes contentLayoutId: Int,
) : AppCompatActivity(contentLayoutId) {
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("$this::onCreate: $savedInstanceState")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        Timber.d("$this::onDestroy")
    }
}
