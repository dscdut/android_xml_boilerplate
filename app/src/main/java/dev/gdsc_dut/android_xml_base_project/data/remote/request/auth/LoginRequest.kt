package dev.gdsc_dut.android_xml_base_project.data.remote.request.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
   val email: String,
    val password: String
)
