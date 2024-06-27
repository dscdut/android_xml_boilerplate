package dev.gdsc_dut.android_xml_base_project.data.remote

import dev.gdsc_dut.android_xml_base_project.data.remote.request.auth.LoginRequest
import dev.gdsc_dut.android_xml_base_project.data.remote.request.auth.RegisterRequest
import dev.gdsc_dut.android_xml_base_project.data.remote.request.user_management.UpdateUserRequest
import dev.gdsc_dut.android_xml_base_project.data.remote.response.user_management.GetCurrentUserResponse
import dev.gdsc_dut.android_xml_base_project.data.remote.response.auth.LoginResponse
import dev.gdsc_dut.android_xml_base_project.data.remote.response.user_management.GetUsersResponse
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {
    @Headers("@: NoAuth")
    @POST("auth/login/")
    suspend fun login(
        @Body body: LoginRequest,
    ): LoginResponse

    @Headers("@: NoAuth")
    @POST("auth/register/")
    suspend fun register(
        @Body body: RegisterRequest,
    )

    @GET("users/me/")
    suspend fun getCurrentUser(): GetCurrentUserResponse

    @PUT("users/{id}/")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body body: UpdateUserRequest
    ): GetCurrentUserResponse

    @GET("users/")
    suspend fun getUsers(): GetUsersResponse

    companion object Factory {
        operator fun invoke(retrofit: Retrofit): APIService = retrofit.create()
    }
}
