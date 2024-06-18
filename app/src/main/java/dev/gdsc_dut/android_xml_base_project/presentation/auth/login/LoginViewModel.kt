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

data class LoginUiState(
    val loadingStatus: LoadingStatus = LoadingStatus.INITIAL,
    val error: String? = null,
    val email: String = "",
    val password: String = "",
    // Add other fields as needed like handle error
)

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val authenticationRepository: AuthenticationRepository,
    ) : BaseViewModel() {
        private val _uiState = MutableStateFlow(LoginUiState())
        val uiState = _uiState.asStateFlow()

        fun changeEmail(email: String) {
            _uiState.update {
                it.copy(email = email)
            }
        }

        fun changePassword(password: String) {
            _uiState.update {
                it.copy(password = password)
            }
        }

        fun login() {
            viewModelScope.launch {
                val currentState =
                    _uiState.updateAndGet {
                        it.copy(loadingStatus = LoadingStatus.LOADING)
                    }
                // Call login function from repository
                // If success, update uiState with isLoading = false
                // If failed, update uiState with isLoading = false and error message
                authenticationRepository
                    .login(
                        email = currentState.email,
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
