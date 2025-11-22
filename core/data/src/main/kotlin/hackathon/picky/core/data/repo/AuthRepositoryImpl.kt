package hackathon.picky.core.data.repo

import hackathon.picky.core.data.mapper.toResult
import hackathon.picky.core.network.datasource.auth.AuthDatasource
import hackathon.picky.core.network.di.FakeDataSource
import hackathon.picky.core.network.model.response.LoginResponse
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    @param:FakeDataSource private val authDatasource: AuthDatasource
): AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> {
        return authDatasource.login(email, password).toResult(
            transform = {
                it.isSuccess
            }
        )
    }
}