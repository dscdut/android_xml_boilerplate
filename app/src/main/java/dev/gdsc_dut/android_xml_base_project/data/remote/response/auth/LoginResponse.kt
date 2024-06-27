package dev.gdsc_dut.android_xml_base_project.data.remote.response.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val access: String,
    val refresh: String
)