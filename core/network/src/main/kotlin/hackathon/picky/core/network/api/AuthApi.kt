package hackathon.picky.core.network.api

import hackathon.picky.core.network.model.response.LoginResponse
import retrofit2.Response

interface AuthApi {
    suspend fun login(email: String, password: String): Response<LoginResponse>
}