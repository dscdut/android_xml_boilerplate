package dev.gdsc_dut.android_xml_base_project.data.remote.response.user_management

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUsersResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<User>
) {
    @JsonClass(generateAdapter = true)
    data class User(
        val id: Int,
        val name: String,
        val email: String,
        val role: Role,
        @Json(name = "created_at") val createdAt: String,
        @Json(name = "updated_at") val updatedAt: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Role(
            val id: Int,
            val name: String
        )
    }
}