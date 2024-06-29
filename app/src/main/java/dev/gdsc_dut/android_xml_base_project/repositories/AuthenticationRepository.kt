package dev.gdsc_dut.android_xml_base_project.repositories

import dev.gdsc_dut.android_xml_base_project.core.AppDispatchers
import dev.gdsc_dut.android_xml_base_project.data.local.LocalUserDataSource
import dev.gdsc_dut.android_xml_base_project.data.remote.APIService
import dev.gdsc_dut.android_xml_base_project.data.remote.request.auth.LoginRequest
import dev.gdsc_dut.android_xml_base_project.data.remote.request.auth.RegisterRequest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository
    @Inject
    constructor(
        private val apiService: APIService,
        private val localUserDataSource: LocalUserDataSource,
        private val appDispatchers: AppDispatchers,
    ) {
        fun getUser() = localUserDataSource.jwt

        suspend fun login(
            email: String,
            password: String,
        ) = runCatching {
            withContext(appDispatchers.io) {
                apiService
                    .login(body = LoginRequest(email, password))
                    .also {
                        localUserDataSource.update(it.access.toString())
                        Timber.d("Token stored after login: ${it.access.toString()}") // Log the token here.
                    }
            }
        }

        suspend fun register(
            name: String,
            email: String,
            role: Int,
            password: String,
        ) = runCatching {
            withContext(appDispatchers.io) {
                apiService
                    .register(
                        body = RegisterRequest(name, email, role, password),
                    ).also {
                        Timber.d("Register response: $it")
                    }
            }
        }

        suspend fun logout() {
            localUserDataSource.deleteAll()
        }
    }
