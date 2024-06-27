package dev.gdsc_dut.android_xml_base_project.data.remote.request.user_management

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserRequest(
    val name: String,
    val email: String,
    val role: String
)