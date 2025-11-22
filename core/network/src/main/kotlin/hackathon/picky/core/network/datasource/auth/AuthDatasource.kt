package hackathon.picky.core.network.datasource.auth

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.LoginResponse

interface AuthDatasource {
    suspend fun login(email: String, password: String): ApiResponse<LoginResponse>
}