package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentAccountBinding

@AndroidEntryPoint
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
        refreshData()

        binding.btnEditAccount.setOnClickListener {
            updateAccount()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun updateAccount() {
        val name = binding.etFullName.text.toString()
        val email = binding.etEmailProfile.text.toString()

        // hide keyboard
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)

        binding.progressBar.visibility = View.VISIBLE

        viewModel.updateUser(name, email).observe(viewLifecycleOwner) { result ->
            binding.progressBar.visibility = View.GONE

            if (result != null) {
                refreshData()
                Toast.makeText(context, "Account updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to update account", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun refreshData() {
        viewModel.currentUser().observe(viewLifecycleOwner) { user ->
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