package dev.gdsc_dut.android_xml_base_project

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Timber in debug builds
        Timber.plant(Timber.DebugTree())
    }
}