package dev.gdsc_dut.android_xml_base_project.presentation.auth.register

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentRegisterBinding
import dev.gdsc_dut.android_xml_base_project.presentation.auth.login.RegisterViewModel
import dev.gdsc_dut.android_xml_base_project.utils.extensions.collectIn
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.widget.textChanges
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    private val binding by viewBinding<FragmentRegisterBinding>()

    private val viewModel by viewModels<RegisterViewModel>()

    override fun bindEvent() {
        binding.etEmail
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changeEmail(it.toString()) }
            .launchIn(lifecycleScope)

        binding.etFullname
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changeFullName(it.toString()) }
            .launchIn(lifecycleScope)

        binding.etPassword
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changePhoneNumber(it.toString()) }
            .launchIn(lifecycleScope)

        binding.etConfirmationPassword
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changeConfirmationPassword(it.toString()) }
            .launchIn(lifecycleScope)

        binding.etPhoneNumber
            .textChanges()
            .skipInitialValue()
            .onEach { viewModel.changePassword(it.toString()) }
            .launchIn(lifecycleScope)

        binding.btnRegister.setOnClickListener {
            viewModel.register()
        }
    }

    override fun collectState() {
        viewModel.uiState
            .map { it.loadingStatus }
            .distinctUntilChanged()
            .collectIn(this) {
                Timber.d("Loading status: $it")
                binding.btnRegister.isVisible = !it.isLoading
                binding.fLoading.isVisible = it.isLoading

                if (it.isSuccess) {
                    findNavController().popBackStack()
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
