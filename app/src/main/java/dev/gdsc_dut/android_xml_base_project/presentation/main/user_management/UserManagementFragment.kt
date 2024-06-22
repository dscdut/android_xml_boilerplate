package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentUserManagementBinding
import dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users.adapters.UserManagementAdapter

class UserManagementFragment: BaseFragment(R.layout.fragment_user_management) {

    private val binding by viewBinding<FragmentUserManagementBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter
        val adapter = UserManagementAdapter(childFragmentManager, lifecycle)

        // Set the adapter to ViewPager2
        binding.viewpagerUserManagement.adapter = adapter

        // Link ViewPager2 and TabLayout
        TabLayoutMediator(binding.tablayoutUserManagement, binding.viewpagerUserManagement) { tab, position ->
            tab.text = when (position) {
                0 -> "Account"
                1 -> "Users"
                else -> null
            }
        }.attach()
    }
}