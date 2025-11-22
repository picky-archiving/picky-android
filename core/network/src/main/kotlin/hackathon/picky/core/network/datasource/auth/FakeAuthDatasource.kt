package hackathon.picky.core.network.datasource.auth

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthDatasource @Inject constructor(): AuthDatasource {
    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        return ApiResponse.Success(LoginResponse(true))
    }
}