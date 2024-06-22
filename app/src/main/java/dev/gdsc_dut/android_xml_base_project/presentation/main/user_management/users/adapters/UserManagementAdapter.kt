package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users.adapters

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.account.AccountFragment
import dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users.UsersFragment

public class UserManagementAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): BaseFragment {
        when(position) {
            0 -> return AccountFragment()
            1 -> return UsersFragment()
        }
        return AccountFragment()
    }
}