package dev.gdsc_dut.android_xml_base_project.data.remote.request.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    val name: String,
    val email: String,
    val role: Int,
    val password: String
)