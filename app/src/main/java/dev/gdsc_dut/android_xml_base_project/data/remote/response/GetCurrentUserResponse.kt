package dev.gdsc_dut.android_xml_base_project.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCurrentUserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val role: UserRole,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

@JsonClass(generateAdapter = true)
data class UserRole(
    val id: Int,
    val name: String
)