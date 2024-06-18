package dev.gdsc_dut.android_xml_base_project.presentation.auth.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gdsc_dut.android_xml_base_project.core.LoadingStatus
import dev.gdsc_dut.android_xml_base_project.core.base.BaseViewModel
import dev.gdsc_dut.android_xml_base_project.repositories.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val loadingStatus: LoadingStatus = LoadingStatus.INITIAL,
    val error: String? = null,
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val phoneNumber: String = "",
    val fullName: String = "",
    // Add other fields as needed like handle error
)

@HiltViewModel
class RegisterViewModel
    @Inject
    constructor(
        private val authenticationRepository: AuthenticationRepository,
    ) : BaseViewModel() {
        private val _uiState = MutableStateFlow(RegisterUiState())
        val uiState = _uiState.asStateFlow()

        fun changeEmail(email: String) {
            _uiState.update {
                it.copy(email = email)
            }
        }

        fun changeFullName(fullName: String) {
            _uiState.update {
                it.copy(fullName = fullName)
            }
        }

        fun changePhoneNumber(phoneNumber: String) {
            _uiState.update {
                it.copy(phoneNumber = phoneNumber)
            }
        }

        fun changePassword(password: String) {
            _uiState.update {
                it.copy(password = password)
            }
        }

        fun changeConfirmationPassword(confirmationPassword: String) {
            _uiState.update {
                it.copy(confirmationPassword = confirmationPassword)
            }
        }

        fun register() {
            viewModelScope.launch {
                val currentState =
                    _uiState.updateAndGet {
                        it.copy(loadingStatus = LoadingStatus.LOADING)
                    }
                // Call login function from repository
                // If success, update uiState with isLoading = false
                // If failed, update uiState with isLoading = false and error message
                authenticationRepository
                    .register(
                        email = currentState.email,
                        name = "",
                        role = 1,
                        password = currentState.password,
                    ).onSuccess {
                        _uiState.update {
                            it.copy(loadingStatus = LoadingStatus.SUCCESS)
                        }
                    }.onFailure { exception ->
                        _uiState.update {
                            it.copy(
                                loadingStatus = LoadingStatus.ERROR,
                                error = exception.toString(),
                            )
                        }
                    }
            }
        }

        fun clearError() {
            _uiState.update {
                it.copy(error = null)
            }
        }
    }
