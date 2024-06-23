package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentAccountBinding

class AccountFragment : BaseFragment(R.layout.fragment_account) {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountViewModel by viewModels()

    companion object {
        fun newInstance() = AccountFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.etFullName.setText(user.name)
            }
            if (user != null) {
                binding.etEmailProfile.setText(user.email)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}