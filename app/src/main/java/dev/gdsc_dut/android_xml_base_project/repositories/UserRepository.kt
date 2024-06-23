package dev.gdsc_dut.android_xml_base_project.repositories

import dev.gdsc_dut.android_xml_base_project.core.AppDispatchers
import dev.gdsc_dut.android_xml_base_project.data.remote.APIService
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
}