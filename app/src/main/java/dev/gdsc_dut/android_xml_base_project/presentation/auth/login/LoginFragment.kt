package dev.gdsc_dut.android_xml_base_project.presentation.auth.login

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentLoginBinding
import dev.gdsc_dut.android_xml_base_project.presentation.main.MainActivity
import dev.gdsc_dut.android_xml_base_project.utils.extensions.collectIn
import dev.gdsc_dut.android_xml_base_project.utils.extensions.startActivity
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.widget.textChanges
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding<FragmentLoginBinding>()

    private val viewModel by viewModels<LoginViewModel>()

    override fun setupView() {
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    override fun bindEvent() {
        binding.etEmail
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changeEmail(it.toString()) }
            .launchIn(lifecycleScope)

        binding.etPassword
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changePassword(it.toString()) }
            .launchIn(lifecycleScope)

        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
    }

    override fun collectState() {
        viewModel.uiState
            .map { it.loadingStatus }
            .distinctUntilChanged()
            .collectIn(this) {
                Timber.d("Loading status: $it")
                binding.btnLogin.isVisible = !it.isLoading
                binding.progressBar.isVisible = it.isLoading

                if (it.isSuccess) {
                    startActivity<MainActivity>()
                    activity?.finish()
                }
            }

        viewModel.uiState
            .map { it.error }
            .distinctUntilChanged()
            .collectIn(this) { error ->
                error?.also {
                    Snackbar.make(this.requireView(), it, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.clearError()
            }
    }
}
