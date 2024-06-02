package dev.gdsc_dut.android_xml_base_project.presentation.main

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseActivity
import dev.gdsc_dut.android_xml_base_project.databinding.ActivityMainBinding

class MainActivity: BaseActivity(R.layout.activity_main) {

    private val binding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}