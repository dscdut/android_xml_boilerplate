package dev.gdsc_dut.android_xml_base_project.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.presentation.main.home.HomeFragment
import dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.UserManagementFragment

class MainAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): BaseFragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> UserManagementFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}