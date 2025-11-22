package hackathon.picky.core.network.datasource.auth

import hackathon.picky.core.network.api.AuthApi
import hackathon.picky.core.network.datasource.auth.AuthDatasource
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.LoginResponse
import hackathon.picky.core.network.util.runRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitAuthDatasource @Inject constructor(
    private val authApi: AuthApi
): AuthDatasource {
    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse>  =
        runRemote{
            authApi.login(email, password)
        }
}