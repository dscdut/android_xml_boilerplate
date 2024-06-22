package dev.gdsc_dut.android_xml_base_project.data.remote

import dev.gdsc_dut.android_xml_base_project.data.remote.request.auth.LoginRequest
import dev.gdsc_dut.android_xml_base_project.data.remote.request.auth.RegisterRequest
import dev.gdsc_dut.android_xml_base_project.data.remote.response.LoginResponse
import okhttp3.Call
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("/auth/login/")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @POST("/auth/register/")
    suspend fun register(@Body body: RegisterRequest)

    companion object Factory {
        operator fun invoke(retrofit: Retrofit): APIService = retrofit.create()
    }
}
