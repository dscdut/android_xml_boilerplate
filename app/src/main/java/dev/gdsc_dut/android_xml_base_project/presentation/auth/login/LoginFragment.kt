package dev.gdsc_dut.android_xml_base_project.presentation.auth.login

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()

    override fun setupView() {
        super.setupView()
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }
}