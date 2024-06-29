package dev.gdsc_dut.android_xml_base_project.presentation.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gdsc_dut.android_xml_base_project.core.base.BaseViewModel
import dev.gdsc_dut.android_xml_base_project.repositories.AuthenticationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

enum class AuthenticationState {
    UNKNOWN,
    AUTHENTICATED,
    UNAUTHENTICATED,
}

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        authenticationRepository: AuthenticationRepository,
    ) : BaseViewModel() {
        val authState =
            authenticationRepository
                .getUser()
                .map {
                    if (it == null) {
                        AuthenticationState.UNAUTHENTICATED
                    } else {
                        AuthenticationState.AUTHENTICATED
                    }
                }.stateIn(
                    scope = viewModelScope,
                    initialValue = AuthenticationState.UNKNOWN,
                    started = SharingStarted.WhileSubscribed(5000),
                )
    }
