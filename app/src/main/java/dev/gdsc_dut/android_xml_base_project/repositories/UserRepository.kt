package dev.gdsc_dut.android_xml_base_project.repositories

import dev.gdsc_dut.android_xml_base_project.core.AppDispatchers
import dev.gdsc_dut.android_xml_base_project.data.remote.APIService
import dev.gdsc_dut.android_xml_base_project.data.remote.request.user_management.UpdateUserRequest
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: APIService,
    private val appDispatchers: AppDispatchers,
) {
    suspend fun getCurrentUser() = runCatching {
        withContext(appDispatchers.io) {
            apiService.getCurrentUser()
        }
    }

    suspend fun updateUser(
        name: String,
        email: String
    ) = runCatching {
        val currentUser = apiService.getCurrentUser()
        withContext(appDispatchers.io) {
            apiService.updateUser(
                currentUser.id,
                body = UpdateUserRequest(name, email, currentUser.role.name)
            )
        }
    }

    suspend fun getUsers() = runCatching {
        withContext(appDispatchers.io) {
            apiService.getUsers()
        }
    }
}