package dev.gdsc_dut.android_xml_base_project.presentation.splash

import androidx.lifecycle.viewModelScope
import dev.gdsc_dut.android_xml_base_project.core.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AuthenticationState {
    UNKNOWN,
    AUTHENTICATED,
    UNAUTHENTICATED
}

class SplashViewModel : BaseViewModel() {

    private val _authState = MutableStateFlow(AuthenticationState.UNKNOWN);
    val authState = _authState.asStateFlow()

    fun getAuthenticationState() {
        viewModelScope.launch {
            delay(1000)
            _authState.update {
                AuthenticationState.UNAUTHENTICATED
            }
        }
    }
}